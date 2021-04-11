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
import lombok.extern.log4j.Log4j2;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

/**
 *
 * @author jona
 */
@Log4j2
public class TestDeleteEstadoCuenta {

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
    void deleteEntity1() throws UnknownException {
        DaoEstadoCuenta dao = new DaoEstadoCuenta();
        dao.delete(1L, "id");
    }

    @Test
    void deleteEntity2() throws UnknownException {
        DaoEstadoCuenta dao = new DaoEstadoCuenta();
        dao.delete(2L, "id");
    }

    @Test
    void deleteEntity3() throws UnknownException {
        DaoEstadoCuenta dao = new DaoEstadoCuenta();
        dao.delete(3L, "id");
    }    
}
