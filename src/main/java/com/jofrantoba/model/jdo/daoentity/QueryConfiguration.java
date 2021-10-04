/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jofrantoba.model.jdo.daoentity;

import com.jofrantoba.model.jdo.pmf.PMF;
import javax.jdo.Query;

/**
 *
 * @author jona
 */
public class QueryConfiguration {
    public static void readConfigQuery(Query query){
        query.extension("datanucleus.query.flushBeforeExecution", "true");       
        query.addExtension("datanucleus.rdbms.query.resultSetConcurrency", "read-only");
        query.addExtension("datanucleus.rdbms.query.resultSetType", "forward-only");
        query.addExtension("datanucleus.rdbms.query.fetchDirection", "forward");
        query.addExtension("datanucleus.query.resultCacheType", "none");
        query.addExtension("datanucleus.cache.level1.type","none");
        query.ignoreCache(true);
        /*No soportadas para MongoDB*/
        //query.datastoreReadTimeoutMillis(PMF.DATASTOREREADTIMEOUTMILLIS);
        //query.extension("datanucleus.query.loadResultsAtCommit", "false");                
    }
    
    public static String nameGroup(Class<?> clazz,String[] members){
        StringBuilder str=new StringBuilder();
        str.append(clazz.getSimpleName());
        for(String item:members){
            str.append(item);
        }        
        return str.toString();
    }
}
