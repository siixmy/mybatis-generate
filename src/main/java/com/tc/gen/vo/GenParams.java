package com.tc.gen.vo;

public class GenParams extends DbInfo {
    private String tableGen;
    private Integer tableFirstRemove;
    private Boolean firstGen;
    private String baseDaoClass = "IMybatisBaseDao";
    private String packagePrefix;
    private String exceptionClass;
    private String author;
    private String version;

    private boolean useOld;

    public String getTableGen() {
        return tableGen;
    }

    public void setTableGen(String tableGen) {
        this.tableGen = tableGen;
    }

    public Integer getTableFirstRemove() {
        return tableFirstRemove;
    }

    public void setTableFirstRemove(Integer tableFirstRemove) {
        this.tableFirstRemove = tableFirstRemove;
    }

    public Boolean getFirstGen() {
        return firstGen;
    }

    public void setFirstGen(Boolean firstGen) {
        this.firstGen = firstGen;
    }

    public String getBaseDaoClass() {
        return baseDaoClass;
    }

    public void setBaseDaoClass(String baseDaoClass) {
        this.baseDaoClass = baseDaoClass;
    }

    public String getPackagePrefix() {
        return packagePrefix;
    }

    public void setPackagePrefix(String packagePrefix) {
        this.packagePrefix = packagePrefix;
    }

    public String getExceptionClass() {
        return exceptionClass;
    }

    public void setExceptionClass(String exceptionClass) {
        this.exceptionClass = exceptionClass;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean getUseOld() {
        return useOld;
    }

    public void setUseOld(boolean useOld) {
        this.useOld = useOld;
    }
}
