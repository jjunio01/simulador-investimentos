/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jjunio01.simulador.investimentos.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import javax.persistence.Entity;

/**
 *
 * @author Jose Junio
 */
@Entity
public class InvestPoupanca extends Investimento implements Serializable {

    private BigDecimal taxaTr = new BigDecimal("0.0000903");
    private BigDecimal taxaSelic = new BigDecimal("10.15");
    private BigDecimal taxaAdicional;

    public InvestPoupanca() {
    }

    @Override
    public void calcularRendimentos() {
        //Calcula os juros mensais do valor, utilizando juros compostos.
        if (super.getPeriodo() >= 30) {
            MathContext mc = new MathContext(8);
            this.setRendimentos(this.getValor().multiply(
                    getIndiceRendimento()));
        }

    }

    public BigDecimal getTaxaTr() {
        return taxaTr;
    }

    public void setTaxaTr(BigDecimal taxaTr) {
        this.taxaTr = taxaTr;
    }

    public BigDecimal getTaxaSelic() {
        return taxaSelic;
    }

    public void setTaxaSelic(BigDecimal taxaSelic) {
        this.taxaSelic = taxaSelic;
    }

    public BigDecimal getTaxaAdicional() {
        setTaxaAdicional();
        return taxaAdicional;
    }

    public void setTaxaAdicional() {
        //Atualiza a taxa de rendimento adicional de acordo com a Taxa Selic Vigente
        if (taxaSelic.compareTo(new BigDecimal("8.5")) > 0) {
            this.taxaAdicional = new BigDecimal("0.0053");
        } else {
            this.taxaAdicional = getTaxaSelic().multiply(new BigDecimal("0.7"));
        }
    }

    public int periodo() {
        setPeriodo();
        return super.getPeriodo();
    }

    public void setPeriodo() {
        //Atualiza o período de forma que os rendimentos sejam calculados
        //a cada 30 dias.
        if (super.getPeriodo() < 30) {
            this.setRendimentos(new BigDecimal("0"));
        } else if (super.getPeriodo() == 30) {
            super.setPeriodo(1);
        } else {
            super.setPeriodo(super.getPeriodo() / 30);
        }
    }

    @Override
    public BigDecimal getIndiceRendimento() {
        //Retorna o índice de rendimento que é formado por:
        //Taxa TR e pela taxa adicional que é calulado de acordo com a SELIC
        setIndiceRendimento(getTaxaAdicional().add(this.taxaTr));
        return super.getIndiceRendimento();
    }

    @Override
    public BigDecimal getValorAtualizado() {
        setValorAtualizado(this.getValor().add(this.getRendimentos()));
        return super.getValorAtualizado();
    }

}
