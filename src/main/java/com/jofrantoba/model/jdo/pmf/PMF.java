/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jofrantoba.model.jdo.pmf;


import com.jofrantoba.model.jdo.pmf.connection.ConnectionPropertiesMemHsqldb;
import com.jofrantoba.model.jdo.pmf.connection.ConnectionPropertiesMongo;
import com.jofrantoba.model.jdo.pmf.connection.ConnectionPropertiesOracle;
import java.util.HashMap;
import java.util.Properties;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;
import lombok.extern.log4j.Log4j2;

/**
 *
 * @author jona
 */
@Log4j2
public final class PMF {
    public static final boolean IGNORECACHE=true;
    public static final Integer DATASTOREREADTIMEOUTMILLIS=1000;
    public static final Integer DATASTOREWRITETIMEOUTMILLIS=1000;
    private static final String SCHEMASTATIC="indiant";
    //private static final ConnectionPropertiesOracle propsStatic = new ConnectionPropertiesOracle("192.168.1.4", 1521, "orcl", "sysdecorprueba", "sysdecorprueba");
    //private static final ConnectionPropertiesMemHsqldb propsStatic = new ConnectionPropertiesMemHsqldb("jofrantoba", "SA","");
    private static final ConnectionPropertiesMongo propsStatic = new ConnectionPropertiesMongo("indiant", "jofrantoba","lvDmek9ERF2qbGww");
    private static final PMF pmf = new PMF();    
    private final HashMap<String, PersistenceManagerFactory> mapPMF = new HashMap();

    private PMF() {        
        getPMF(SCHEMASTATIC, propsStatic.getProperties());
    }
    
    public PersistenceManagerFactory getPMFStatic() {        
        return mapPMF.get(SCHEMASTATIC);
    }
    
    public void destroyPMFStatic() {
        if (mapPMF.get(SCHEMASTATIC) != null) {
            mapPMF.get(SCHEMASTATIC).close();
            mapPMF.remove(SCHEMASTATIC);           
        }

    }

    public static PMF getClassPMF() {                
        return pmf;
    }

    public PersistenceManagerFactory getPMF(String publickey, Properties props) {
        if (mapPMF.get(publickey) == null) {
            PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory(props);                        
            mapPMF.put(publickey, pmf);
        }
        return mapPMF.get(publickey);
    }    
    
    public PersistenceManagerFactory getPMF(String publickey) {        
        return mapPMF.get(publickey);
    }       
    
    

    public void destroyPMF(String publickey) {
        if (mapPMF.get(publickey) != null) {
            mapPMF.get(publickey).close();
            mapPMF.remove(publickey);
        }

    }        
    
    public HashMap<String, PersistenceManagerFactory> getMapPMF(){
        return mapPMF;
    }    

}
