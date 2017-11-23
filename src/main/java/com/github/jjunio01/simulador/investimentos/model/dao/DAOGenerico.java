/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jjunio01.simulador.investimentos.model.dao;

import com.github.jjunio01.simulador.investimentos.util.ErroSistema;
import java.util.List;

/**
 *
 * @author Jose Junio
 */
public interface DAOGenerico<T> {

    public void create(T t) throws ErroSistema;

    public void update(T t) throws ErroSistema;

    public T read(String c) throws ErroSistema;

    public void delete(String txt) throws ErroSistema;

    public List<T> readALL() throws ErroSistema;

}
