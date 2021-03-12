package com.jofrantoba.model.jdo.shared;

import java.io.Serializable;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Data
public class UnknownException extends Exception implements Serializable {    
    private Class<?> clase;

    public UnknownException() {
        super();        
    }
    
    public UnknownException(Class<?> clase,String message) {
        super(message);
        this.clase=clase;
    } 
    
    public UnknownException(Class<?> clase,String message, Throwable cause) {
        super(message, cause);
        this.clase=clase;
    }
    
    public UnknownException(Class<?> clase,String message, Throwable cause,
                        boolean enableSuppression,
                        boolean writableStackTrace){
        super(message, cause, enableSuppression, writableStackTrace);
        this.clase=clase;
    }

    public void traceLog(boolean allTrace){        
        StringBuilder logTrace=new StringBuilder();
        logTrace.append("\n");
        logTrace.append("***Inicia UnknownException***").append("\n");
        logTrace.append(".:.:.stacktrace.:.:.").append("\n");        
        logTrace.append("Mensaje:").append(this.getMessage()).append("\n");
        logTrace.append("Causa:").append(this.getCause()).append("\n");
        logTrace.append("Clase:").append(this.getClase()).append("\n");
        logTrace.append("Localizacion:").append(this.getLocalizedMessage()).append("\n");        
        for(int i=0;i<this.getStackTrace().length;i++){                       
            if(!allTrace && i>0){
                continue;
            }
            logTrace.append("En ").append(this.getStackTrace()[i].getClassName()).append(".").append(this.getStackTrace()[i].getMethodName()).append("(").append(this.getStackTrace()[i].getFileName()).append(":").append(this.getStackTrace()[i].getLineNumber()).append(")\n");                       
        }
        logTrace.append(".:.:.stacktrace.:.:.").append("\n");        
        logTrace.append("***Termina UnknownException***").append("\n");
        log.error(logTrace);
    }
    
}
