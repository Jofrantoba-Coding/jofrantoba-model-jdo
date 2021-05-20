/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jofrantoba.model.jdo.daoentity;


import com.jofrantoba.model.jdo.shared.UnknownException;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

/**
 *
 * @author jona
 * @param <T>
 */
public interface InterCrud<T extends Serializable>{        
    Collection<T> allFieldsEntity(PersistenceManagerFactory pmf,PersistenceManager pm,Long limitInicio,Long limitFin,boolean loadSubClase,String[] members,Integer maxFetchDepth,String filter,Map paramValue,String order)throws UnknownException;
    Collection<T> allFieldsEntity(Long limitInicio,Long limitFin,boolean loadSubClase,String[] members,Integer maxFetchDepth,String filter,Map paramValue,String order)throws UnknownException;   
    Collection<T> allFieldsEntity(PersistenceManagerFactory pmf,PersistenceManager pm,boolean loadSubClase,String[] members, Integer maxFetchDepth,String filter,Map paramValue,String order)throws UnknownException;    
    Collection<T> allFieldsEntity(boolean loadSubClase,String[] members, Integer maxFetchDepth,String filter,Map paramValue,String order)throws UnknownException;    
    Collection<T> allFieldsEntity(PersistenceManagerFactory pmf,PersistenceManager pm)throws UnknownException;    
    Collection<T> allFieldsEntity()throws UnknownException;    
    T allFieldsEntity(PersistenceManagerFactory pmf,PersistenceManager pm,boolean loadSubClase,String[] members, Integer maxFetchDepth,Long idEntity,String fieldId)throws UnknownException;    
    T allFieldsEntity(boolean loadSubClase,String[] members, Integer maxFetchDepth,Long idEntity,String fieldId)throws UnknownException;    
    T allFieldsEntity(PersistenceManagerFactory pmf,PersistenceManager pm,boolean loadSubClase,String[] members, Integer maxFetchDepth,String idEntity,String fieldId,boolean isIdTypeDataCharacter)throws UnknownException;    
    T allFieldsEntity(boolean loadSubClase,String[] members, Integer maxFetchDepth,String idEntity,String fieldId,boolean isIdTypeDataCharacter)throws UnknownException;    
    Collection<T> customFieldEntity(PersistenceManagerFactory pmf,PersistenceManager pm,boolean loadSubClase,String[] members, Integer maxFetchDepth,String fields,String filter,Map paramValue,String order)throws UnknownException;
    Collection<T> customFieldEntity(boolean loadSubClase,String[] members, Integer maxFetchDepth,String fields,String filter,Map paramValue,String order)throws UnknownException;
    Collection<T> customFieldEntity(PersistenceManagerFactory pmf,PersistenceManager pm,boolean loadSubClase,String[] members, Integer maxFetchDepth,Long limitInicio, Long limitFin,String fields,String filter,Map paramValue,String order)throws UnknownException;
    Collection<T> customFieldEntity(boolean loadSubClase,String[] members, Integer maxFetchDepth,Long limitInicio, Long limitFin,String fields,String filter,Map paramValue,String order)throws UnknownException;    
    T customFieldEntity(PersistenceManagerFactory pmf,PersistenceManager pm,boolean loadSubClase,String[] members, Integer maxFetchDepth,String fields,Long idEntity,String fieldId)throws UnknownException;    
    T customFieldEntity(boolean loadSubClase,String[] members, Integer maxFetchDepth,String fields,Long idEntity,String fieldId)throws UnknownException;    
    T customFieldEntity(PersistenceManagerFactory pmf,PersistenceManager pm,boolean loadSubClase,String[] members, Integer maxFetchDepth,String fields,String idEntity,String fieldId,boolean isIdTypeDataCharacter)throws UnknownException;    
    T customFieldEntity(boolean loadSubClase,String[] members, Integer maxFetchDepth,String fields,String idEntity,String fieldId,boolean isIdTypeDataCharacter)throws UnknownException;    
    T findEntity(String[] members, Integer maxFetchDepth,Object id)throws UnknownException;    
    T findEntity(PersistenceManagerFactory pmf,PersistenceManager pm,String[] members, Integer maxFetchDepth,Object id)throws UnknownException;    
    T findEntityIdChar(String[] members, Integer maxFetchDepth,String fieldId,String id)throws UnknownException;    
    T findEntityIdChar(PersistenceManagerFactory pmf,PersistenceManager pm,String[] members, Integer maxFetchDepth,String fieldId,String id)throws UnknownException;        
    void saveOrUpdate(T entity)throws UnknownException;
    T saveOrUpdateReturn(T entity,boolean isDetach)throws UnknownException;
    void saveOrUpdate(PersistenceManager pm,T entity)throws UnknownException;
    T saveOrUpdateReturn(PersistenceManager pm,T entity,boolean isDetach)throws UnknownException;
    void saveOrUpdate(PersistenceManager pm,Transaction tx,T entity)throws UnknownException;
    T saveOrUpdateReturn(PersistenceManager pm,Transaction tx,T entity)throws UnknownException;    
    Long delete(PersistenceManager pm,Object id,String fieldId)throws UnknownException;    
    Long delete(PersistenceManager pm,Transaction tx,Object id,String fieldId)throws UnknownException;    
    Long delete(Object id,String fieldId)throws UnknownException;    
    Long deleteBulkFilter(String filter,Object[] valuesParam)throws UnknownException;
    Long deleteBulkFilter(PersistenceManager pm,String filter,Object[] valuesParam)throws UnknownException;
    Long deleteBulkFilter(PersistenceManager pm,Transaction tx,String filter,Object[] valuesParam)throws UnknownException;
    Long updateBulk(String fieldsValuesUpdate,String filter,Object[] valuesParam)throws UnknownException;
    Long updateBulk(PersistenceManager pm,String fieldsValuesUpdate,String filter,Object[] valuesParam)throws UnknownException;
    Long updateBulk(PersistenceManager pm,Transaction tx,String fieldsValuesUpdate,String filter,Object[] valuesParam)throws UnknownException;
    void saveOrUpdateList(List<T> entitys) throws UnknownException;
    /*Map<?,T> allKeyValue(String fieldKey,String... arrayFields)throws UnknownException;*/
}
