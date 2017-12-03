/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jjunio01.simulador.investimentos.controller;

import com.github.jjunio01.simulador.investimentos.model.Cliente;
import com.github.jjunio01.simulador.investimentos.model.Usuario;
import com.github.jjunio01.simulador.investimentos.model.dao.ClienteDao;
import com.github.jjunio01.simulador.investimentos.util.Criptografia;
import com.github.jjunio01.simulador.investimentos.util.ErroSistema;
import com.github.jjunio01.simulador.investimentos.util.Message;
import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.hibernate.exception.JDBCConnectionException;

/**
 *
 * @author Jose Junio
 */
@ManagedBean
@RequestScoped
public class ControllerCliente {

    private Cliente cliente;

    public ControllerCliente() {

    }

    @PostConstruct
    public void init() {
        cliente = new Cliente();
    }

    public void salvar(Cliente clienteNovo) throws ErroSistema, IOException {
        try {
            //Verifica se já existe cadastrado um cliente com o CPF e Login informados.
            Cliente novo = ClienteDao.getInstance().read(clienteNovo.getCpf());
            Usuario novoLogin = ClienteDao.getInstance().readLogin(clienteNovo.getUsuario().getLogin());
            if (novo == null && novoLogin == null) {
                //Criptograva a senha e seta no usuário cliente.
                Usuario usurioNovo = clienteNovo.getUsuario();
                usurioNovo.setSenha(Criptografia.criptografarSenha(clienteNovo.getUsuario().getSenha()));
                clienteNovo.setUsuario(usurioNovo);
                //Salva o cliente no banco de dados.
                ClienteDao.getInstance().create(clienteNovo);
                Message.getInstance().adicionarMensagem(null, "Cliente Salvo com sucesso", FacesMessage.SEVERITY_INFO);
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            } else {
                Message.getInstance().adicionarMensagem(null, "Já cadastrado cliente para o CPF ou Login informado", FacesMessage.SEVERITY_WARN);
            }
        } catch (JDBCConnectionException erroAbrirConexao) {
            Message.getInstance().adicionarMensagem(erroAbrirConexao.getMessage(), erroAbrirConexao.getCause().getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        cliente = new Cliente();
    }

    public void excluir() throws ErroSistema, IOException {
        try {
            //Recupera o cliente utilizando o CPF.
            Cliente clienteLogado = (Cliente) FacesContext.getCurrentInstance().
                    getExternalContext().getSessionMap().get("user");
            if (clienteLogado.getCpf().equals(this.cliente.getCpf())) {
                Cliente novo = ClienteDao.getInstance().read(this.cliente.getCpf());
                if (novo == null) {
                    Message.getInstance().adicionarMensagem(null, "Cliente não encontrado para este CPF:", FacesMessage.SEVERITY_ERROR);
                } else {
                    //Deleta o cliente.
                    ClienteDao.getInstance().delete(this.cliente.getCpf());
                    Message.getInstance().adicionarMensagem(null, "Cadastro excluído com sucesso", FacesMessage.SEVERITY_INFO);
                    //Retira o cliente da sessão
                    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
                    //Redireciona ao Login
                    FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
                    cliente = new Cliente();
                }
            } else {
                Message.getInstance().adicionarMensagem(null, "Seu CPF está incorreto.", FacesMessage.SEVERITY_INFO);
            }
        } catch (ErroSistema erroExcluir) {
            Message.getInstance().adicionarMensagem(erroExcluir.getMessage(), erroExcluir.getCause().getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void alterar(Cliente cliente) throws ErroSistema, IOException {

        try {
            //Recupera o cliente do banco de dados.
            Cliente novo = ClienteDao.getInstance().read(cliente.getCpf());
            //Atualiza os valores
            novo.setDataNasc(cliente.getDataNasc());
            novo.setEmail(cliente.getEmail());
            novo.setNome(cliente.getNome());
            //Salva as alterações no banco de dados.
            ClienteDao.getInstance().update(novo);
            Message.getInstance().adicionarMensagem(null, "Cadastro alterado com sucesso", FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch (ErroSistema erroAlterar) {
            Message.getInstance().adicionarMensagem(erroAlterar.getMessage(), erroAlterar.getCause().getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        cliente = new Cliente();

    }

    public void consultar(String cpf) throws ErroSistema {
        try {
            //Recupera o cliente do banco de dados.
            this.cliente = ClienteDao.getInstance().read(cpf);
            if (cliente == null) {
                Message.getInstance().adicionarMensagem(null, " Não encontrado Cliente para o CPF informado", FacesMessage.SEVERITY_ERROR);
                this.cliente = new Cliente();
            }
        } catch (ErroSistema erroConsultar) {
            Message.getInstance().adicionarMensagem(erroConsultar.getMessage(), erroConsultar.getCause().getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public List<Cliente> consultarTodos() throws ErroSistema {
        //Recupera todos os clientes do banco de dados.
        List<Cliente> clientes = ClienteDao.getInstance().readALL();
        if (clientes == null || clientes.isEmpty()) {
            Message.getInstance().adicionarMensagem(null, "Não existem clientes cadastrados no sistema", FacesMessage.SEVERITY_ERROR);
        }
        return clientes;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
