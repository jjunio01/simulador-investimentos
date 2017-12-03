/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jjunio01.simulador.investimentos.model;

import java.math.BigDecimal;
import javax.persistence.Entity;

/**
 *
 * @author Jose Junio
 */
@Entity
public class InvestCDB extends Investimento {

    private BigDecimal taxaIR;
    private final BigDecimal cdi = new BigDecimal("10.14");
    private BigDecimal percentCDI = new BigDecimal("90");
    private BigDecimal rendimentoLiquido;
    private BigDecimal iof;

    public InvestCDB() {
    }

    @Override
    public void calcularRendimentos() {

        for (int i = 0; i < this.getPeriodo(); i++) {
            setIndiceRendimento(new BigDecimal(1));
            //Calcula o índice de rendimento capitalizados ao dia.
            setIndiceRendimento(getIndiceRendimento().multiply(
                    new BigDecimal(0.003968254).pow(4)));
        }
        //Atualiza o valor dos rendimentos.
        this.setRendimentos(((getIndiceRendimento().subtract(new BigDecimal(1))).multiply(this.getValor())));
        //Atualiza o valor do rendimento líquido.
        rendimentoLiquido = getRendimentos().multiply(new BigDecimal(1).subtract(getTaxaIR()));
        //Atualiza valor atualizado(Valor + Jutos).
        setValorAtualizado(getValor().add(rendimentoLiquido.subtract(getIof())));
    }

    public BigDecimal getTaxaIR() {
        setTaxaIR();
        return taxaIR;
    }

    public void setTaxaIR() {
        if (getPeriodo() <= 126) {
            this.taxaIR = new BigDecimal(0.2250);
        } else if (getPeriodo() > 126 && getPeriodo() <= 252) {
            this.taxaIR = new BigDecimal(0.2000);
        } else if (getPeriodo() > 252 && getPeriodo() <= 504) {
            this.taxaIR = new BigDecimal(0.1750);
        } else if (getPeriodo() > 504) {
            this.taxaIR = new BigDecimal(0.1500);
        }

    }

    public BigDecimal getCdi() {
        return cdi.multiply(getPercentCDI().divide(new BigDecimal(100)));
    }

    public BigDecimal getPercentCDI() {
        return percentCDI;
    }

    public void setPercentCDI(BigDecimal percentCDI) {
        this.percentCDI = percentCDI;
    }

    public BigDecimal getRendimentoLiquido() {

        return rendimentoLiquido;
    }

    public void setRendimentoLiquido(BigDecimal rendimentoLiquido) {
        this.rendimentoLiquido = rendimentoLiquido;
    }

    public BigDecimal getIof() {
        setIof();
        return iof;
    }

    public void setIof() {

        if (getPeriodo() <= 30) {
            this.iof = new BigDecimal(0);
        } else {
            this.iof = new BigDecimal(0);
        }
    }

    @Override
    public BigDecimal getValorAtualizado() {
        setValorAtualizado(this.getValor().add(this.getRendimentoLiquido()));
        return super.getValorAtualizado();
    }

    @Override
    public void setValorAtualizado(BigDecimal valorAtualizado) {
        super.setValorAtualizado(valorAtualizado);
    }

}
