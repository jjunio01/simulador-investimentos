<<<<<<< HEAD:src/main/java/com/github/jjunio01/simulador/investimentos/model/dao/DaoBancoMySql.java
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jjunio01.simulador.investimentos.model.dao;

import com.github.jjunio01.simulador.investimentos.util.ErroSistema;
import com.github.jjunio01.simulador.investimentos.util.Message;
import javax.faces.application.FacesMessage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.JDBCConnectionException;

/**
 *
 * @author Jose Junio
 */
public class DaoBancoMySql {

    private static DaoBancoMySql instance;
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private DaoBancoMySql() throws ErroSistema {
        this.sessionFactory = abrirSession();
    }

    //Obtém uma instancia única do DaoBancoMySql
    public static DaoBancoMySql getInstance() throws ErroSistema {

        if (instance == null) {
            instance = new DaoBancoMySql();
        }
        return instance;
    }

    //Abre sessão para conexão com o banco.
    public SessionFactory abrirSession() throws ErroSistema {

        SessionFactory factory = null;
        try {
            factory = new Configuration().configure().buildSessionFactory();
            Session session = factory.openSession();
        } catch (JDBCConnectionException erroAbrirSession) {
            Message.getInstance().adicionarMensagem(
                    null, "Não foi possível abrir a conexão com o banco de dados", FacesMessage.SEVERITY_INFO);
            throw new ErroSistema(
                    "Não foi possível abrir a conexão com o banco de dados", erroAbrirSession);
        }
        return factory;
    }

    //Inicia uma transação com o banco
    public static Session iniciarTransacao() throws ErroSistema {
        //Abre uma sessão de conexão com o banco
        Session session = getInstance().abrirSession().openSession();
        try {
            //Inicia uma transação
            session.beginTransaction();
        } catch (NullPointerException erroIniciarTransacao) {
            erroIniciarTransacao.getStackTrace();
        } catch (JDBCConnectionException erroIniciarTransacao) {
            erroIniciarTransacao.printStackTrace();
        }
        //Retorna uma sessão aberta e com uma transação iniciada
        return session;

    }

    //Recupera a sessão e encerra a transação iniciadas e fecha a conexão.
    public static void fecharTransacao(Session session) throws ErroSistema {

        try {
            //Encerra a transação da sessão recuperada e efetua commit
            session.getTransaction().commit();
        } catch (NullPointerException erroFecharTransacao) {
            erroFecharTransacao.printStackTrace();
        }
        //Fecha a conexão com o banco.
        session.close();

    }

}
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jjunio01.simulador.investimentos.model.dao;

import com.github.jjunio01.simulador.investimentos.util.ErroSistema;
import com.github.jjunio01.simulador.investimentos.util.Message;
import javax.faces.application.FacesMessage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.JDBCConnectionException;

/**
 *
 * @author Jose Junio
 */
public class DaoBancoMySql {

    private static DaoBancoMySql instance;
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private DaoBancoMySql() throws ErroSistema {
        this.sessionFactory = abrirSession();
    }

    //Obtém uma instancia única do DaoBancoMySql
    public static DaoBancoMySql getInstance() throws ErroSistema {

        if (instance == null) {
            instance = new DaoBancoMySql();
        }
        return instance;
    }

    //Abre sessão para conexão com o banco.
    public SessionFactory abrirSession() throws ErroSistema {

        SessionFactory factory = null;
        try {
            factory = new Configuration().configure().buildSessionFactory();
            Session session = factory.openSession();
        } catch (JDBCConnectionException erroAbrirSession) {
            Message.getInstance().adicionarMensagem(
                    null, "Não foi possível abrir a conexão com o banco de dados", FacesMessage.SEVERITY_INFO);
            throw new ErroSistema(
                    "Não foi possível abrir a conexão com o banco de dados", erroAbrirSession);
        }
        return factory;
    }

    //Inicia uma transação com o banco
    public static Session iniciarTransacao() throws ErroSistema {
        //Abre uma sessão de conexão com o banco
        Session session = getInstance().abrirSession().openSession();
        try {
            //Inicia uma transação
            session.beginTransaction();
        } catch (NullPointerException erroIniciarTransacao) {
            erroIniciarTransacao.getStackTrace();
        } catch (JDBCConnectionException erroIniciarTransacao) {
            erroIniciarTransacao.printStackTrace();
        }
        //Retorna uma sessão aberta e com uma transação iniciada
        return session;

    }

    //Recupera a sessão e encerra a transação iniciadas e fecha a conexão.
    public static void fecharTransacao(Session session) throws ErroSistema {

        try {
            //Encerra a transação da sessão recuperada e efetua commit
            session.getTransaction().commit();
        } catch (NullPointerException erroFecharTransacao) {
            erroFecharTransacao.printStackTrace();
        }
        //Fecha a conexão com o banco.
        session.close();

    }

}
>>>>>>> 8c34a6d7b0524189876c835b1677e41dcc3c9808:src/main/java/com/github/jjunio01/simulador/investimentos/model/dao/DaoBancoMySql.java
