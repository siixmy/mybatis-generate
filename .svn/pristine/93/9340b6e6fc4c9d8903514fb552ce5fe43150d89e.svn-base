package com.tc.gen.vo;

/**
 * description
 *
 * @author Tantal
 * @version xx
 * @date 2015/07/09 11:51
 */
public interface DbTypeEnum {

    enum DbMeta {
        POSTGRESQL("org.postgresql.Driver",
                "select tablename from pg_tables where schemaname='public'",
                "select column_name AS columnName,data_type AS dataType," +
                        "(CASE WHEN is_nullable='YES' THEN true ELSE false END) AS isNullable,character_maximum_length AS charMaxLength," +
                        "(CASE WHEN ordinal_position=1 THEN true ELSE false END) AS isPrimaryKey" +
                        " from information_schema.columns" +
                        " where table_schema = 'public' and table_name = '%s'"),
        MYSQL("com.mysql.jdbc.Driver",
                "SELECT TABLE_NAME FROM information_schema.TABLES WHERE TABLE_SCHEMA='%s'",
                "SELECT column_name AS columnName,data_type AS dataType," +
                        "character_maximum_length AS charMaxLength,numeric_precision AS numericMaxLength," +
                        "numeric_scale AS numericScaleMaxLength,is_nullable AS isNullable," +
                        "(CASE WHEN extra = 'auto_increment' THEN TRUE ELSE FALSE END) AS isPrimaryKey" +
                        " FROM Information_schema.COLUMNS" +
                        " WHERE table_Name = '%s'"),
        ;

        private String jdbcDriver, allTableSQL, tableInfoSQL;

        private DbMeta(String jdbcDriver, String allTableSQL, String tableInfoSQL) {
            this.jdbcDriver = jdbcDriver;
            this.allTableSQL = allTableSQL;
            this.tableInfoSQL = tableInfoSQL;
        }

        public String getJdbcDriver() {
            return jdbcDriver;
        }

        public String getAllTableSQL() {
            return allTableSQL;
        }

        public String getTableInfoSQL() {
            return tableInfoSQL;
        }
    }
}
