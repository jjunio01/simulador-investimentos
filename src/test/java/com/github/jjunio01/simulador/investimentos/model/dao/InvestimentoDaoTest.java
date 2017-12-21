/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jjunio01.simulador.investimentos.model.dao;

import com.github.jjunio01.simulador.investimentos.model.Cliente;
import com.github.jjunio01.simulador.investimentos.model.InvestCDB;
import com.github.jjunio01.simulador.investimentos.model.InvestLCI;
import com.github.jjunio01.simulador.investimentos.model.InvestPoupanca;
import com.github.jjunio01.simulador.investimentos.model.Investimento;
import com.github.jjunio01.simulador.investimentos.model.Usuario;
import com.github.jjunio01.simulador.investimentos.util.ErroSistema;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Jose Junio
 */
public class InvestimentoDaoTest {

    @Rule
    public ExpectedException excecao = ExpectedException.none();

    /**
     * Test of salvar method, of class InvestimentoDao.
     *
     * @throws com.github.jjunio01.simulador.investimentos.util.ErroSistema
     */
    @Test
    public void testSalvar_InvestPoupanca() throws ErroSistema {

        Cliente clienteTest = new Cliente("Teste", "0123", new Date(), "teste@email.com", new Usuario("novo", "senha"));
        InvestPoupanca poupanca = new InvestPoupanca();
        poupanca.setValor(new BigDecimal("1000"));
        poupanca.setPeriodo(1);
        List<Investimento> listaInvestimentos = new ArrayList<>();
        listaInvestimentos.add(poupanca);
        clienteTest.setListaInvestimentos(listaInvestimentos);
        ClienteDao.getInstance().create(clienteTest);
        Cliente clienteBD = ClienteDao.getInstance().read("0123");
        Assert.assertEquals(clienteBD.getListaInvestimentos().get(0).getValor(),
                Investimento.formatarNumero(new BigDecimal("1000")));
        ClienteDao.getInstance().delete("0123");

    }

    @Test
    public void testSalvarPoupancaValorZero() throws ErroSistema {

        Cliente clienteTest = new Cliente("Teste", "0123", new Date(), "teste@email.com", new Usuario("novo", "senha"));
        InvestPoupanca poupanca = new InvestPoupanca();
        poupanca.setValor(new BigDecimal("0"));
        poupanca.setPeriodo(1);
        List<Investimento> listaInvestimentos = new ArrayList<>();
        listaInvestimentos.add(poupanca);
        clienteTest.setListaInvestimentos(listaInvestimentos);
        ClienteDao.getInstance().create(clienteTest);
        Cliente clienteBD = ClienteDao.getInstance().read("0123");
        Assert.assertEquals(clienteBD.getListaInvestimentos().get(0).getValor(),
                Investimento.formatarNumero(new BigDecimal("0")));
        ClienteDao.getInstance().delete("0123");

    }

    @Test
    public void testSalvarPoupancaValorNegativo() throws ErroSistema {

        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("Valor Negativo");

        Cliente clienteTest = new Cliente("Teste", "0123", new Date(), "teste@email.com", new Usuario("novo", "senha"));
        InvestPoupanca poupanca = new InvestPoupanca();
        poupanca.setValor(new BigDecimal("-1000"));
        poupanca.setPeriodo(1);
        List<Investimento> listaInvestimentos = new ArrayList<>();
        listaInvestimentos.add(poupanca);
        clienteTest.setListaInvestimentos(listaInvestimentos);
        ClienteDao.getInstance().create(clienteTest);
        Cliente clienteBD = ClienteDao.getInstance().read("0123");

    }

    @Test
    public void testRendimentosPoupancaValorZero() throws ErroSistema {

        Cliente clienteTest = new Cliente("Teste", "0123", new Date(), "teste@email.com", new Usuario("novo", "senha"));
        InvestPoupanca poupanca = new InvestPoupanca();
        poupanca.setValor(new BigDecimal("0"));
        poupanca.setPeriodo(1);
        List<Investimento> listaInvestimentos = new ArrayList<>();
        listaInvestimentos.add(poupanca);
        clienteTest.setListaInvestimentos(listaInvestimentos);
        ClienteDao.getInstance().create(clienteTest);
        Cliente clienteBD = ClienteDao.getInstance().read("0123");
        Assert.assertEquals(clienteBD.getListaInvestimentos().get(0).getRendimentos(),
                Investimento.formatarNumero(new BigDecimal("0")));
        ClienteDao.getInstance().delete("0123");
    }

    /**
     * Test of salvar method, of class InvestimentoDao.
     *
     * @throws com.github.jjunio01.simulador.investimentos.util.ErroSistema
     */
    @Test
    public void testSalvar_InvestCDB() throws ErroSistema {

        Cliente clienteTest = new Cliente("Teste", "0123", new Date(), "teste@email.com", new Usuario("novo", "senha"));
        InvestCDB cdb = new InvestCDB();
        cdb.setValor(new BigDecimal("0"));
        cdb.setPeriodo(90);
        List<Investimento> listaInvestimentos = new ArrayList<>();
        listaInvestimentos.add(cdb);
        clienteTest.setListaInvestimentos(listaInvestimentos);
        ClienteDao.getInstance().create(clienteTest);
        Cliente clienteBD = ClienteDao.getInstance().read("0123");
        Assert.assertEquals(clienteBD.getListaInvestimentos().get(0).getRendimentos(),
                Investimento.formatarNumero(new BigDecimal("0")));
        ClienteDao.getInstance().delete("0123");
    }

    @Test
    public void testSalvarCdbValorZero() throws ErroSistema {

        Cliente clienteTest = new Cliente("Teste", "0123", new Date(), "teste@email.com", new Usuario("novo", "senha"));
        InvestCDB cdb = new InvestCDB();
        cdb.setValor(new BigDecimal("0"));
        cdb.setPeriodo(1);
        List<Investimento> listaInvestimentos = new ArrayList<>();
        listaInvestimentos.add(cdb);
        clienteTest.setListaInvestimentos(listaInvestimentos);
        ClienteDao.getInstance().create(clienteTest);
        Cliente clienteBD = ClienteDao.getInstance().read("0123");
        Assert.assertEquals(clienteBD.getListaInvestimentos().get(0).getValor(),
                Investimento.formatarNumero(new BigDecimal("0")));
        ClienteDao.getInstance().delete("0123");

    }

    @Test
    public void testSalvarCdbValorNegativo() throws ErroSistema {

        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("Valor Negativo");

        Cliente clienteTest = new Cliente("Teste", "0123", new Date(), "teste@email.com", new Usuario("novo", "senha"));
        InvestCDB cdb = new InvestCDB();
        cdb.setValor(new BigDecimal("-1000"));
        cdb.setPeriodo(1);
        List<Investimento> listaInvestimentos = new ArrayList<>();
        listaInvestimentos.add(cdb);
        clienteTest.setListaInvestimentos(listaInvestimentos);
        ClienteDao.getInstance().create(clienteTest);
        Cliente clienteBD = ClienteDao.getInstance().read("0123");

    }

    @Test
    public void testRendimentosCdbValorZero() throws ErroSistema {

        Cliente clienteTest = new Cliente("Teste", "0123", new Date(), "teste@email.com", new Usuario("novo", "senha"));
        InvestCDB cdb = new InvestCDB();
        cdb.setValor(new BigDecimal("0"));
        cdb.setPeriodo(1);
        List<Investimento> listaInvestimentos = new ArrayList<>();
        listaInvestimentos.add(cdb);
        clienteTest.setListaInvestimentos(listaInvestimentos);
        ClienteDao.getInstance().create(clienteTest);
        Cliente clienteBD = ClienteDao.getInstance().read("0123");
        Assert.assertEquals(clienteBD.getListaInvestimentos().get(0).getRendimentos(),
                Investimento.formatarNumero(new BigDecimal("0")));
        ClienteDao.getInstance().delete("0123");
    }

    /**
     * Test of salvarLCI method, of class InvestimentoDao.
     *
     * @throws com.github.jjunio01.simulador.investimentos.util.ErroSistema
     */
    @Test
    public void testSalvarLCI() throws ErroSistema {

        Cliente clienteTest = new Cliente("Teste", "0123", new Date(), "teste@email.com", new Usuario("novo", "senha"));
        InvestLCI lci = new InvestLCI();
        lci.setValor(new BigDecimal("0"));
        lci.setPeriodo(90);
        List<Investimento> listaInvestimentos = new ArrayList<>();
        listaInvestimentos.add(lci);
        clienteTest.setListaInvestimentos(listaInvestimentos);
        ClienteDao.getInstance().create(clienteTest);
        Cliente clienteBD = ClienteDao.getInstance().read("0123");
        Assert.assertEquals(clienteBD.getListaInvestimentos().get(0).getRendimentos(),
                Investimento.formatarNumero(new BigDecimal("0")));
        ClienteDao.getInstance().delete("0123");
    }

    @Test
    public void testSalvarLciComValorZero() throws ErroSistema {

        Cliente clienteTest = new Cliente("Teste", "0123", new Date(), "teste@email.com", new Usuario("novo", "senha"));
        InvestLCI lci = new InvestLCI();
        lci.setValor(new BigDecimal("0"));
        lci.setPeriodo(1);
        List<Investimento> listaInvestimentos = new ArrayList<>();
        listaInvestimentos.add(lci);
        clienteTest.setListaInvestimentos(listaInvestimentos);
        ClienteDao.getInstance().create(clienteTest);
        Cliente clienteBD = ClienteDao.getInstance().read("0123");
        Assert.assertEquals(clienteBD.getListaInvestimentos().get(0).getValor(),
                Investimento.formatarNumero(new BigDecimal("0")));
        ClienteDao.getInstance().delete("0123");
    }

    @Test
    public void testSalvarLciValorNegativo() throws ErroSistema {

        excecao.expect(IllegalArgumentException.class);
        excecao.expectMessage("Valor Negativo");

        Cliente clienteTest = new Cliente("Teste", "0123", new Date(), "teste@email.com", new Usuario("novo", "senha"));
        InvestLCI lci = new InvestLCI();
        lci.setValor(new BigDecimal("-1000"));
        lci.setPeriodo(1);
        List<Investimento> listaInvestimentos = new ArrayList<>();
        listaInvestimentos.add(lci);
        clienteTest.setListaInvestimentos(listaInvestimentos);
        ClienteDao.getInstance().create(clienteTest);
        Cliente clienteBD = ClienteDao.getInstance().read("0123");

    }

    @Test
    public void testRendimentosLciValorZero() throws ErroSistema {

        Cliente clienteTest = new Cliente("Teste", "0123", new Date(), "teste@email.com", new Usuario("novo", "senha"));
        InvestLCI lci = new InvestLCI();
        lci.setValor(new BigDecimal("0"));
        lci.setPeriodo(1);
        List<Investimento> listaInvestimentos = new ArrayList<>();
        listaInvestimentos.add(lci);
        clienteTest.setListaInvestimentos(listaInvestimentos);
        ClienteDao.getInstance().create(clienteTest);
        Cliente clienteBD = ClienteDao.getInstance().read("0123");
        Assert.assertEquals(clienteBD.getListaInvestimentos().get(0).getRendimentos(),
                Investimento.formatarNumero(new BigDecimal("0")));
        ClienteDao.getInstance().delete("0123");
    }

}
