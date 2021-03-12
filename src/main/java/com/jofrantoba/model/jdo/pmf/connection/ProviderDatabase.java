/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jofrantoba.model.jdo.pmf.connection;

/**
 *
 * @author jona
 */
public enum ProviderDatabase {
    MONGO("MongoDb",""),
    ORACLE("Oracle","oracle.jdbc.driver.OracleDriver"),
    POSTGRES("Postgres",""),
    MYSQL("MySql","com.mysql.jdbc.Driver"),
    SQLSERVER("SqlServer","com.microsoft.sqlserver.jdbc.SQLServerDriver"),
    HYPERSQL("HyperSql","org.hsqldb.jdbc.JDBCDriver");
    
    private String nameProvider;
    private String driver;
    
    private ProviderDatabase(String nameProvider,String driver){
        this.nameProvider=nameProvider;
        this.driver=driver;
    }

    public String getNameProvider() {
        return nameProvider;
    }

    public String getDriver() {
        return driver;
    }
    
    
}
