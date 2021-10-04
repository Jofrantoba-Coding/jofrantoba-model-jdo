/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jofrantoba.model.jdo.daoentity.testentityindiantmongo.daoestadocuenta;

import com.jofrantoba.model.jdo.shared.UnknownException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

/**
 *
 * @author jona
 */
@Log4j2
public class TestInsertEstadoCuenta {

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

    /*@Test
    void createEntity1() throws UnknownException {
        EstadoCuenta entity = new EstadoCuenta();
        entity.setId(1L);
        entity.setDescripcion("ACTIVO");
        entity.setIsPersistente(Boolean.TRUE);
        entity.setVersion(1L);
        DaoEstadoCuenta dao = new DaoEstadoCuenta();
        dao.saveOrUpdate(entity);
    }

    @Test
    void createEntity2() throws UnknownException {
        EstadoCuenta entity = new EstadoCuenta();
        entity.setId(2L);
        entity.setDescripcion("ELIMINADO");
        entity.setVersion(1L);
        entity.setIsPersistente(Boolean.TRUE);
        DaoEstadoCuenta dao = new DaoEstadoCuenta();
        dao.saveOrUpdate(entity);
    }

    @Test
    void createEntity3() throws UnknownException {
        EstadoCuenta entity = new EstadoCuenta();
        entity.setId(3L);
        entity.setDescripcion("BLOQUEADO");
        entity.setIsPersistente(Boolean.TRUE);
        entity.setVersion(1L);
        DaoEstadoCuenta dao = new DaoEstadoCuenta();
        dao.saveOrUpdate(entity);
    }*/
    
    /*@Test
    void createEntitys4() throws UnknownException {        
        List<EstadoCuenta> list=new ArrayList();
        for(int i=1;i<=1000000;i++){
            EstadoCuenta entity = new EstadoCuenta();
            entity.setId(Long.valueOf(i));
            entity.setDescripcion("BLOQUEADO "+i);
            entity.setIsPersistente(Boolean.TRUE);
            entity.setVersion(1L);
            list.add(entity);            
        }        
        DaoEstadoCuenta dao = new DaoEstadoCuenta();    
        dao.saveOrUpdateList(list);
    }*/
    
    @Test
    void createEntitys4() throws UnknownException {        
        List<EstadoCuenta> list=new ArrayList();
        DaoEstadoCuenta dao = new DaoEstadoCuenta();
        for(int i=1;i<=1000000;i++){
            EstadoCuenta entity = new EstadoCuenta();
            entity.setId(Long.valueOf(i));
            entity.setDescripcion("BLOQUEADO "+i);
            entity.setIsPersistente(Boolean.TRUE);
            entity.setVersion(1L);
            list.add(entity);            
            if(i%1000==0){
                dao.insertBulk(list);      
                list.removeAll(list);
            }
        }                        
    }            
    
}
