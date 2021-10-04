/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jofrantoba.model.jdo.daoentity.testentityindiantmongo.daoestadocuenta;

import com.jofrantoba.model.jdo.daoentity.AbstractGeneralJdoDao;
import com.jofrantoba.model.jdo.daoentity.AbstractMongoJdoDao;
import com.jofrantoba.model.jdo.pmf.PMF;

/**
 *
 * @author jona
 */
public class DaoEstadoCuenta extends AbstractMongoJdoDao<EstadoCuenta> 
        implements InterDaoEstadoCuenta{
    public DaoEstadoCuenta(){
        super();
        this.setClazz(EstadoCuenta.class);       
        this.setPmf(PMF.getClassPMF().getPMFStatic());
    }
    
}
