/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jofrantoba.model.jdo.daoentity.testentityindiantmongo.daoestadocuenta;

import com.jofrantoba.model.jdo.daoentity.testentityindiantmongo.daoamistad.*;
import com.jofrantoba.model.jdo.shared.UnknownException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import javax.jdo.JDOHelper;
import lombok.extern.log4j.Log4j2;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

/**
 *
 * @author jona
 */
@Log4j2
public class TestUpdateEstadoCuenta {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }


    @Test
    void updateEntity1() throws UnknownException {
        DaoEstadoCuenta dao = new DaoEstadoCuenta();
        EstadoCuenta entity = dao.findEntity(null, 1, 1L);
        entity.setDescripcion("BLO");
        entity.setVersion(5L);
        System.err.println(JDOHelper.getObjectState(entity));
        dao.saveOrUpdate(entity);
    }   
    @Test
    void updateEntity2() throws UnknownException {                
        DaoEstadoCuenta dao = new DaoEstadoCuenta();
        EstadoCuenta entity=dao.findEntity(null, 1, 2L);
        entity.setDescripcion("ACT");
        entity.setVersion(6L);
        System.err.println(JDOHelper.getObjectState(entity));
        dao.saveOrUpdate(entity);
    }
    
    @Test
    void updateEntity3() throws UnknownException {                
        DaoEstadoCuenta dao = new DaoEstadoCuenta();
        EstadoCuenta entity=dao.findEntity(null, 1, 3L);
        entity.setDescripcion("ELI");
        entity.setVersion(7L);
        System.err.println(JDOHelper.getObjectState(entity));
        dao.saveOrUpdate(entity);
    }
     

}
