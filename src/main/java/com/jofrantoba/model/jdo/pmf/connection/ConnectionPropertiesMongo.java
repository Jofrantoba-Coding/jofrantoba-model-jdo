/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jofrantoba.model.jdo.pmf.connection;

import java.util.Properties;

/**
 *
 * @author jona
 */
public class ConnectionPropertiesMongo extends AbstractConnectionProperties{
    
    public ConnectionPropertiesMongo(            
            String nameDatabase,
            String userDatabase,
            String passwordDatabase            
            ){                
        super.setNameDatabase(nameDatabase);
        super.setUserDatabase(userDatabase);
        super.setPasswordDatabase(passwordDatabase);    
        super.setDriver(ProviderDatabase.MONGO.getDriver());
        super.setProviderDatabase(ProviderDatabase.MONGO.getNameProvider());         
        super.setUrlConnection("mongodb:/"+nameDatabase);
    }
    
    @Override
    public Properties getProperties() {        
        Properties props=new Properties();
        //props.setProperty("datanucleus.ConnectionDriverName", super.getDriver());
        props.setProperty("javax.jdo.PersistenceManagerFactoryClass", "org.datanucleus.api.jdo.JDOPersistenceManagerFactory");
        props.setProperty("datanucleus.ConnectionURL", super.getUrlConnection());
        props.setProperty("datanucleus.ConnectionUserName", super.getUserDatabase());
        props.setProperty("datanucleus.ConnectionPassword", super.getPasswordDatabase());
        props.setProperty("datanucleus.schema.autoCreateAll", "true");
        props.setProperty("datanucleus.validateConstraints", "false");
        props.setProperty("datanucleus.validateTables", "false");
        props.setProperty("datanucleus.query.jdoql.allowAll","true");
        return props;
    }
    
}
