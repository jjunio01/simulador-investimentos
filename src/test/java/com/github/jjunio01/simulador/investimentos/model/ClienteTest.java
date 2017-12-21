/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jjunio01.simulador.investimentos.model;

import com.github.jjunio01.simulador.investimentos.model.dao.ClienteDao;
import com.github.jjunio01.simulador.investimentos.util.ErroSistema;
import java.util.Date;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Jose Junio
 */
public class ClienteTest {

    @Test
    public void testVerificarSeClienteFoiCadastrado() throws ErroSistema {

        Cliente clienteNull = ClienteDao.getInstance().read("000");
        Assert.assertNull(clienteNull);
        Cliente clienteTest = new Cliente("Teste", "000", new Date(), "email", new Usuario("loginTest", "senha"));
        ClienteDao.getInstance().create(clienteTest);
        Cliente clienteBd = ClienteDao.getInstance().read("000");
        Assert.assertEquals(clienteTest.getNome(), clienteBd.getNome());
        Assert.assertEquals(clienteTest.getEmail(), clienteBd.getEmail());
        Assert.assertEquals(clienteTest.getUsuario().getLogin(), clienteBd.getUsuario().getLogin());
        Assert.assertEquals(clienteTest.getUsuario().getSenha(), clienteBd.getUsuario().getSenha());
        ClienteDao.getInstance().delete("000");

    }

}
