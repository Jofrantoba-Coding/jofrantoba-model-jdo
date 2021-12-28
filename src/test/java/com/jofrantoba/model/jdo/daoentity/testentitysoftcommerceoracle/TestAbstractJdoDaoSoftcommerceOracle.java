/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jofrantoba.model.jdo.daoentity.testentitysoftcommerceoracle;

import com.jofrantoba.model.jdo.daoentity.TestBaseDao;
import com.jofrantoba.model.jdo.pmf.PMF;
import org.junit.jupiter.api.Test;

/**
 *
 * @author jona
 */
public class TestAbstractJdoDaoSoftcommerceOracle extends TestBaseDao{
    
    @Test
    void testConnection(){
        System.out.println(PMF.getClassPMF().getPMFStatic().getPersistenceManager().getServerDate());
    }
}
