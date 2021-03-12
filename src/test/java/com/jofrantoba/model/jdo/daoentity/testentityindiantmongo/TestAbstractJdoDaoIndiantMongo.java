/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jofrantoba.model.jdo.daoentity.testentityindiantmongo;

import com.jofrantoba.model.jdo.pmf.PMF;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import javax.jdo.PersistenceManager;
import lombok.extern.log4j.Log4j2;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

/**
 *
 * @author jona
 */
@Log4j2
public class TestAbstractJdoDaoIndiantMongo {

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
    void hyperSqlMemConnection() {
        PersistenceManager pm = PMF.getClassPMF().getPMFStatic().getPersistenceManager();
        log.info("Date Server database:{} ", pm.getServerDate());
        log.info("Aduana:{} ", Aduana.class.getCanonicalName());
    }*/
    
    /*@Test
    void createSchema() throws UnknownException {
        JDOPersistenceManagerFactory pmf = (JDOPersistenceManagerFactory) PMF.getClassPMF().getPMFStatic();
        PersistenceNucleusContext ctx = pmf.getNucleusContext();
        Set<String> classNames = new HashSet();        
        classNames.add(Aduana.class.getCanonicalName());
        Properties props = new Properties();
        ((SchemaAwareStoreManager)ctx.getStoreManager()).createSchemaForClasses(classNames, props);
    }*/

    /*@Test
    void createEntity() throws UnknownException {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        Aduana entity = new Aduana();
        entity.setIdAduana(uuid);
        entity.setDescripcion("jofrantoba");
        DaoAduana dao = new DaoAduana();
        dao.saveOrUpdate(entity);
    }*/

   /*@Test
    void createEntity1() throws UnknownException {
        PersistenceManager pm = null;
        Transaction tx = null;
        try {
            DaoAduana dao = new DaoAduana();
            PersistenceManagerFactory pmf = PMF.getClassPMF().getPMFStatic();
            pm = pmf.getPersistenceManager();
            tx = pm.currentTransaction();
            tx.begin();
            pm.setDetachAllOnCommit(true);
            String uuid = UUID.randomUUID().toString().replace("-", "");
            Aduana entity = new Aduana();
            entity.setIdAduana(uuid);
            entity.setDescripcion("jofrantoba");            
            dao.saveOrUpdate(pm,entity);
            tx.commit();
        } catch (UnknownException ex) {
            UnknownException ue = new UnknownException(ex.getClass(), ex.getMessage(), ex.getCause(), true, true);
            ue.setStackTrace(ex.getStackTrace());
            ue.traceLog(true);
        } finally {
            try {
                if (pm != null && !pm.isClosed()) {
                    if (tx != null && tx.isActive()) {
                        tx.rollback();
                    }
                    pm.close();
                }
            } catch (Exception ex) {
                UnknownException ue = new UnknownException(ex.getClass(), ex.getMessage(), ex.getCause(), true, true);
                ue.setStackTrace(ex.getStackTrace());
                ue.traceLog(false);
            }
        }
    }

    @Test
    void listEntity() throws UnknownException {
        DaoAduana dao = new DaoAduana();
        dao.allFieldsEntity();
    }

    @Test
    void listEntity1() throws UnknownException {
        DaoAduana dao = new DaoAduana();
        dao.allFieldsEntity();
    }*/
}
