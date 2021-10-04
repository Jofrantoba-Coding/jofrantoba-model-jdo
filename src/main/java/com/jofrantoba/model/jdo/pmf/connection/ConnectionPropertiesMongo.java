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
        //super.setUrlConnection("mongodb:/"+nameDatabase);      
        //super.setUrlConnection("mongodb:clustersanboxindiant.qmitb.mongodb.net/indiant");
        //super.setUrlConnection("mongodb:clustersanboxindiant-shard-00-00.qmitb.mongodb.net/indiant,clustersanboxindiant-shard-00-01.qmitb.mongodb.net,clustersanboxindiant-shard-00-02.qmitb.mongodb.net");
        //super.setUrlConnection("mongodb://jofrantoba:lvDmek9ERF2qbGww@clustersanboxindiant-shard-00-00.qmitb.mongodb.net:27017,clustersanboxindiant-shard-00-01.qmitb.mongodb.net:27017,clustersanboxindiant-shard-00-02.qmitb.mongodb.net:27017/indiant?ssl=true&replicaSet=atlas-tdibxy-shard-0&authSource=admin&retryWrites=true&w=majority");
        //datanucleus.ConnectionURL=mongodb:[{server}][/{dbName}] [,{server2}[,server3}]]        
        //super.setUrlConnection("mongodb+srv://jofrantoba:lvDmek9ERF2qbGww@clustersanboxindiant.qmitb.mongodb.net/"+nameDatabase+"?retryWrites=true&w=majority");//connection atlas mongo
        //super.setUrlConnection("mongodb+srv://jofrantoba:lvDmek9ERF2qbGww@clustersanboxindiant.qmitb.mongodb.net/"+nameDatabase+"?retryWrites=true&w=majority");//connection atlas mongo
        //super.setUrlConnection("mongodb://mtoral:Maej1019@ec2-34-222-157-77.us-west-2.compute.amazonaws.com:27017/"+nameDatabase+"?authSource=admin&readPreference=primary&appname=MongoDB%20Compass&ssl=false");
        //super.setUrlConnection("mongodb://mtoral:Maej1019@ec2-34-222-157-77.us-west-2.compute.amazonaws.com:27017/"+nameDatabase+"?retryWrites=true&w=majority&readPreference=primary&ssl=false");Router 1
        //super.setUrlConnection("mongodb://mtoral:Maej1019@ec2-35-163-220-126.us-west-2.compute.amazonaws.com:27017/"+nameDatabase+"?retryWrites=true&w=majority&readPreference=primary&ssl=false");Router 2
        super.setUrlConnection("mongodb://mtoral:Maej1019@ec2-35-163-220-126.us-west-2.compute.amazonaws.com:27017,ec2-34-222-157-77.us-west-2.compute.amazonaws.com:27017/"+nameDatabase+"?retryWrites=true&w=majority&readPreference=primary&ssl=false");
    }
    
    @Override
    public Properties getProperties() {        
        Properties props=new Properties();
        //props.setProperty("datanucleus.ConnectionDriverName", super.getDriver());
        props.setProperty("javax.jdo.PersistenceManagerFactoryClass", "org.datanucleus.api.jdo.JDOPersistenceManagerFactory");
        props.setProperty("datanucleus.ConnectionURL", super.getUrlConnection());
        //props.setProperty("datanucleus.ConnectionURL", "mongodb:/clustersanboxindiant.qmitb.mongodb.net/indiant");
        //props.setProperty("datanucleus.ConnectionUserName", super.getUserDatabase());
        //props.setProperty("datanucleus.ConnectionPassword", super.getPasswordDatabase());
        props.setProperty("datanucleus.schema.autoCreateAll", "true");
        props.setProperty("datanucleus.validateConstraints", "false");
        props.setProperty("datanucleus.validateTables", "false");
        props.setProperty("datanucleus.query.jdoql.allowAll","true");
        //props.setProperty("datanucleus.mongodb.serverSelectionTimeout", "100000");
        //props.setProperty("datanucleus.mongodb.maxConnectionIdleTime",  "100000");    
        //props.setProperty("datanucleus.mongodb.socketTimeout",  "100000");    
        props.setProperty("datanucleus.mongodb.sslEnabled","false");
        //props.setProperty("datanucleus.mongodb.replicaSetName","atlas-tdibxy-shard-0");
        System.setProperty("java.net.preferIPv4Stack" , "true");
        return props;
    }
    
}
