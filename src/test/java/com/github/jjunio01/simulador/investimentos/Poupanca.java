/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jjunio01.simulador.investimentos;

import com.github.jjunio01.simulador.investimentos.controller.ControllerInvestimento;
import com.github.jjunio01.simulador.investimentos.model.InvestPoupanca;
import com.github.jjunio01.simulador.investimentos.util.ErroSistema;
import org.junit.Test;

/**
 *
 * @author Jose Junio
 */
public class Poupanca {

    @Test
    public void testarRendimentoPoupancaNaoVazio() throws ErroSistema {
        InvestPoupanca poupanca = new InvestPoupanca();
        poupanca.setPeriodo(30);
        poupanca.setValor(200);
        ControllerInvestimento controller = new ControllerInvestimento();
        controller.setPoupanca(poupanca);
        controller.calcularRendimentos(poupanca);
    }

    @Test
    public void testarValorRendimentoPoupanca() {

    }

}
