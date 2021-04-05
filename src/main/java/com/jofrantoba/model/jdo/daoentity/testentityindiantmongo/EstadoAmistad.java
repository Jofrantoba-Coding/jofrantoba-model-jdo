/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jofrantoba.model.jdo.daoentity.testentityindiantmongo;

import java.io.Serializable;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

/**
 *
 * @author jona
 */
@Data
@Log4j2
@PersistenceCapable(detachable = "true")
public class EstadoAmistad implements Serializable {
    @PrimaryKey
    @Persistent    
    private String idEstadoAmistad;    
    @Persistent
    private String descripcion;
    @Persistent
    private Long version;    
    @Persistent
    private Boolean isPersistente;
    @NotPersistent
    private String operacion;

}
