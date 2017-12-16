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

    private final BigDecimal taxaTr = new BigDecimal("0.0000000");
    private final BigDecimal taxaSelic = new BigDecimal("7.5000000");
    private BigDecimal taxaAdicional;

    public InvestPoupanca() {
    }

    @Override
    public void calcularRendimentos() {
        //Calcula os juros mensais do valor, utilizando juros compostos.
        this.setRendimentos(this.getValor().multiply(
                getIndiceRendimento()).pow(super.getPeriodo()));

    }

    public BigDecimal getTaxaTr() {
        return taxaTr;
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
        if (this.taxaSelic.compareTo(new BigDecimal("8.5000000")) > 0) {
            this.taxaAdicional = new BigDecimal("0.0053");
        } else {
            //Capitalizando a taxa ao mês (im = ((1 + 1a) ^ 1/12 )- 1);
            BigDecimal taxaVariavel;
            taxaVariavel = this.taxaSelic.multiply(new BigDecimal("0.70000000")).abs(new MathContext(9));
            taxaVariavel = taxaVariavel.divide(new BigDecimal("100.00000000"));
            taxaVariavel = taxaVariavel.add(new BigDecimal("1.00000000"));

            BigDecimal tempo = (new BigDecimal("1.0000000").divide(
                    new BigDecimal("12.0000000"), BigDecimal.ROUND_UP)).abs(new MathContext(4));

            taxaVariavel = new BigDecimal(Math.pow(taxaVariavel.floatValue(), tempo.floatValue()));
            taxaVariavel = taxaVariavel.subtract(new BigDecimal("1.00000000"));

            this.taxaAdicional = (taxaVariavel);
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
