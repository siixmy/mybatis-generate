package com.tc.gen.vo;

import java.io.Serializable;

/**
 * description
 *
 * @author Tantal
 * @version xx
 * @date 2015/07/09 10:25
 */
public class DbInfo implements Serializable {
    private String dbType;
    private String dbHost;
    private String dbPort;
    private String dbUsername;
    private String dbPwd;
    private String db;

    private DbTypeEnum.DbMeta dbMeta;
    private String dbUrl;

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getDbHost() {
        return dbHost;
    }

    public void setDbHost(String dbHost) {
        this.dbHost = dbHost;
    }

    public String getDbPort() {
        return dbPort;
    }

    public void setDbPort(String dbPort) {
        this.dbPort = dbPort;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    public String getDbPwd() {
        return dbPwd;
    }

    public void setDbPwd(String dbPwd) {
        this.dbPwd = dbPwd;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public DbTypeEnum.DbMeta getDbMeta() {
        return dbMeta;
    }

    public void setDbMeta(DbTypeEnum.DbMeta dbMeta) {
        this.dbMeta = dbMeta;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }
}
