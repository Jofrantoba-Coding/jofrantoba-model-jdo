/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jofrantoba.model.jdo.daoentity.testentityindiantmongo.daoamistad;

import com.jofrantoba.model.jdo.daoentity.AbstractJdoDao;

/**
 *
 * @author jona
 */
public class DaoEstadoAmistad extends AbstractJdoDao<EstadoAmistad> 
        implements InterDaoEstadoAmistad{
    public DaoEstadoAmistad(){
        super();
        setClazz(EstadoAmistad.class);        
    }
}
