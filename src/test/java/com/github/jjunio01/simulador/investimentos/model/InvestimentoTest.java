/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jjunio01.simulador.investimentos.model;

import java.math.BigDecimal;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jose Junio
 */
public class InvestimentoTest {

    public InvestimentoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of calcularRendimentos method, of class Investimento.
     */
    @Test
    public void testCalcularRendimentos() {
        System.out.println("calcularRendimentos");
        Investimento instance = new InvestimentoImpl();
        instance.calcularRendimentos();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class Investimento.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Investimento instance = new InvestimentoImpl();
        Integer expResult = null;
        Integer result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValor method, of class Investimento.
     */
    @Test
    public void testGetValor() {
        System.out.println("getValor");
        Investimento instance = new InvestimentoImpl();
        BigDecimal expResult = null;
        BigDecimal result = instance.getValor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setValor method, of class Investimento.
     */
    @Test
    public void testSetValor() {
        System.out.println("setValor");
        BigDecimal valor = null;
        Investimento instance = new InvestimentoImpl();
        instance.setValor(valor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPeriodo method, of class Investimento.
     */
    @Test
    public void testGetPeriodo() {
        System.out.println("getPeriodo");
        Investimento instance = new InvestimentoImpl();
        int expResult = 0;
        int result = instance.getPeriodo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPeriodo method, of class Investimento.
     */
    @Test
    public void testSetPeriodo() {
        System.out.println("setPeriodo");
        int periodo = 0;
        Investimento instance = new InvestimentoImpl();
        instance.setPeriodo(periodo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRendimentos method, of class Investimento.
     */
    @Test
    public void testGetRendimentos() {
        System.out.println("getRendimentos");
        Investimento instance = new InvestimentoImpl();
        BigDecimal expResult = null;
        BigDecimal result = instance.getRendimentos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRendimentos method, of class Investimento.
     */
    @Test
    public void testSetRendimentos() {
        System.out.println("setRendimentos");
        BigDecimal rendimentos = null;
        Investimento instance = new InvestimentoImpl();
        instance.setRendimentos(rendimentos);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIndiceRendimento method, of class Investimento.
     */
    @Test
    public void testGetIndiceRendimento() {
        System.out.println("getIndiceRendimento");
        Investimento instance = new InvestimentoImpl();
        BigDecimal expResult = null;
        BigDecimal result = instance.getIndiceRendimento();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIndiceRendimento method, of class Investimento.
     */
    @Test
    public void testSetIndiceRendimento() {
        System.out.println("setIndiceRendimento");
        BigDecimal indiceRendimento = null;
        Investimento instance = new InvestimentoImpl();
        instance.setIndiceRendimento(indiceRendimento);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValorAtualizado method, of class Investimento.
     */
    @Test
    public void testGetValorAtualizado() {
        System.out.println("getValorAtualizado");
        Investimento instance = new InvestimentoImpl();
        BigDecimal expResult = null;
        BigDecimal result = instance.getValorAtualizado();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setValorAtualizado method, of class Investimento.
     */
    @Test
    public void testSetValorAtualizado() {
        System.out.println("setValorAtualizado");
        BigDecimal valorAtualizado = null;
        Investimento instance = new InvestimentoImpl();
        instance.setValorAtualizado(valorAtualizado);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDataAcesso method, of class Investimento.
     */
    @Test
    public void testGetDataAcesso() {
        System.out.println("getDataAcesso");
        Investimento instance = new InvestimentoImpl();
        Date expResult = null;
        Date result = instance.getDataAcesso();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDataAcesso method, of class Investimento.
     */
    @Test
    public void testSetDataAcesso() {
        System.out.println("setDataAcesso");
        Date dataAcesso = null;
        Investimento instance = new InvestimentoImpl();
        instance.setDataAcesso(dataAcesso);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTipo method, of class Investimento.
     */
    @Test
    public void testGetTipo() {
        System.out.println("getTipo");
        Investimento instance = new InvestimentoImpl();
        String expResult = "";
        String result = instance.getTipo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTipo method, of class Investimento.
     */
    @Test
    public void testSetTipo() {
        System.out.println("setTipo");
        String tipo = "";
        Investimento instance = new InvestimentoImpl();
        instance.setTipo(tipo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class InvestimentoImpl extends Investimento {

        public void calcularRendimentos() {
        }
    }

}
