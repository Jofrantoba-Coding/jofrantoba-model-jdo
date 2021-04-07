/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jofrantoba.model.jdo.daoentity.testentityindiantmongo.daoamistad;

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
public class TestInsertEstadoAmistad {

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
    void createEntity1() throws UnknownException {
        EstadoAmistad entity = new EstadoAmistad();
        entity.setId("A");
        entity.setDescripcion("ACTIVO");
        entity.setIsPersistente(Boolean.TRUE);
        entity.setVersion(1L);
        DaoEstadoAmistad dao = new DaoEstadoAmistad();
        dao.saveOrUpdate(entity);
    }

    @Test
    void createEntity2() throws UnknownException {
        EstadoAmistad entity = new EstadoAmistad();
        entity.setId("E");
        entity.setDescripcion("ELIMINADO");
        entity.setVersion(1L);
        entity.setIsPersistente(Boolean.TRUE);
        DaoEstadoAmistad dao = new DaoEstadoAmistad();
        dao.saveOrUpdate(entity);
    }

    @Test
    void createEntity3() throws UnknownException {
        EstadoAmistad entity = new EstadoAmistad();
        entity.setId("B");
        entity.setDescripcion("BLOQUEADO");
        entity.setIsPersistente(Boolean.TRUE);
        entity.setVersion(1L);
        DaoEstadoAmistad dao = new DaoEstadoAmistad();
        dao.saveOrUpdate(entity);
    }
    
}
