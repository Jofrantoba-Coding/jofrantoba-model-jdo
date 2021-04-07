/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jofrantoba.model.jdo.daoentity.testentityindiantmongo.daoamistad;

import java.io.Serializable;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import lombok.Getter;
import lombok.Setter;
import com.jofrantoba.model.jdo.daoentity.testentityindiantmongo.GlobalEntityPkString;

/**
 *
 * @author jona
 */
@PersistenceCapable(detachable="true")
public class EstadoAmistad extends GlobalEntityPkString implements Serializable{            
    @Persistent    
    @Getter
    @Setter
    private String descripcion;

}
