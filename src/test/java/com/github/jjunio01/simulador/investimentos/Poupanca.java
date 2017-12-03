/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jjunio01.simulador.investimentos;

import com.github.jjunio01.simulador.investimentos.model.InvestPoupanca;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Jose Junio
 */
public class Poupanca {

    @Test
    public void testarValorAtualizadoDaPoupanca() {

        InvestPoupanca poupanca = new InvestPoupanca();
        poupanca.setPeriodo(30);
        poupanca.setValor(new BigDecimal("1000"));
        poupanca.calcularRendimentos();
        BigDecimal valor = new BigDecimal("1000");
        //0.0053(Taxa Adicional) + 0.0000903(TR)
        BigDecimal taxa = new BigDecimal("0.0000903").add(new BigDecimal("0.0053"));
        BigDecimal resultadoTeste = valor.multiply(taxa).add(valor);
        Assert.assertEquals(poupanca.getValorAtualizado(), resultadoTeste);

    }
}
