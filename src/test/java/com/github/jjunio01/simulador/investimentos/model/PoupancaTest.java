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
import org.junit.Ignore;

/**
 *
 * @author Jose Junio
 */
public class PoupancaTest {

    @Test
    public void testCalcularRendimentosPoupanca() {

        InvestPoupanca poupanca = new InvestPoupanca();
        poupanca.setPeriodo(1);
        poupanca.setValor(Investimento.formatarNumero(new BigDecimal("1000")));
        poupanca.calcularRendimentos();
        BigDecimal valor = Investimento.formatarNumero(new BigDecimal("1000"));
        BigDecimal valorAtualizado;
        BigDecimal taxaTR = Investimento.formatarTaxa(new BigDecimal("0.0"));
        BigDecimal indiceRendimento;
        BigDecimal taxaSelic = Investimento.formatarNumero(new BigDecimal("7.5"));
        BigDecimal taxaVariavel;
        //taxaVariável - Se taxaSelic > 8.5% a.a = 0.5% a.m.
        //tavaVariável - Se taxaSelic <= 8.5% a.a. = taxaSelic * 0.7 capitalizado ao mês
        taxaVariavel = Investimento.formatarTaxa(taxaSelic.multiply(new BigDecimal("0.7")));
        //Capitalizando a taxa ao mês
        taxaVariavel = Investimento.formatarTaxa(taxaVariavel.divide(new BigDecimal("100")));
        taxaVariavel = Investimento.formatarTaxa(taxaVariavel.add(new BigDecimal("1")));
        BigDecimal tempo = (new BigDecimal("1.0000000").divide(
                new BigDecimal("12.0000000"), BigDecimal.ROUND_UP)).abs(new MathContext(4));
        taxaVariavel = new BigDecimal(Math.pow(taxaVariavel.floatValue(), tempo.floatValue()));
        taxaVariavel = Investimento.formatarTaxa(taxaVariavel.subtract(new BigDecimal("1")));
        //Calculando índice de rendimento
        indiceRendimento = Investimento.formatarTaxa(taxaTR.add(taxaVariavel));
        //Atualizando o valor
        valorAtualizado = valor.multiply(indiceRendimento).pow(1).add(valor);
        Assert.assertEquals(poupanca.getValorAtualizado(), Investimento.formatarNumero(valorAtualizado));
    }

    //@Test
    public void testTaxaAdicional() {
        BigDecimal taxaSelicTest = Investimento.formatarTaxa(new BigDecimal("7.5"));
        BigDecimal taxaAdicional;
        taxaAdicional = Investimento.formatarTaxa(taxaSelicTest.multiply(new BigDecimal("0.7")));
        taxaAdicional = Investimento.formatarTaxa(taxaAdicional.divide(new BigDecimal("100")));
        //Capitalizando ao mês;
        taxaAdicional = Investimento.formatarTaxa(taxaAdicional.add(new BigDecimal("1")));
        BigDecimal tempo = (new BigDecimal("1.0000000").divide(
                new BigDecimal("12.0000000"), BigDecimal.ROUND_UP)).abs(new MathContext(4));

        taxaAdicional = Investimento.formatarTaxa(new BigDecimal(Math.pow(taxaAdicional.floatValue(), tempo.floatValue())));
        taxaAdicional = Investimento.formatarTaxa(taxaAdicional.subtract(new BigDecimal("1")));

        InvestPoupanca poupanca = new InvestPoupanca();
        System.out.println(taxaAdicional);
        Assert.assertEquals(poupanca.getTaxaAdicional(), taxaAdicional);

    }

    @Test
    public void testPoupancaValoZero() {

        BigDecimal valorZero = new BigDecimal("0.00000000");
        InvestPoupanca poupanca = new InvestPoupanca();
        poupanca.setPeriodo(1);
        poupanca.setValor(valorZero);
        poupanca.calcularRendimentos();
        valorZero = valorZero.setScale(2, RoundingMode.CEILING);
        Assert.assertEquals(poupanca.getValorAtualizado().setScale(2, RoundingMode.CEILING), valorZero);
    }

    @Test
    public void testFormatarTaxa() {

        BigDecimal taxa = new BigDecimal("8.7");
        taxa = taxa.setScale(8, RoundingMode.CEILING);
        Assert.assertEquals(Investimento.formatarTaxa(taxa), taxa);
    }

    //@Test
    public void testPoupancaValorNegativo() {

    }
}
