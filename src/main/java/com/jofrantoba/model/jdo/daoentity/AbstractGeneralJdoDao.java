    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jofrantoba.model.jdo.daoentity;

import com.jofrantoba.model.jdo.shared.UnknownException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.jdo.Extent;
import javax.jdo.FetchGroup;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
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
public abstract class AbstractGeneralJdoDao<T extends Serializable> implements InterCrud<T> {
    private PersistenceManagerFactory pmf;
    private PersistenceManager pm;    
    protected Class<T> clazz;

    public PersistenceManager getCurrentPm(){        
        if(pm==null || pm.isClosed()){
            pm=pmf.getPersistenceManager();
        }
        return pm;
    }
    
    @Override
    public Object detachObject(Object entity)throws UnknownException{
        return getCurrentPm().detachCopy(entity);
    }
    
    @Override
    public T detach(T entity)throws UnknownException{
        return getCurrentPm().detachCopy(entity);
    }
    
    @Override
    public void closePm() throws UnknownException{
        closeConnection(this.pm);
    }

    @Override
    public void saveOrUpdate(T entity) throws UnknownException {
        PersistenceManager pm = null;
        Transaction tx = null;
        try {           
            pm = this.getCurrentPm();
            tx = pm.currentTransaction();
            tx.begin();            
            pm.makePersistent(entity);             
        } catch (Exception ex) {
            rollback(tx);
            throw throwsException(ex, false);
        } finally {
            try {
                commit(tx);
            } catch (Exception ex) {
                rollback(tx);
                throw throwsException(ex, false);
            }
        }
    }
    
    @Override
    public Integer insertBulk(List<T> entitys) throws UnknownException {  
        PersistenceManager pm = null;
        Transaction tx = null;
        try {            
            pm = this.getCurrentPm();                            
            pm.setIgnoreCache(true);                             
            tx = pm.currentTransaction();
            tx.begin();
            return pm.makeInsertBulk(entitys);
        } catch (Exception ex) {  
            rollback(tx);
            throw throwsException(ex, false);
        } finally {
            try {
                commit(tx);
            } catch (Exception ex) {
                rollback(tx);
                throw throwsException(ex, false);
            }
        }
    }
    
    @Override
    public void saveOrUpdateList(List<T> entitys) throws UnknownException {
        PersistenceManager pm = null;
        Transaction tx = null;
        try {            
            pm = this.getCurrentPm();
            pm.setDetachAllOnCommit(false);
            pm.setIgnoreCache(true);                 
            tx = pm.currentTransaction();            
            tx.setOptimistic(false);            
            tx.begin();    
            tx.setRetainValues(false);            
            pm.makePersistentAll(entitys);            
        } catch (Exception ex) {
            rollback(tx);
            throw throwsException(ex, false);
        } finally {
            try {
                commit(tx);
            } catch (Exception ex) {
                rollback(tx);
                throw throwsException(ex, false);
            }
        }
    }

    @Override
    public T saveOrUpdateReturn(T entity, boolean isDetach) throws UnknownException {
        PersistenceManager pm = null;
        Transaction tx = null;
        try {            
            pm = this.getCurrentPm();
            tx = pm.currentTransaction();
            if (isDetach) {
                pm.setDetachAllOnCommit(true);
            } else {
                tx.setRetainValues(true);
            }
            tx.begin();
            return pm.makePersistent(entity);
        } catch (Exception ex) {
            rollback(tx);
            throw throwsException(ex, false);
        } finally {
            try {
                commit(tx);
            } catch (Exception ex) {
                rollback(tx);
                throw throwsException(ex, false);
            }
        }
    }

    @Override
    public void saveOrUpdate(PersistenceManager pm, T entity) throws UnknownException {        
        try {            
            pm.makePersistent(entity);
        } catch (Exception ex) {            
            throw throwsException(ex, false);
        } 
    }

    @Override
    public T saveOrUpdateReturn(PersistenceManager pm,Transaction tx, T entity, boolean isDetach) throws UnknownException {        
        try {    
            if (isDetach) {
                pm.setDetachAllOnCommit(isDetach);
            } else {
                tx.setRetainValues(!isDetach);
            }           
            return pm.makePersistent(entity);
        } catch (Exception ex) {            
            throw throwsException(ex, false);
        } 
    }
    
    @Override
    public T saveOrUpdateReturnDetachCopy(PersistenceManager pm, T entity) throws UnknownException {
        try {
            return pm.detachCopy(pm.makePersistent(entity));
        } catch (Exception ex) {
            throw throwsException(ex, false);
        }
    }
    
    @Override
    public Long updateBulk(String fieldsValuesUpdate,String filter,Object[] valuesParam)throws UnknownException {
        Transaction tx = null;
        Query query = null;
        PersistenceManager pm = null;
        try {            
            pm = this.getCurrentPm();
            tx = pm.currentTransaction();
            tx.begin();
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
            rollback(tx, query);
            throw throwsException(ex, false);
        } finally {
            try {
                commit(tx, query);
            } catch (Exception ex) {
                rollback(tx, query);
                throw throwsException(ex, false);
            }
        }
    }        
    
    @Override
    public Long updateBulk(PersistenceManager pm,String fieldsValuesUpdate,String filter,Object[] valuesParam)throws UnknownException {    
        Query query = null;    
        try {            
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
            closeQuery(query);
        }
    }           

    @Override
    public Long delete(Object id, String fieldId) throws UnknownException {
        Transaction tx = null;
        Query query = null;
        PersistenceManager pm = null;
        try {            
            pm = this.getCurrentPm();
            tx = pm.currentTransaction();
            tx.begin();
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
            rollback(tx, query);
            throw throwsException(ex, false);
        } finally {
            try {
                commit(tx, query);
            } catch (Exception ex) {
                rollback(tx, query);
                throw throwsException(ex, false);
            }
        }
    }
       
    
    @Override
    public Long delete(PersistenceManager pm,Object id, String fieldId) throws UnknownException {        
        Query query = null;        
        try {            
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
            closeQuery(query);
        }
    }
    
    @Override
    public Long deleteBulkFilter(String filter,Object[] valuesParam) throws UnknownException {
        Transaction tx = null;
        Query query = null;
        PersistenceManager pm = null;
        try {            
            pm = this.getCurrentPm();
            tx = pm.currentTransaction();
            tx.begin();
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
            rollback(tx, query);
            throw throwsException(ex, false);
        } finally {
            try {
                commit(tx, query);
            } catch (Exception ex) {
                rollback(tx, query);
                throw throwsException(ex, false);
            }
        }
    }
        
    
    @Override
    public Long deleteBulkFilter(PersistenceManager pm,String filter,Object[] valuesParam) throws UnknownException {        
        Query query = null;        
        try {            
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
            closeQuery(query);
        }
    }

    @Override
    public Collection<T> allFields(PersistenceManager pm, Long limitInicio, Long limitFin, boolean loadSubClase, String[] members, Integer maxFetchDepth, String filter, Map paramValue, String order) throws UnknownException {
        Query<T> query = null;
        try {
            Extent extent = pm.getExtent(clazz, loadSubClase);
            query = pm.newQuery(extent);
            filter(query, filter, paramValue);
            order(query, order);
            return allFieldsEntity(pm.getPersistenceManagerFactory(), pm, query, limitInicio, limitFin, members, maxFetchDepth);
        } catch (Exception ex) {
            throw throwsException(ex, false);
        } finally {
            closeQuery(query);
        }
    }

    @Override
    public Collection<T> allFields(Long limitInicio, Long limitFin, boolean loadSubClase, String[] members, Integer maxFetchDepth, String filter, Map paramValue, String order) throws UnknownException {
        Query<T> query = null;
        PersistenceManager pm = null;
        try {            
            pm = this.getCurrentPm();
            Extent extent = pm.getExtent(clazz, loadSubClase);
            query = pm.newQuery(extent);
            filter(query, filter, paramValue);
            order(query, order);
            return allFieldsEntity(pmf, pm, query, limitInicio, limitFin, members, maxFetchDepth);
        } catch (Exception ex) {
            throw throwsException(ex, false);
        } finally {
            closeQuery(query);
        }
    }

    @Override
    public Collection<T> allFields(PersistenceManager pm, boolean loadSubClase, String[] members, Integer maxFetchDepth, String filter, Map paramValue, String order) throws UnknownException {
        Query<T> query = null;
        try {
            Extent extent = pm.getExtent(clazz, loadSubClase);
            query = pm.newQuery(extent);
            filter(query, filter, paramValue);
            order(query, order);
            return allFieldsEntity(pm.getPersistenceManagerFactory(), pm, query, members, maxFetchDepth);
        } catch (Exception ex) {
            throw throwsException(ex, false);
        } finally {
            closeQuery(query);
        }
    }

    @Override
    public Collection<T> allFields(boolean loadSubClase, String[] members, Integer maxFetchDepth, String filter, Map paramValue, String order) throws UnknownException {
        Query<T> query = null;
        PersistenceManager pm = null;
        try {            
            pm = this.getCurrentPm();
            Extent extent = pm.getExtent(clazz, loadSubClase);
            query = pm.newQuery(extent);
            filter(query, filter, paramValue);
            order(query, order);
            return allFieldsEntity(pmf, pm, query, members, maxFetchDepth);
        } catch (Exception ex) {
            throw throwsException(ex, true);
        } finally {
            closeQuery(query);
        }
    }
    
    @Override
    public Collection<T> allFields(PersistenceManager pm) throws UnknownException {
        Query<T> query = null;        
        try {                       
            Extent extent = pm.getExtent(clazz, true);
            query = pm.newQuery(extent);                        
            return allFieldsEntity(pm.getPersistenceManagerFactory(), pm, query, null, 1);
        } catch (Exception ex) {
            throw throwsException(ex, false);
        } finally {
            closeQuery(query);
        }
    }
    
    @Override
    public Collection<T> allFields() throws UnknownException {
        Query<T> query = null;
        PersistenceManager pm = null;
        try {            
            pm = this.getCurrentPm();
            Extent extent = pm.getExtent(clazz, true);
            query = pm.newQuery(extent);                        
            return allFieldsEntity(pmf, pm, query, null, 1);
        } catch (Exception ex) {
            throw throwsException(ex, true);
        } finally {
            closeQuery(query);
        }
    }

    @Override
    public Collection<T> customField(boolean loadSubClase, String[] members, Integer maxFetchDepth, Long limitInicio, Long limitFin, String fields, String filter, Map paramValue, String order) throws UnknownException {
        Query<T> query = null;
        PersistenceManager pm = null;
        try {            
            pm = this.getCurrentPm();
            Extent extent = pm.getExtent(clazz, loadSubClase);
            query = pm.newQuery(extent);
            filter(query, filter, paramValue);
            order(query, order);
            List<T> lista = new ArrayList();
            lista.addAll(customFieldsEntity(pmf, pm, query, limitInicio, limitFin, members, maxFetchDepth, fields));
            return lista;
        } catch (Exception ex) {
            throw throwsException(ex, false);
        } finally {
            closeQuery(query);
        }
    }

    @Override
    public Collection<T> customField(boolean loadSubClase, String[] members, Integer maxFetchDepth, String fields, String filter, Map paramValue, String order) throws UnknownException {
        Query<T> query = null;
        PersistenceManager pm = null;
        try {            
            pm = this.getCurrentPm();
            Extent extent = pm.getExtent(clazz, loadSubClase);
            query = pm.newQuery(extent);
            filter(query, filter, paramValue);
            order(query, order);
            List<T> lista = new ArrayList();
            lista.addAll(customFieldsEntity(pmf, pm, query, members, maxFetchDepth, fields));
            return lista;
        } catch (Exception ex) {
            throw throwsException(ex, false);
        } finally {
            closeQuery(query);
        }
    }

    @Override
    public Collection<T> customField(PersistenceManager pm, boolean loadSubClase, String[] members, Integer maxFetchDepth, Long limitInicio, Long limitFin, String fields, String filter, Map paramValue, String order) throws UnknownException {
        Query<T> query = null;
        try {
            Extent extent = pm.getExtent(clazz, loadSubClase);
            query = pm.newQuery(extent);
            filter(query, filter, paramValue);
            order(query, order);
            List<T> lista = new ArrayList();
            lista.addAll(customFieldsEntity(pm.getPersistenceManagerFactory(), pm, query, limitInicio, limitFin, members, maxFetchDepth, fields));
            return lista;
        } catch (Exception ex) {
            throw throwsException(ex, false);
        } finally {
            closeQuery(query);
        }
    }

    @Override
    public Collection<T> customField(PersistenceManager pm, boolean loadSubClase, String[] members, Integer maxFetchDepth, String fields, String filter, Map paramValue, String order) throws UnknownException {
        Query<T> query = null;
        try {
            Extent extent = pm.getExtent(clazz, loadSubClase);
            query = pm.newQuery(extent);
            filter(query, filter, paramValue);
            order(query, order);
            List<T> lista = new ArrayList();
            lista.addAll(customFieldsEntity(pm.getPersistenceManagerFactory(), pm, query, members, maxFetchDepth, fields));
            return lista;
        } catch (Exception ex) {
            throw throwsException(ex, false);
        } finally {
            closeQuery(query);
        }
    }

    @Override
    public T allFields(PersistenceManager pm, boolean loadSubClase, String[] members, Integer maxFetchDepth, Long idEntity, String fieldId) throws UnknownException {
        Query<T> query = null;
        try {
            Extent extent = pm.getExtent(clazz, loadSubClase);
            query = pm.newQuery(extent);
            return fieldsEntity(pm.getPersistenceManagerFactory(), pm, query, members, maxFetchDepth, idEntity, fieldId, false, null);
        } catch (Exception ex) {
            throw throwsException(ex, false);
        } finally {
            closeQuery(query);
        }
    }

    @Override
    public T allFields(boolean loadSubClase, String[] members, Integer maxFetchDepth, Long idEntity, String fieldId) throws UnknownException {
        Query<T> query = null;
        PersistenceManager pm = null;
        try {            
            pm = this.getCurrentPm();
            Extent extent = pm.getExtent(clazz, loadSubClase);
            query = pm.newQuery(extent);
            return fieldsEntity(pmf, pm, query, members, maxFetchDepth, idEntity, fieldId, false, null);
        } catch (Exception ex) {
            throw throwsException(ex, false);
        } finally {
            closeQuery(query);
        }
    }

    @Override
    public T allFields(PersistenceManager pm, boolean loadSubClase, String[] members, Integer maxFetchDepth, String idEntity, String fieldId, boolean isIdTypeDataCharacter) throws UnknownException {
        Query<T> query = null;
        try {
            Extent extent = pm.getExtent(clazz, loadSubClase);
            query = pm.newQuery(extent);
            return fieldsEntity(pm.getPersistenceManagerFactory(), pm, query, members, maxFetchDepth, idEntity, fieldId, isIdTypeDataCharacter, null);
        } catch (Exception ex) {
            throw throwsException(ex, false);
        } finally {
            closeQuery(query);
        }
    }

    @Override
    public T allFields(boolean loadSubClase, String[] members, Integer maxFetchDepth, String idEntity, String fieldId, boolean isIdTypeDataCharacter) throws UnknownException {
        Query<T> query = null;
        PersistenceManager pm = null;
        try {           
            pm = this.getCurrentPm();
            Extent extent = pm.getExtent(clazz, loadSubClase);
            query = pm.newQuery(extent);
            return fieldsEntity(pmf, pm, query, members, maxFetchDepth, idEntity, fieldId, isIdTypeDataCharacter, null);
        } catch (Exception ex) {
            throw throwsException(ex, true);
        } finally {
            closeQuery(query);
        }
    }

    @Override
    public T customField(PersistenceManager pm, boolean loadSubClase, String[] members, Integer maxFetchDepth, String fields, Long idEntity, String fieldId) throws UnknownException {
        Query<T> query = null;
        try {
            Extent extent = pm.getExtent(clazz, loadSubClase);
            query = pm.newQuery(extent);
            return fieldsEntity(pm.getPersistenceManagerFactory(), pm, query, members, maxFetchDepth, idEntity, fieldId, false, fields);
        } catch (Exception ex) {
            throw throwsException(ex, false);
        } finally {
            closeQuery(query);
        }
    }

    @Override
    public T customField(boolean loadSubClase, String[] members, Integer maxFetchDepth, String fields, Long idEntity, String fieldId) throws UnknownException {
        Query<T> query = null;
        PersistenceManager pm = null;
        try {           
            pm = this.getCurrentPm();
            Extent extent = pm.getExtent(clazz, loadSubClase);
            query = pm.newQuery(extent);
            return fieldsEntity(pmf, pm, query, members, maxFetchDepth, idEntity, fieldId, false, fields);
        } catch (Exception ex) {
            throw throwsException(ex, true);
        } finally {
            closeQuery(query);
        }
    }

    @Override
    public T customField(PersistenceManager pm, boolean loadSubClase, String[] members, Integer maxFetchDepth, String fields, String idEntity, String fieldId, boolean isIdTypeDataCharacter) throws UnknownException {
        Query<T> query = null;
        try {
            Extent extent = pm.getExtent(clazz, loadSubClase);
            query = pm.newQuery(extent);
            return fieldsEntity(pm.getPersistenceManagerFactory(), pm, query, members, maxFetchDepth, idEntity, fieldId, isIdTypeDataCharacter, fields);
        } catch (Exception ex) {
            throw throwsException(ex, false);
        } finally {
            closeQuery(query);
        }
    }

    @Override
    public T customField(boolean loadSubClase, String[] members, Integer maxFetchDepth, String fields, String idEntity, String fieldId, boolean isIdTypeDataCharacter) throws UnknownException {
        Query<T> query = null;
        PersistenceManager pm = null;
        try {            
            pm = this.getCurrentPm();
            Extent extent = pm.getExtent(clazz, loadSubClase);
            query = pm.newQuery(extent);
            return fieldsEntity(pmf, pm, query, members, maxFetchDepth, idEntity, fieldId, isIdTypeDataCharacter, fields);
        } catch (Exception ex) {
            throw throwsException(ex, false);
        } finally {
            closeQuery(query);
        }
    }
    
    @Override
    public T find(Object id) throws UnknownException {
        PersistenceManager pm = null;
        try {            
            pm = this.getCurrentPm();            
            return pm.getObjectById(clazz, id);
        } catch (Exception ex) {            
            throw throwsException(ex, true);
        }
    }

    @Override
    public T find(String[] members, Integer maxFetchDepth, Object id) throws UnknownException {
        PersistenceManager pm = null;
        try {            
            pm = this.getCurrentPm();
            membersFetchPlan(members, pmf, pm, maxFetchDepth);
            return pm.detachCopy(pm.getObjectById(clazz, id));
        } catch (Exception ex) {            
            throw throwsException(ex, true);
        }
    }

    @Override
    public T find(PersistenceManager pm, String[] members, Integer maxFetchDepth, Object id) throws UnknownException {
        try {
            membersFetchPlan(members, pm.getPersistenceManagerFactory(), pm, maxFetchDepth);
            return pm.detachCopy(pm.getObjectById(clazz, id));
        } catch (Exception ex) {
            throw throwsException(ex, false);
        }
    }

    @Override
    public T findIdChar(String[] members, Integer maxFetchDepth, String fieldId, String id) throws UnknownException {
        Query<T> query = null;
        PersistenceManager pm = null;
        try {            
            pm = this.getCurrentPm();
            query = pm.newQuery(clazz);
            return fieldsEntityIdChar(pmf, pm, query, members, maxFetchDepth, id, fieldId);
        } catch (Exception ex) {
            throw throwsException(ex, false);
        } finally {
            closeQuery(query);
        }
    }

    @Override
    public T findIdChar(PersistenceManager pm, String[] members, Integer maxFetchDepth, String fieldId, String id) throws UnknownException {
        Query<T> query = null;
        try {
            query = pm.newQuery(clazz);
            return fieldsEntityIdChar(pm.getPersistenceManagerFactory(), pm, query, members, maxFetchDepth, id, fieldId);
        } catch (Exception ex) {
            throw throwsException(ex, false);
        } finally {
            closeQuery(query);
        }
    }

    private Collection<T> customFieldsEntity(PersistenceManagerFactory pmf, PersistenceManager pm, Query<T> query, Long limitInicio, Long limitFin, String[] members, Integer maxFetchDepth, String fields) {
        membersFetchPlan(members, pmf, pm, maxFetchDepth);
        QueryConfiguration.readConfigQuery(query);
        query.setRange(limitInicio, limitFin);
        query.setResult(fields);
        return query.executeResultList(clazz);
    }

    private Collection<T> customFieldsEntity(PersistenceManagerFactory pmf, PersistenceManager pm, Query<T> query, String[] members, Integer maxFetchDepth, String fields) {
        membersFetchPlan(members, pmf, pm, maxFetchDepth);
        QueryConfiguration.readConfigQuery(query);
        query.setResult(fields);
        return query.executeResultList(clazz);
    }

    private Collection<T> allFieldsEntity(PersistenceManagerFactory pmf, PersistenceManager pm, Query<T> query, Long limitInicio, Long limitFin, String[] members, Integer maxFetchDepth) {
        membersFetchPlan(members, pmf, pm, maxFetchDepth);
        QueryConfiguration.readConfigQuery(query);
        query.setRange(limitInicio, limitFin);
        return pm.detachCopyAll(query.executeList());
    }

    private Collection<T> allFieldsEntity(PersistenceManagerFactory pmf, PersistenceManager pm, Query<T> query, String[] members, Integer maxFetchDepth) {
        membersFetchPlan(members, pmf, pm, maxFetchDepth);
        QueryConfiguration.readConfigQuery(query);
        return pm.detachCopyAll(query.executeList());
    }

    private T fieldsEntityIdChar(PersistenceManagerFactory pmf, PersistenceManager pm, Query<T> query, String[] members, Integer maxFetchDepth, String id, String fieldId) {
        membersFetchPlan(members, pmf, pm, maxFetchDepth);
        QueryConfiguration.readConfigQuery(query);
        StringBuilder str = new StringBuilder();
        str.append(fieldId);
        str.append(" == ");
        str.append("'").append(id).append("'");
        query.setFilter(str.toString());
        return pm.detachCopy(query.executeUnique());
    }

    private T fieldsEntity(PersistenceManagerFactory pmf, PersistenceManager pm, Query<T> query, String[] members, Integer maxFetchDepth, Object id, String fieldId, boolean isTypeDataChar, String fields) {
        membersFetchPlan(members, pmf, pm, maxFetchDepth);
        QueryConfiguration.readConfigQuery(query);
        StringBuilder str = new StringBuilder();
        str.append(fieldId);
        if (isTypeDataChar) {
            str.append(".trim()");
        }
        str.append(" == ");
        str.append("paramId");
        query.setFilter(str.toString());
        query.declareParameters(id.getClass().getCanonicalName() + " paramId");
        query.setParameters(id);
        if (fields != null && !fields.isEmpty()) {
            query.setResult(fields);
            return query.executeResultUnique(clazz);
        }
        return pm.detachCopy(query.executeUnique());
    }

    private void membersFetchPlan(String[] members, PersistenceManagerFactory pmf, PersistenceManager pm, Integer maxFetchDepth) {
        if (members != null && members.length > 0) {
            String nameGroup = QueryConfiguration.nameGroup(clazz, members);
            FetchGroup fg = pmf.getFetchGroup(clazz, nameGroup);
            fg.addMembers(members);
            pmf.addFetchGroups(fg);
            pm.getFetchPlan().addGroup(nameGroup);
            pm.getFetchPlan().setMaxFetchDepth(maxFetchDepth == null ? 0 : maxFetchDepth);
        }
    }

    private void filter(Query<T> query, String filter, Map paramValue) {
        if (filter != null && paramValue != null) {
            query.setFilter(filter);
            query.setNamedParameters(paramValue);
        }
    }

    private void order(Query<T> query, String order) {
        if (order != null) {
            query.setOrdering(order);
        }
    }

    private void rollback(PersistenceManager pm, Transaction tx) {
        if (tx != null && tx.isActive()) {
            tx.rollback();
        }
        if (pm != null && !pm.isClosed()) {
            pm.close();
        }
    }

    private void rollback(PersistenceManager pm, Transaction tx, Query query) {
        if (tx != null && tx.isActive()) {
            tx.rollback();
        }
        if (query != null) {
            query.closeAll();
        }
        if (pm != null && !pm.isClosed()) {
            pm.close();
        }
    }
    
    private void rollback(Transaction tx, Query query) {
        if (tx != null && tx.isActive()) {
            tx.rollback();
        }
        if (query != null) {
            query.closeAll();
        }        
    }

    private void rollback(Transaction tx) {
        if (tx != null && tx.isActive()) {
            tx.rollback();
        }
    }

    private void commit(PersistenceManager pm, Transaction tx) {
        if (tx != null && tx.isActive()) {
            tx.commit();
        }
        if (pm != null && !pm.isClosed()) {
            pm.close();
        }
    }
    
    private void commit(Transaction tx, Query query) {
        if (tx != null && tx.isActive()) {
            tx.commit();
        }
        if (query != null) {
            query.closeAll();
        }    
    }

    private void commit(PersistenceManager pm, Transaction tx, Query query) {
        if (tx != null && tx.isActive()) {
            tx.commit();
        }
        if (query != null) {
            query.closeAll();
        }
        if (pm != null && !pm.isClosed()) {
            pm.close();
        }
    }

    private void commit(Transaction tx) {
        if (tx != null && tx.isActive()) {
            tx.commit();
        }
    }

    private void closeQuery(Query<T> query) throws UnknownException {
        try {
            if (query != null) {
                query.closeAll();
            }
        } catch (Exception ex) {
            throw throwsException(ex, false);
        }
    }

    private void closeQueryConnection(PersistenceManager pm, Query<T> query) throws UnknownException {
        try {
            if (query != null) {
                query.closeAll();
            }
            if (pm != null && !pm.isClosed()) {
                pm.close();
            }
        } catch (Exception ex) {
            throw throwsException(ex, false);
        }
    }
        
    protected void closeConnection(PersistenceManager pm) throws UnknownException {
        try {
            if (pm != null && !pm.isClosed()) {
                pm.close();
            }
        } catch (Exception ex) {
            throw throwsException(ex, false);
        }
    }

    protected UnknownException throwsException(Exception ex, boolean trace) {
        UnknownException ue = new UnknownException(ex.getClass(), ex.getMessage(), ex.getCause(), true, true);
        ue.setStackTrace(ex.getStackTrace());
        ue.traceLog(trace);
        return ue;
    }

}
