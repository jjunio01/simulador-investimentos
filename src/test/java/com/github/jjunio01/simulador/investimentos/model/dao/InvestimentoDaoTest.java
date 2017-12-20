/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jjunio01.simulador.investimentos.model.dao;

import com.github.jjunio01.simulador.investimentos.model.InvestCDB;
import com.github.jjunio01.simulador.investimentos.model.InvestLCI;
import com.github.jjunio01.simulador.investimentos.model.InvestPoupanca;
import com.github.jjunio01.simulador.investimentos.util.ErroSistema;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
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
     * Test of getInstance method, of class InvestimentoDao.
     */
    @Ignore
    @Test
    public void testGetInstance() {

    }

    /**
     * Test of salvar method, of class InvestimentoDao.
     */
    @Ignore
    @Test
    public void testSalvar_InvestPoupanca() {

        InvestPoupanca poupanca = new InvestPoupanca();
        poupanca.setValor(new BigDecimal("1000"));
        poupanca.setPeriodo(1);

        try {
            InvestimentoDao.getInstance().salvar(poupanca);
        } catch (ErroSistema ex) {

        }

    }

    /**
     * Test of salvar method, of class InvestimentoDao.
     */
    @Ignore
    @Test
    public void testSalvar_InvestCDB() throws Exception {

    }

    /**
     * Test of salvarLCI method, of class InvestimentoDao.
     */
    @Ignore
    @Test
    public void testSalvarLCI() throws Exception {

    }

}
