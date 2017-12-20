/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jjunio01.simulador.investimentos.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Jose Junio
 */
@Entity
public class Investimento implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    private BigDecimal valor;
    private int periodo;
    private BigDecimal rendimentos;
    private BigDecimal indiceRendimento;
    private BigDecimal valorAtualizado;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAcesso;
    private String tipo;

    public Investimento() {

    }

    public void calcularRendimentos() {

    }

    public Integer getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        if (valor.signum() != -1) {
            this.valor = valor;
        } else {
            throw new IllegalArgumentException("Valor Negativo");
        }
    }

    public int getPeriodo() {

        return periodo;
    }

    public void setPeriodo(int periodo) {
        if (periodo >= 0) {
            this.periodo = periodo;
        } else {
            throw new IllegalArgumentException("Período Negativo");
        }
    }

    public BigDecimal getRendimentos() {
        return rendimentos;
    }

    public void setRendimentos(BigDecimal rendimentos) {
        this.rendimentos = rendimentos;
    }

    public BigDecimal getIndiceRendimento() {
        return indiceRendimento;
    }

    public void setIndiceRendimento(BigDecimal indiceRendimento) {
        this.indiceRendimento = indiceRendimento;
    }

    public BigDecimal getValorAtualizado() {
        setValorAtualizado();
        return valorAtualizado;
    }

    public void setValorAtualizado(BigDecimal valorAtualizado) {
        this.valorAtualizado = valorAtualizado;
    }

    private void setValorAtualizado() {

        this.valorAtualizado = formatarNumero(this.getValor().add(this.rendimentos));
    }

    public Date getDataAcesso() {
        return dataAcesso;
    }

    public void setDataAcesso(Date dataAcesso) {
        this.dataAcesso = dataAcesso;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public static BigDecimal formatarNumero(BigDecimal numero) {

        return numero.setScale(2, RoundingMode.CEILING);

    }

    public static BigDecimal formatarTaxa(BigDecimal taxa) {

        return taxa.setScale(8, RoundingMode.CEILING);
    }

}
