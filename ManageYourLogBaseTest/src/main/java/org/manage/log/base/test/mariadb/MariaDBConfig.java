package org.manage.log.base.test.mariadb;

import ch.vorburger.mariadb4j.DB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author cartoon
 * @date 2022/4/5 16:41
 */
public class MariaDBConfig {

    /**
     * database schema script
     */
    private List<String> schemaList = new ArrayList<>();

    /**
     * database data script
     */
    private List<String> dataList = new ArrayList<>();

    /**
     * database schema directory
     */
    private String baseDir;

    /**
     * database data directory
     */
    private String dataDir;

    /**
     * database port
     */
    private Integer port;

    /**
     * db start flag
     */
    public AtomicBoolean mariaDbIsStart = new AtomicBoolean(false);

    /**
     * db object
     */
    private DB db;

    public String getBaseDir() {
        return baseDir;
    }

    public MariaDBConfig setBaseDir(String baseDir) {
        this.baseDir = baseDir;
        return this;
    }

    public String getDataDir() {
        return dataDir;
    }

    public MariaDBConfig setDataDir(String dataDir) {
        this.dataDir = dataDir;
        return this;
    }

    public Integer getPort() {
        return port;
    }

    public MariaDBConfig setPort(Integer port) {
        this.port = port;
        return this;
    }

    public AtomicBoolean getMariaDbIsStart() {
        return mariaDbIsStart;
    }

    public void setMariaDbIsStart(AtomicBoolean mariaDbIsStart) {
        this.mariaDbIsStart = mariaDbIsStart;
    }

    public DB getDb() {
        return db;
    }

    public MariaDBConfig setDb(DB db) {
        this.db = db;
        return this;
    }

    public List<String> getSchemaList() {
        return schemaList;
    }

    public MariaDBConfig addSchemaScript(String... schemaList){
        this.schemaList.addAll(Arrays.asList(schemaList));
        return this;
    }

    public List<String> getDataList() {
        return dataList;
    }

    public MariaDBConfig addDataScript(String... dataList) {
        this.dataList.addAll(Arrays.asList(dataList));
        return this;
    }
}
