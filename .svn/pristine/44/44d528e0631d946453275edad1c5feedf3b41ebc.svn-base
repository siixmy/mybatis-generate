package com.tc.gen.service;

import com.google.common.collect.Lists;
import com.tc.gen.vo.DBFieldInfo;
import com.tc.gen.vo.DbInfo;
import com.tc.gen.vo.DbTypeEnum;
import com.tc.gen.vo.GenParams;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * db操作
 *
 * @author Tantal
 * @version xx
 * @date 2015/07/10 15:32
 */
@Service
public class DbOperateService {
    /**
     * 连接db
     *
     * @param dbInfo
     * @return
     * @throws Exception
     */
    public DataSource connectDB(DbInfo dbInfo) throws Exception {
        if (dbInfo.getDbType().equals(DbTypeEnum.DbMeta.POSTGRESQL.toString())) {//postgresql
            dbInfo.setDbMeta(DbTypeEnum.DbMeta.POSTGRESQL);
            dbInfo.setDbUrl("jdbc:postgresql://" + dbInfo.getDbHost() + ":" + dbInfo.getDbPort() + "/" + dbInfo.getDb());
        } else {//mysql
            dbInfo.setDbMeta(DbTypeEnum.DbMeta.MYSQL);
            dbInfo.setDbUrl("jdbc:mysql://" + dbInfo.getDbHost() + ":" + dbInfo.getDbPort() + "/" + dbInfo.getDb() + "?useUnicode=true&amp;characterEncoding=UTF-8");
        }

        Class.forName(dbInfo.getDbMeta().getJdbcDriver());
        DataSource ds = new DriverManagerDataSource(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPwd());
        if (ds.getConnection().isClosed()) {
            throw new Exception("DB连接失败(可能原因：参数错误; 数据库关闭)");
        }
        return ds;
    }

    /**
     * 断开db连接
     */
    public void closeDB(DataSource ds) {
        try {
            if (ds != null)
                ds.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取所有表名
     *
     * @return
     */
    public List<String> getAllTable(GenParams genParams, NamedParameterJdbcTemplate jdbcTemplate) {

        String sql = String.format(genParams.getDbMeta().getAllTableSQL(), genParams.getDb());

        List<String> tables = jdbcTemplate.queryForList(sql, new HashMap<String, Object>(), String.class);
        if (StringUtils.isBlank(genParams.getTableGen())) return tables;

        List<String> retTables = Lists.newArrayList();
        for (String v : genParams.getTableGen().split(",")) {
            if (tables.contains(v)) {
                retTables.add(v.trim());
            }
        }
        return retTables;
    }

    /**
     * 获取表字段信息
     *
     * @param table 表名
     * @return
     */
    public List<DBFieldInfo> getFieldInfo(String table, DbTypeEnum.DbMeta dbMeta, NamedParameterJdbcTemplate jdbcTemplate) {

        String sql = String.format(dbMeta.getTableInfoSQL(), table);
        RowMapper mapper = new BeanPropertyRowMapper(DBFieldInfo.class);
        return jdbcTemplate.query(sql, new HashMap<String, Object>(), mapper);
    }
}
