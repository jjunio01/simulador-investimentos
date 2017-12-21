/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jjunio01.simulador.investimentos.model;

import com.github.jjunio01.simulador.investimentos.util.Message;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.faces.application.FacesMessage;
import javax.persistence.Entity;

/**
 *
 * @author Jose Junio
 */
@Entity
public class InvestLCI extends Investimento implements Serializable {

    private final BigDecimal cdi = Investimento.formatarTaxa(new BigDecimal("6.89"));
    private BigDecimal percentCDI = new BigDecimal("80");

    public InvestLCI() {
        this.percentCDI = Investimento.formatarTaxa(this.percentCDI);
        super.setRendimentos(Investimento.formatarNumero(new BigDecimal("0")));
    }

    @Override
    public void calcularRendimentos() {
        //Verifica se o périodo é inferior a 90 dias.
        if (getPeriodo() < 90) {
            Message.getInstance().adicionarMensagem(
                    null, "Período para o LCI deve ser igual ou superior a 90 dias", FacesMessage.SEVERITY_WARN);
            this.setValorAtualizado(getValor().add(getRendimentos()));
            return;
        }

        for (int i = 0; i < this.getPeriodo(); i++) {
            setIndiceRendimento(new BigDecimal(1));
            //Calcula o índice de rendimento capitalizados ao dia
            setIndiceRendimento(getIndiceRendimento().multiply(
                    getCdi().divide(new BigDecimal(100)).add(new BigDecimal(1)).pow(1)));
        }
        //Atualiza o valor dos rendimentos;
        this.setRendimentos(((getIndiceRendimento().subtract(new BigDecimal(1))).multiply(this.getValor())));
        this.setValorAtualizado(getValor().add(getRendimentos()));

    }

    public BigDecimal getCdi() {
        return cdi;
    }

    public BigDecimal getPercentCDI() {
        return percentCDI;
    }

    public void setPercentCDI(BigDecimal percentCDI) {
        this.percentCDI = percentCDI;
    }

    /*
    ValorNomialAtualizado = ValorNominalEmissao x C
     */
 /*
    C = produtorio . (TR/100 + 1) ^ periodo
     */
 /*
    J = VNA x (FatJuros -1)
     */
 /*
    FatJuros = [ (i/100 + 1) ^ prazo/360
     */
}
