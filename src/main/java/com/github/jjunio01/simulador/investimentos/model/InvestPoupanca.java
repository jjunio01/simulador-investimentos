/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jjunio01.simulador.investimentos.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.persistence.Entity;

/**
 *
 * @author Jose Junio
 */
@Entity
public class InvestPoupanca extends Investimento implements Serializable {

    private BigDecimal taxaTr = new BigDecimal("0.0");
    private BigDecimal taxaSelic = new BigDecimal("7.5");
    private BigDecimal taxaAdicional;

    public InvestPoupanca() {
        this.taxaTr = Investimento.formatarTaxa(this.taxaTr);
        this.taxaSelic = Investimento.formatarTaxa(this.taxaSelic);
        super.setRendimentos(Investimento.formatarNumero(new BigDecimal("0")));
    }

    @Override
    public void calcularRendimentos() {
        //Calcula os juros mensais do valor, utilizando juros compostos.
        if (this.getValor().signum() != -1) {
            this.setRendimentos(this.getValor().multiply(
                    getIndiceRendimento()).pow(super.getPeriodo()));
        }

    }

    public BigDecimal getTaxaTr() {
        return taxaTr;
    }

    public void setTaxaTr(BigDecimal taxaTr) {
        this.taxaTr = taxaTr;
    }

    public void setTaxaSelic(BigDecimal taxaSelic) {
        this.taxaSelic = taxaSelic;
    }

    public BigDecimal getTaxaSelic() {
        return taxaSelic;
    }

    public BigDecimal getTaxaAdicional() {
        setTaxaAdicional();
        return taxaAdicional;
    }

    public void setTaxaAdicional() {
        //Atualiza a taxa de rendimento adicional de acordo com a Taxa Selic Vigente
        if (this.taxaSelic.compareTo(Investimento.formatarTaxa(new BigDecimal("8.5"))) > 0) {
            this.taxaAdicional = Investimento.formatarTaxa(new BigDecimal("0.0053"));
        } else {
            //Capitalizando a taxa ao mês (im = ((1 + 1a) ^ 1/12 )- 1);
            BigDecimal taxaVariavel;
            taxaVariavel = Investimento.formatarTaxa(this.taxaSelic.multiply(new BigDecimal("0.7")));
            taxaVariavel = Investimento.formatarTaxa(taxaVariavel.divide(new BigDecimal("100.0")));
            taxaVariavel = Investimento.formatarTaxa(taxaVariavel.add(new BigDecimal("1.0")));

            BigDecimal tempo = (new BigDecimal("1.0000000").divide(
                    new BigDecimal("12.0000000"), BigDecimal.ROUND_UP)).setScale(4, RoundingMode.CEILING);;

            taxaVariavel = new BigDecimal(Math.pow(taxaVariavel.floatValue(), tempo.floatValue()));
            taxaVariavel = Investimento.formatarTaxa(taxaVariavel.subtract(new BigDecimal("1.0")));

            this.taxaAdicional = taxaVariavel;
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
        setIndiceRendimento(Investimento.formatarTaxa(getTaxaAdicional().add(getTaxaTr())));
        return super.getIndiceRendimento();
    }

    /* @Override
    public BigDecimal getValorAtualizado() {
        setValorAtualizado(Investimento.formatarNumero(this.getValor().add(this.getRendimentos())));
        return super.getValorAtualizado();
    }*/
}
