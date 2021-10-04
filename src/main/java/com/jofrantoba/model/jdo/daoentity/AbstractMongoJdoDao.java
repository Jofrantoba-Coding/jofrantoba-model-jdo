    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jofrantoba.model.jdo.daoentity;

import com.jofrantoba.model.jdo.shared.UnknownException;
import java.io.Serializable;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;
import lombok.Data;
import lombok.extern.log4j.Log4j2;


/**
 *
 * @author jona
 * @param <T>
 */
@Log4j2
@Data
public abstract class AbstractMongoJdoDao<T extends Serializable> extends  AbstractGeneralJdoDao<T>{
    
    @Override
    public void saveOrUpdate(T entity) throws UnknownException {
        PersistenceManager pm = null;
        try {           
            pm = this.getCurrentPm();            
            pm.makePersistent(entity);            
        } catch (Exception ex) {            
            throw throwsException(ex, false);
        } finally {
            closeConnection(pm);
        }
    }
    
    @Override
    public Integer insertBulk(List<T> entitys) throws UnknownException {  
        PersistenceManager pm = null;
        try {            
            pm = this.getCurrentPm();                            
            pm.setIgnoreCache(true);                      
            return pm.makeInsertBulk(entitys);
        } catch (Exception ex) {              
            throw throwsException(ex, false);
        } finally {
            closeConnection(pm);
        }
    }
    
    @Override
    public void saveOrUpdateList(List<T> entitys) throws UnknownException {
        PersistenceManager pm = null;
        try {            
            pm = this.getCurrentPm();
            pm.setDetachAllOnCommit(false);
            pm.setIgnoreCache(true);                             
            pm.makePersistentAll(entitys);            
        } catch (Exception ex) {            
            throw throwsException(ex, false);
        } finally {
            closeConnection(pm);
        }
    }
    
    @Override
    public T saveOrUpdateReturn(T entity, boolean isDetach) throws UnknownException {
        PersistenceManager pm = null;
        try {            
            pm = this.getCurrentPm();            
            if (isDetach) {
                pm.setDetachAllOnCommit(true);
            } else {                
                pm.setDetachAllOnCommit(false);
            }            
            return pm.makePersistent(entity);
        } catch (Exception ex) {            
            throw throwsException(ex, false);
        } finally {
            closeConnection(pm);
        }
    }
    
    @Override
    public Long updateBulk(String fieldsValuesUpdate,String filter,Object[] valuesParam)throws UnknownException {
        Query query = null;
        PersistenceManager pm = null;
        try {            
            pm = this.getCurrentPm();
            StringBuilder strJdoql = new StringBuilder();
            strJdoql.append("UPDATE").append(" ");
            strJdoql.append(clazz.getCanonicalName()).append(" ").append("SET").append(" ");
            strJdoql.append(fieldsValuesUpdate).append(" ");
            strJdoql.append("WHERE").append(" ");
            strJdoql.append(filter);            
            query = pm.newQuery(strJdoql.toString());            
            if(valuesParam!=null){
                return (Long) query.executeWithArray(valuesParam);
            }            
            return (Long) query.execute();
        } catch (Exception ex) {            
            throw throwsException(ex, false);
        } finally {
            closeConnection(pm);
        }
    }
    
    @Override
    public Long delete(Object id, String fieldId) throws UnknownException {        
        Query query = null;
        PersistenceManager pm = null;
        try {            
            pm = this.getCurrentPm();            
            StringBuilder strJdoql = new StringBuilder();
            strJdoql.append("DELETE FROM").append(" ");
            strJdoql.append(clazz.getCanonicalName()).append(" ");
            strJdoql.append("WHERE").append(" ");
            strJdoql.append(fieldId).append("==");
            if (id.getClass().getSimpleName().equalsIgnoreCase("String")) {
                strJdoql.append("'").append(id).append("'");
            } else {
                strJdoql.append(id);
            }
            query = pm.newQuery(strJdoql.toString());
            return (Long) query.execute();
        } catch (Exception ex) {            
            throw throwsException(ex, false);
        } finally {
            closeConnection(pm);
        }
    }
    
    @Override
    public Long deleteBulkFilter(String filter,Object[] valuesParam) throws UnknownException {
        Query query = null;
        PersistenceManager pm = null;
        try {            
            pm = this.getCurrentPm();        
            StringBuilder strJdoql = new StringBuilder();
            strJdoql.append("DELETE FROM").append(" ");
            strJdoql.append(clazz.getCanonicalName()).append(" ");
            strJdoql.append("WHERE").append(" ");
            strJdoql.append(filter);
            query = pm.newQuery(strJdoql.toString());
            if(valuesParam!=null){
                return (Long) query.executeWithArray(valuesParam);
            }            
            return (Long) query.execute();            
        } catch (Exception ex) {            
            throw throwsException(ex, false);
        } finally {
            closeConnection(pm);
        }
    }
    
}
