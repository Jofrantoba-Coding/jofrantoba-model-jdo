/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jofrantoba.model.jdo.daoentity.testentityindiantmongo.daoamistad;

import com.jofrantoba.model.jdo.daoentity.testentityindiantmongo.daoamistad.DaoEstadoAmistad;
import com.jofrantoba.model.jdo.daoentity.testentityindiantmongo.daoamistad.EstadoAmistad;
import com.jofrantoba.model.jdo.shared.UnknownException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import lombok.extern.log4j.Log4j2;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

/**
 *
 * @author jona
 */
@Log4j2
public class TestUpdateEstadoAmistad {

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
        DaoEstadoAmistad dao = new DaoEstadoAmistad();
        EstadoAmistad entity = dao.findEntity(null, 1, "B");
        entity.setVersion(5L);
        dao.saveOrUpdate(entity);
    }   
    @Test
    void updateEntity2() throws UnknownException {                
        DaoEstadoAmistad dao = new DaoEstadoAmistad();
        EstadoAmistad entity=dao.findEntity(null, 1, "A");
        entity.setVersion(6L);
        dao.saveOrUpdate(entity);
    }
    
    @Test
    void updateEntity3() throws UnknownException {                
        DaoEstadoAmistad dao = new DaoEstadoAmistad();
        EstadoAmistad entity=dao.findEntity(null, 1, "E");
        entity.setVersion(7L);
        dao.saveOrUpdate(entity);
    }
     

}
