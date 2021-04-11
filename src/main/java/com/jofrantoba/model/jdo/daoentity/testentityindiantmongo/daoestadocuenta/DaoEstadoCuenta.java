/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jofrantoba.model.jdo.daoentity.testentityindiantmongo.daoestadocuenta;

import com.jofrantoba.model.jdo.daoentity.AbstractJdoDao;

/**
 *
 * @author jona
 */
public class DaoEstadoCuenta extends AbstractJdoDao<EstadoCuenta> 
        implements InterDaoEstadoCuenta{
    public DaoEstadoCuenta(){
        super();
        setClazz(EstadoCuenta.class);        
    }
    
}
