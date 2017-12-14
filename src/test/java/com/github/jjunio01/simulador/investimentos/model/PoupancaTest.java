/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jjunio01.simulador.investimentos.model;

import java.math.MathContext;
import org.junit.Assert;
import org.junit.Test;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Jose Junio
 */
public class PoupancaTest {

    @Test

    public void testarValorAtualizadoDaPoupanca() {

        InvestPoupanca poupanca = new InvestPoupanca();
        poupanca.setPeriodo(1);
        poupanca.setValor(new BigDecimal("1000").abs(new MathContext(9)));
        poupanca.calcularRendimentos();
        BigDecimal valor = new BigDecimal("1000").abs(new MathContext(8));
        BigDecimal valorAtualizado;
        BigDecimal taxaTR = new BigDecimal("0.00000000");
        BigDecimal indiceRendimento;
        BigDecimal taxaSelic = new BigDecimal("7.50000000");
        BigDecimal taxaVariavel;
        //taxaVariável - Se taxaSelic > 8.5% a.a = 0.5% a.m.
        //tavaVariável - Se taxaSelic <= 8.5% a.a. = taxaSelic * 0.7 capitalizado ao mês
        taxaVariavel = taxaSelic.multiply(new BigDecimal("0.70000000")).abs(new MathContext(9));
        //Capitalizando a taxa ao mês
        taxaVariavel = taxaVariavel.divide(new BigDecimal("100.00000000"));
        taxaVariavel = taxaVariavel.add(new BigDecimal("1.00000000"));
        BigDecimal tempo = (new BigDecimal("1.0000000").divide(
                new BigDecimal("12.0000000"), BigDecimal.ROUND_UP)).abs(new MathContext(4));
        taxaVariavel = new BigDecimal(Math.pow(taxaVariavel.floatValue(), tempo.floatValue()));
        taxaVariavel = taxaVariavel.subtract(new BigDecimal("1.00000000"));
        //Calculando índice de rendimento
        indiceRendimento = taxaTR.add(taxaVariavel).abs(new MathContext(9));
        //Atualizando o valor
        valorAtualizado = valor.multiply(indiceRendimento).pow(poupanca.getPeriodo()).add(valor).setScale(2, RoundingMode.CEILING);
        Assert.assertEquals(poupanca.getValorAtualizado().setScale(2, RoundingMode.CEILING), valorAtualizado);
    }

}
