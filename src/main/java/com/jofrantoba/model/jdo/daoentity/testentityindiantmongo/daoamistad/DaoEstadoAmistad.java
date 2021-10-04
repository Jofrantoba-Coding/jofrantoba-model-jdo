/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jofrantoba.model.jdo.daoentity.testentityindiantmongo.daoamistad;

import com.jofrantoba.model.jdo.daoentity.AbstractMongoJdoDao;
import com.jofrantoba.model.jdo.pmf.PMF;

/**
 *
 * @author jona
 */
public class DaoEstadoAmistad extends AbstractMongoJdoDao<EstadoAmistad> 
        implements InterDaoEstadoAmistad{
    public DaoEstadoAmistad(){
        super();
        this.setClazz(EstadoAmistad.class);       
        this.setPmf(PMF.getClassPMF().getPMFStatic());
    }
}
