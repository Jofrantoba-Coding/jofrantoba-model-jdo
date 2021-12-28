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
public class ConnectionPropertiesOracle extends AbstractConnectionProperties{
    
    public ConnectionPropertiesOracle(
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
        super.setDriver(ProviderDatabase.ORACLE.getDriver());
        super.setProviderDatabase(ProviderDatabase.ORACLE.getNameProvider());
        super.setUrlConnection("jdbc:oracle:thin:@//"+host+":"+port+"/"+nameDatabase);        
    }
    
    @Override
    public Properties getProperties() {
        Properties props=new Properties();
        props.setProperty("javax.jdo.PersistenceManagerFactoryClass", "org.datanucleus.api.jdo.JDOPersistenceManagerFactory");
        props.setProperty("javax.jdo.option.ServerTimeZoneID","America/Lima");                    
        props.setProperty("datanucleus.ConnectionDriverName", super.getDriver());
        props.setProperty("datanucleus.ConnectionURL", super.getUrlConnection());
        props.setProperty("datanucleus.ConnectionUserName", super.getUserDatabase());
        props.setProperty("datanucleus.ConnectionPassword", super.getPasswordDatabase());        
        props.setProperty("datanucleus.transaction.nontx.write","false");  
        props.setProperty("javax.jdo.option.NontransactionalRead","true");                    
        props.setProperty("javax.jdo.option.NontransactionalWrite","false");
        props.setProperty("javax.jdo.option.TransactionIsolationLevel","read-committed");                            
        props.setProperty("datanucleus.connection.singleConnectionPerExecutionContext", "true");               
        props.setProperty("datanucleus.transaction.isolation", "read-committed");        
        props.setProperty("datanucleus.transaction.nontx.read", "true");        
        props.setProperty("datanucleus.transaction.nontx.write", "false");
        props.setProperty("datanucleus.persistenceByReachabilityAtCommit", "true");     
        props.setProperty("datanucleus.connection.nontx.releaseAfterUse", "false");
        props.setProperty("datanucleus.query.jdoql.allowAll","true");
        props.setProperty("datanucleus.query.sql.allowAll","true");
        /*Apagado por rendimiento*/
        props.setProperty("datanucleus.metadata.allowXML", "true");         
        props.setProperty("datanucleus.metadata.supportORM", "true");                 
        props.setProperty("datanucleus.schema.autoCreateAll", "true");
        props.setProperty("datanucleus.schema.autoCreateTables", "true");
        props.setProperty("datanucleus.schema.autoCreateColumns", "true");
        props.setProperty("datanucleus.schema.autoCreateConstraints", "true");
        /*Desactivar en producción*/
        props.setProperty("datanucleus.schema.validateTables", "true");
        props.setProperty("datanucleus.schema.validateConstraints", "true");
        props.setProperty("datanucleus.schema.validateColumns", "true");
        props.setProperty("datanucleus.rdbms.CheckExistTablesOrViews", "true");
        props.setProperty("datanucleus.rdbms.initializeColumnInfo", "ALL");//None
        //props.setProperty("datanucleus.NontransactionalRead","true");
        //props.setProperty("datanucleus.NontransactionalWrite","false");                       
        //props.setProperty("datanucleus.RetainValues","true");
        //props.setProperty("javax.jdo.option.RetainValues","true");
        //props.setProperty("datanucleus.RestoreValues","false");
        //props.setProperty("javax.jdo.option.RestoreValues","false");        
        //props.setProperty("javax.jdo.option.Optimistic","false");
        //props.setProperty("javax.jdo.option.IgnoreCache","false");
        //props.setProperty("javax.jdo.option.DetachAllOnCommit","false");    
        //props.setProperty("javax.jdo.option.CopyOnAttach","true");                    
        //props.setProperty("javax.jdo.option.ReadOnly","false");                                                      
        return props;
    }
    
}
