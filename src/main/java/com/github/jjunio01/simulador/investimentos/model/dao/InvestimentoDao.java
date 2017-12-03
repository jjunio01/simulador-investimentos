<<<<<<< HEAD:src/main/java/com/github/jjunio01/simulador/investimentos/model/dao/InvestimentoDao.java
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jjunio01.simulador.investimentos.model.dao;

import com.github.jjunio01.simulador.investimentos.model.InvestCDB;
import com.github.jjunio01.simulador.investimentos.model.InvestLCI;
import com.github.jjunio01.simulador.investimentos.model.InvestPoupanca;
import com.github.jjunio01.simulador.investimentos.util.ErroSistema;
import org.hibernate.Session;

/**
 *
 * @author Jose Junio
 */
public class InvestimentoDao {

    private static InvestimentoDao instance;

    private InvestimentoDao() {
    }

    //Método que cria uma instância única de InvetimentoDao
    public static InvestimentoDao getInstance() {

        if (instance == null) {
            instance = new InvestimentoDao();
        }
        return instance;
    }

    //Método para salvar uma Poupanca
    public void salvar(InvestPoupanca poupanca) throws ErroSistema {
        //Abre uma conexão com o banco e inicia uma trasação
        Session session = DaoBancoMySql.iniciarTransacao();
        //Salva o objeto no banco de dados.
        session.saveOrUpdate(poupanca);
        //Recupera a sessão aberta encerra a transação e fecha a conexão;
        DaoBancoMySql.fecharTransacao(session);
    }

    //Método para salvar uma CDB
    public void salvar(InvestCDB cdb) throws ErroSistema {
        Session session = DaoBancoMySql.iniciarTransacao();
        session.saveOrUpdate(cdb);
        DaoBancoMySql.fecharTransacao(session);
    }

    //Método para salvar uma LCI
    public void salvarLCI(InvestLCI lci) throws ErroSistema {
        Session session = DaoBancoMySql.iniciarTransacao();
        session.saveOrUpdate(lci);
        DaoBancoMySql.fecharTransacao(session);
    }

}
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jjunio01.simulador.investimentos.model.dao;

import com.github.jjunio01.simulador.investimentos.model.InvestCDB;
import com.github.jjunio01.simulador.investimentos.model.InvestLCI;
import com.github.jjunio01.simulador.investimentos.model.InvestPoupanca;
import com.github.jjunio01.simulador.investimentos.util.ErroSistema;
import org.hibernate.Session;

/**
 *
 * @author Jose Junio
 */
public class InvestimentoDao {

    private static InvestimentoDao instance;

    private InvestimentoDao() {
    }

    //Método que cria uma instância única de InvetimentoDAO
    public static InvestimentoDao getInstance() {

        if (instance == null) {
            instance = new InvestimentoDao();
        }
        return instance;
    }

    //Método para salvar uma Poupanca
    public void salvar(InvestPoupanca poupanca) throws ErroSistema {
        //Abre uma conexão com o banco e inicia uma trasação
        Session session = DaoBancoMySql.iniciarTransacao();
        //Salva o objeto no banco de dados.
        session.saveOrUpdate(poupanca);
        //Recupera a sessão aberta encerra a transação e fecha a conexão;
        DaoBancoMySql.fecharTransacao(session);
    }

    //Método para salvar uma CDB
    public void salvar(InvestCDB cdb) throws ErroSistema {
        Session session = DaoBancoMySql.iniciarTransacao();
        session.saveOrUpdate(cdb);
        DaoBancoMySql.fecharTransacao(session);
    }

    //Método para salvar uma LCI
    public void salvarLCI(InvestLCI lci) throws ErroSistema {
        Session session = DaoBancoMySql.iniciarTransacao();
        session.saveOrUpdate(lci);
        DaoBancoMySql.fecharTransacao(session);
    }

}
>>>>>>> 8c34a6d7b0524189876c835b1677e41dcc3c9808:src/main/java/com/github/jjunio01/simulador/investimentos/model/dao/InvestimentoDao.java
