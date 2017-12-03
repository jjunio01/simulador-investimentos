/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jjunio01.simulador.investimentos;

import com.github.jjunio01.simulador.investimentos.model.InvestLCI;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Jose Junio
 */
public class InvestLci {

    @Test
    public void testarValorAtualizadoLci() {

        InvestLCI lci = new InvestLCI();
        lci.setPeriodo(90);
        lci.setValor(new BigDecimal("1000"));
        lci.calcularRendimentos();
        BigDecimal valor = new BigDecimal("1000");
        //0.0053(Taxa Adicional) + 0.0000903(TR)
        BigDecimal taxa = new BigDecimal("0.0000903").add(new BigDecimal("0.0053"));
        BigDecimal resultadoTeste = valor.multiply(taxa).add(valor);
        Assert.assertEquals(lci.getValorAtualizado(), resultadoTeste);

    }

}
