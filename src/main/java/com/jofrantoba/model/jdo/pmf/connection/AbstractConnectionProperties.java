/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jofrantoba.model.jdo.pmf.connection;

import java.io.Serializable;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

/**
 *
 * @author jona
 */

@Log4j2
@Data
public abstract class AbstractConnectionProperties implements ConnectionProperties,Serializable{
    private String host;
    private int port;
    private String nameDatabase;
    private String userDatabase;
    private String passwordDatabase;
    private String urlConnection;
    private String driver;
    private String providerDatabase;   
    
}
