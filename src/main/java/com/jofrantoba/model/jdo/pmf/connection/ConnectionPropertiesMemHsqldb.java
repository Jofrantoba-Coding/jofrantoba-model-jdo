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
public class ConnectionPropertiesMemHsqldb extends AbstractConnectionProperties{
    
    public ConnectionPropertiesMemHsqldb(            
            String nameDatabase,
            String userDatabase,
            String passwordDatabase            
            ){                
        super.setNameDatabase(nameDatabase);
        super.setUserDatabase(userDatabase);
        super.setPasswordDatabase(passwordDatabase);    
        super.setDriver(ProviderDatabase.HYPERSQL.getDriver());
        super.setProviderDatabase(ProviderDatabase.HYPERSQL.getNameProvider());        
        super.setUrlConnection("jdbc:hsqldb:mem:"+nameDatabase);
    }
    
    @Override
    public Properties getProperties() {        
        Properties props=new Properties();
        props.setProperty("datanucleus.ConnectionDriverName", super.getDriver());
        props.setProperty("datanucleus.ConnectionURL", super.getUrlConnection());
        props.setProperty("datanucleus.ConnectionUserName", super.getUserDatabase());
        props.setProperty("datanucleus.ConnectionPassword", super.getPasswordDatabase());
        props.setProperty("datanucleus.connection.nontx.releaseAfterUse", "false");
        props.setProperty("datanucleus.schema.autoCreateAll","true");
        props.setProperty("javax.jdo.option.Mapping","hsql");        
        return props;
    }
    
}
