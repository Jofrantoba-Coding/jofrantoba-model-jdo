    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jofrantoba.model.jdo.daoentity;

import com.jofrantoba.model.jdo.shared.UnknownException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import java.io.Serializable;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;


/**
 *
 * @author jona
 * @param <T>
 */
@Log4j2
@EqualsAndHashCode(callSuper=false)
@Data
public abstract class AbstractMongoJdoDao<T extends Serializable> extends  AbstractGeneralJdoDao<T> implements InterMongoCrud<T>{
    
    @Override
    public void saveOrUpdate(T entity) throws UnknownException {
        PersistenceManager pm = null;
        try {           
            pm = this.getCurrentPm();            
            pm.makePersistent(entity);            
        } catch (Exception ex) {            
            throw throwsException(ex, true);
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
        PersistenceManager pm = null;
        JsonWriterSettings prettyPrint = JsonWriterSettings.builder().indent(true).build();
        try {            
            pm = this.getCurrentPm();
            MongoDatabase db=(MongoDatabase)pm.getDataStoreConnection().getNativeConnection();            
            MongoCollection<Document> collection = db.getCollection(clazz.getSimpleName());            
            String[] filtros= filter.split("==");
            filtros[1]=filtros[1].replaceAll("'", "");            
            String[] setFields=fieldsValuesUpdate.split(",");
            String[] setField=setFields[0].split("=");
            Bson filterBson = Filters.eq(filtros[0].trim(), filtros[1].trim());
            Bson updateOperation = Updates.set(setField[0], valuesParam[0]);
            UpdateResult updateResult = collection.updateOne(filterBson, updateOperation);
            log.info(collection.find(filterBson).first().toJson(prettyPrint));
            log.info("Update Result:{}",updateResult);                                
            return updateResult.getMatchedCount();
        } catch (Exception ex) {            
            throw throwsException(ex, true);
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
