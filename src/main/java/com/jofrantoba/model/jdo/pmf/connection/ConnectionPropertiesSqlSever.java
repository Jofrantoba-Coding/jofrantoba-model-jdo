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
public class ConnectionPropertiesSqlSever  extends AbstractConnectionProperties{
    
    public ConnectionPropertiesSqlSever(
            String host,
            int port,
            String nameDatabase,
            String userDatabase,
            String passwordDatabase){
        super.setHost(host);
        super.setPort(port);
        super.setNameDatabase(nameDatabase);
        super.setUserDatabase(userDatabase);
        super.setPasswordDatabase(passwordDatabase);    
        super.setDriver(ProviderDatabase.SQLSERVER.getDriver());
        super.setProviderDatabase(ProviderDatabase.SQLSERVER.getNameProvider());
        super.setUrlConnection("jdbc:sqlserver://"+host+":"+port+";DatabaseName="+nameDatabase);
    }

    @Override
    public Properties getProperties() {
        Properties props=new Properties();
        props.setProperty("datanucleus.ConnectionDriverName", super.getDriver());
        props.setProperty("datanucleus.ConnectionURL", super.getUrlConnection());
        props.setProperty("datanucleus.ConnectionUserName", super.getUserDatabase());
        props.setProperty("datanucleus.ConnectionPassword", super.getPasswordDatabase());
        props.setProperty("datanucleus.connection.nontx.releaseAfterUse", "false");
        props.setProperty("datanucleus.schema.autoCreateTables","true");
        props.setProperty("datanucleus.NontransactionalRead","true");
        props.setProperty("datanucleus.NontransactionalWrite","true");
        props.setProperty("datanucleus.RetainValues","true");
        return props;
    }
    
}
