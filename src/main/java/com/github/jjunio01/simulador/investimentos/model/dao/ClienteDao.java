/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package com.github.jjunio01.simulador.investimentos.model.dao;

import com.github.jjunio01.simulador.investimentos.model.Cliente;
import com.github.jjunio01.simulador.investimentos.model.Usuario;
import com.github.jjunio01.simulador.investimentos.util.ErroSistema;
import java.util.List;
import org.hibernate.PropertyNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Jose Junio
 */
public class ClienteDao implements DaoGenerico<Cliente> {

    private static ClienteDao instance;
    private Cliente cliente = new Cliente();
    private List<Cliente> clientes;

    private ClienteDao() {
    }

    //Método para criar instancia única de ClienteDao
    public static ClienteDao getInstance() {

        if (instance == null) {
            instance = new ClienteDao();
        }
        return instance;
    }

    @Override
    public void create(Cliente t) throws ErroSistema {
        //Obtém uma sessão aberta com o banco e com uma transação iniciada.
        Session session = DaoBancoMySql.iniciarTransacao();
        try {
            //Salva o obejto no Banco de Dados
            session.save(t);
        } catch (NullPointerException erroCreate) {
            throw new ErroSistema("Erro ao inserir cliente no banco de dados", erroCreate);
        }
        //Recupera a sessão aberta encerrando a transação e fechando a conexão.
        DaoBancoMySql.fecharTransacao(session);
    }

    @Override
    public void update(Cliente t) throws ErroSistema {
        Session session = DaoBancoMySql.iniciarTransacao();
        try {
            //Faz o update do obejto no Banco de Dados
            session.update(t);
        } catch (Exception erroUpdate) {
            throw new ErroSistema("Erro ao atualizar cliente no banco de dados", erroUpdate);
        }
        DaoBancoMySql.fecharTransacao(session);
    }

    @Override
    public Cliente read(String cpf) throws ErroSistema, PropertyNotFoundException {
        Session session = DaoBancoMySql.iniciarTransacao();
        Cliente cli = null;
        try {
            /*Faz consulta no banco de dados utilizando como parâmetro da consulta o
            CPF recebido pelo método, retornando uma lista de obejtos*/
            Query consulta = session.createQuery("from Cliente where cpf like '" + cpf + "'");
            //Resupera o primeiro e único elemento da consulta.
            cli = (Cliente) consulta.list().get(0);
        } catch (Exception erroRead) {
            throw new ErroSistema("Erro ao consultar cliente", erroRead);
        } finally {
            DaoBancoMySql.fecharTransacao(session);
            return cli;
        }
    }

    public Usuario readLogin(String login) throws ErroSistema {
        Session session = DaoBancoMySql.iniciarTransacao();
        Usuario usuario = null;
        try {
            /*Faz consulta no banco de dados utilizando como parâmetro da consulta o
            Login recebido pelo método, retornando uma lista de obejtos*/
            Query consulta = session.createQuery("from Usuario where login like '" + login + "'");
            usuario = (Usuario) consulta.list().get(0);
        } catch (Exception erroReadLogin) {
            throw new ErroSistema("Erro ao consultar login", erroReadLogin);
        } finally {
            DaoBancoMySql.fecharTransacao(session);
            return usuario;
        }

    }

    public Cliente readLogado(String login) throws ErroSistema {
        Session session = DaoBancoMySql.iniciarTransacao();
        Cliente cliente = null;
        try {
            /*Faz consulta no banco de dados utilizando como parâmetro da consulta o
            Login recebido pelo método, retornando uma lista de obejtos*/
            Query consulta = session.createQuery(" from Cliente as cli where cli.usuario.login like'" + login + "'");
            cliente = (Cliente) consulta.list().get(0);
        } catch (Exception erroReadLogin) {
            throw new ErroSistema("Erro ao consultar cliente logado", erroReadLogin);
        } finally {
            DaoBancoMySql.fecharTransacao(session);
            return cliente;
        }
    }

    @Override
    public void delete(String cpf) throws ErroSistema {
        Session session = DaoBancoMySql.iniciarTransacao();
        Cliente cli = null;
        try {
            /*Faz consulta no banco de dados utilizando como parâmetro da consulta o
            CPF recebido pelo método, retornando uma lista de obejtos*/
            Query consulta = session.createQuery("from Cliente where cpf like '" + cpf + "'");
            cli = (Cliente) consulta.list().get(0);
            //Deleta o obejto recuperado do banco
            session.delete(cli);
        } catch (Exception erroRead) {
            throw new ErroSistema("Erro ao deletar cliente", erroRead);
        } finally {
            DaoBancoMySql.fecharTransacao(session);
        }

    }

    @Override
    public List<Cliente> readALL() throws ErroSistema {
        Session session = DaoBancoMySql.iniciarTransacao();
        List<Cliente> clientes = null;
        try {
            //Faz consulta no banco todos os clientes cadastrados.
            Query consulta = session.createQuery("from Cliente");
            //Recupera todos os objetos do banco
            clientes = consulta.list();
        } catch (Exception erroReadAll) {
            throw new ErroSistema("Erro ao consultar lista de clientes", erroReadAll);
        }
        DaoBancoMySql.fecharTransacao(session);
        return clientes;
    }
}
