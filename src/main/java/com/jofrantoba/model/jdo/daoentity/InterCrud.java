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
import javax.jdo.Transaction;

/**
 *
 * @author jona
 * @param <T>
 */
public interface InterCrud<T extends Serializable>{            
    Collection<T> allFields(PersistenceManager pm,Long limitInicio,Long limitFin,boolean loadSubClase,String[] members,Integer maxFetchDepth,String filter,Map paramValue,String order)throws UnknownException;
    Collection<T> allFields(Long limitInicio,Long limitFin,boolean loadSubClase,String[] members,Integer maxFetchDepth,String filter,Map paramValue,String order)throws UnknownException;   
    Collection<T> allFields(PersistenceManager pm,boolean loadSubClase,String[] members, Integer maxFetchDepth,String filter,Map paramValue,String order)throws UnknownException;    
    Collection<T> allFields(boolean loadSubClase,String[] members, Integer maxFetchDepth,String filter,Map paramValue,String order)throws UnknownException;    
    Collection<T> allFields(PersistenceManager pm)throws UnknownException;    
    Collection<T> allFields()throws UnknownException;    
    T allFields(PersistenceManager pm,boolean loadSubClase,String[] members, Integer maxFetchDepth,Long idEntity,String fieldId)throws UnknownException;    
    T allFields(boolean loadSubClase,String[] members, Integer maxFetchDepth,Long idEntity,String fieldId)throws UnknownException;    
    T allFields(PersistenceManager pm,boolean loadSubClase,String[] members, Integer maxFetchDepth,String idEntity,String fieldId,boolean isIdTypeDataCharacter)throws UnknownException;    
    T allFields(boolean loadSubClase,String[] members, Integer maxFetchDepth,String idEntity,String fieldId,boolean isIdTypeDataCharacter)throws UnknownException;    
    Collection<T> customField(PersistenceManager pm,boolean loadSubClase,String[] members, Integer maxFetchDepth,String fields,String filter,Map paramValue,String order)throws UnknownException;
    Collection<T> customField(boolean loadSubClase,String[] members, Integer maxFetchDepth,String fields,String filter,Map paramValue,String order)throws UnknownException;
    Collection<T> customField(PersistenceManager pm,boolean loadSubClase,String[] members, Integer maxFetchDepth,Long limitInicio, Long limitFin,String fields,String filter,Map paramValue,String order)throws UnknownException;
    Collection<T> customField(boolean loadSubClase,String[] members, Integer maxFetchDepth,Long limitInicio, Long limitFin,String fields,String filter,Map paramValue,String order)throws UnknownException;    
    T customField(PersistenceManager pm,boolean loadSubClase,String[] members, Integer maxFetchDepth,String fields,Long idEntity,String fieldId)throws UnknownException;    
    T customField(boolean loadSubClase,String[] members, Integer maxFetchDepth,String fields,Long idEntity,String fieldId)throws UnknownException;    
    T customField(PersistenceManager pm,boolean loadSubClase,String[] members, Integer maxFetchDepth,String fields,String idEntity,String fieldId,boolean isIdTypeDataCharacter)throws UnknownException;    
    T customField(boolean loadSubClase,String[] members, Integer maxFetchDepth,String fields,String idEntity,String fieldId,boolean isIdTypeDataCharacter)throws UnknownException;    
    T find(Object id)throws UnknownException;    
    T find(String[] members, Integer maxFetchDepth,Object id)throws UnknownException;    
    T find(PersistenceManager pm,String[] members, Integer maxFetchDepth,Object id)throws UnknownException;    
    T findIdChar(String[] members, Integer maxFetchDepth,String fieldId,String id)throws UnknownException;    
    T findIdChar(PersistenceManager pm,String[] members, Integer maxFetchDepth,String fieldId,String id)throws UnknownException;        
    void saveOrUpdate(T entity)throws UnknownException;
    T saveOrUpdateReturn(T entity,boolean isDetach)throws UnknownException;
    void saveOrUpdate(PersistenceManager pm,T entity)throws UnknownException;
    T saveOrUpdateReturn(PersistenceManager pm,Transaction tx,T entity,boolean isDetach)throws UnknownException;    
    T saveOrUpdateReturnDetachCopy(PersistenceManager pm,T entity)throws UnknownException;        
    Long delete(PersistenceManager pm,Object id,String fieldId)throws UnknownException;    
    Long delete(Object id,String fieldId)throws UnknownException;    
    Long deleteBulkFilter(String filter,Object[] valuesParam)throws UnknownException;    
    Long deleteBulkFilter(PersistenceManager pm,String filter,Object[] valuesParam)throws UnknownException;
    Long updateBulk(String fieldsValuesUpdate,String filter,Object[] valuesParam)throws UnknownException;  
    Long updateBulk(PersistenceManager pm,String fieldsValuesUpdate,String filter,Object[] valuesParam)throws UnknownException;
    void saveOrUpdateList(List<T> entitys) throws UnknownException;
    Integer insertBulk(List<T> entitys) throws UnknownException;
    void closePm()throws UnknownException;
    T detach(T entity)throws UnknownException;
    Object detachObject(Object entity)throws UnknownException;
    /*Map<?,T> allKeyValue(String fieldKey,String... arrayFields)throws UnknownException;*/
}
