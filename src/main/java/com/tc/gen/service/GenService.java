package com.tc.gen.service;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.tc.gen.util.GenerateUtils;
import com.tc.gen.vo.DBFieldInfo;
import com.tc.gen.vo.GenParams;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * description
 *
 * @author Tantal
 * @version xx
 * @date 2015/07/10 15:36
 */
@Service
public class GenService {

    private static final String MAVEN_JAVA_PATH = "src/main/java/";
    private static final String MAVEN_RESOURCES_PATH = "src/main/resources/";

    private static final String baseDirectory = GenService.class.getClassLoader().getResource("").getPath();
    private static final String templateDirectory = baseDirectory + "/template";
    public static final String zipDirectory = baseDirectory + "/tmp";
    public static final String targetDirectory = baseDirectory + "/tmp/gen";

    @Resource
    private DbOperateService dbOperateService;

    public void genByTemplate(GenParams genParams, Properties prop, Configuration cfg, Map<String, Object> ftlParams, DataSource ds) throws IOException {
        GenerateUtils.deleteDir(new File(GenService.zipDirectory));

        this.generateBase(genParams, prop, cfg, ftlParams);
        this.generateOther(prop, cfg, ftlParams, genParams, ds);
    }


    /**
     * 初始化模板变量
     *
     * @param prop
     * @param genParams
     * @return
     */
    public Map<String, Object> iniTemplateParams(Properties prop, GenParams genParams) {

        //目标项目包
        prop.setProperty("ftl_package", genParams.getPackagePrefix());
        prop.setProperty("ftl_model_package", genParams.getPackagePrefix() + ".pojo");
        prop.setProperty("ftl_dao_package", genParams.getPackagePrefix() + ".dao");
        prop.setProperty("ftl_service_package", genParams.getPackagePrefix() + ".service");
        prop.setProperty("ftl_exception_class", StringUtils.isBlank(genParams.getExceptionClass()) ? "DBFailException" : genParams.getExceptionClass());
        prop.setProperty("ftl_base_dao_class", StringUtils.isBlank(genParams.getBaseDaoClass()) ? "IMybatisBaseDao" : genParams.getBaseDaoClass());

        Map<String, Object> ftlParams = Maps.newHashMap();
        //装填模板中的变量
        for (Object k : prop.keySet()) {
            if (k.toString().startsWith("ftl")) {
                ftlParams.put(k.toString(), prop.get(k).toString());
            }
        }

        ftlParams.put("ftl_author", Strings.nullToEmpty(genParams.getAuthor()));
        ftlParams.put("ftl_version", Strings.nullToEmpty(genParams.getVersion()));

        ftlParams.put("ftl_now",
                new SimpleDateFormat("yyyy/MM/dd HH:mm").format(new Date()));
        ftlParams.put("ftl_dbType", genParams.getDbType().toString());


        return ftlParams;
    }

    //使用自定义dao对应模板时，文件名称转换
    public String getCustomDaoTemplateFile(String originFile) {
        int idx = StringUtils.indexOf(originFile, ".ftl");
        return StringUtils.substring(originFile, 0, idx) + ".custom.ftl";
    }

    // 初始化FreeMarker配置 创建一个Configuration实例,并设置FreeMarker的模版文件位置
    public Configuration iniFreeMarkerCfg() throws IOException {
        Configuration cfg = new Configuration();
        cfg.setDirectoryForTemplateLoading(new File(templateDirectory));
        cfg.setEncoding(Locale.CHINA, "UTF-8");//处理模板生成中文乱码
        return cfg;
    }

    /**
     * 根据模板生成文件
     *
     * @param root
     * @param savePath
     * @param fileName
     * @param template
     */
    public void buildTemplate(Map root, String savePath,
                              String fileName, Template template) {

        String realSavePath = GenerateUtils.appendBias(targetDirectory) + savePath;
        String realFileName = GenerateUtils.appendBias(realSavePath) + fileName;


        File newsDir = new File(realSavePath);
        if (!newsDir.exists()) {
            newsDir.mkdirs();
        }
        Writer out = null;
        try {
            out = new OutputStreamWriter(new FileOutputStream(
                    realFileName), "UTF-8");

            template.process(root, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) try {
                out.close();
            } catch (IOException e) {
            }
        }
    }


    private void generateBase(GenParams genParams, Properties config, Configuration cfg, Map<String, Object> ftlParams) throws IOException {
        if (!Boolean.TRUE.equals(genParams.getFirstGen())) {
            return;
        }

        //base model
        String savePath = MAVEN_JAVA_PATH +
                Objects.firstNonNull(config.getProperty("ftl_model_package"), "").replace(".", "/");
        Template template = cfg.getTemplate(Objects.firstNonNull(config.getProperty("template.ModelBase.file"), ""));
        this.buildTemplate(ftlParams, savePath, "AbsPojoSet.java", template);

        //1 pagination
        savePath = MAVEN_JAVA_PATH +
                GenerateUtils.appendBias(Objects.firstNonNull(config.getProperty("ftl_model_package"), "").replace(".", "/")) + "pagination";
        template = cfg.getTemplate(Objects.firstNonNull(config.getProperty("template.Page.file"), ""));
        this.buildTemplate(ftlParams, savePath, "Page.java", template);

        savePath = MAVEN_JAVA_PATH +
                GenerateUtils.appendBias(Objects.firstNonNull(config.getProperty("ftl_model_package"), "").replace(".", "/")) + "pagination";
        template = cfg.getTemplate(Objects.firstNonNull(config.getProperty("template.PageAttribute.file"), ""));
        this.buildTemplate(ftlParams, savePath, "PageAttribute.java", template);

        savePath = MAVEN_JAVA_PATH +
                GenerateUtils.appendBias(Objects.firstNonNull(config.getProperty("ftl_model_package"), "").replace(".", "/")) + "pagination";
        template = cfg.getTemplate(Objects.firstNonNull(config.getProperty("template.PageList.file"), ""));
        this.buildTemplate(ftlParams, savePath, "PageList.java", template);


        //2 IMyBatisRepository
        if (!genParams.getUseOld()) {
            savePath = MAVEN_JAVA_PATH +
                    Objects.firstNonNull(config.getProperty("ftl_dao_package"), "").replace(".", "/");
            template = cfg.getTemplate(Objects.firstNonNull(config.getProperty("template.IMyBatisRepository.file"), ""));
            this.buildTemplate(ftlParams, savePath,
                    config.getProperty("ftl_base_dao_class") + ".java", template);
        }

        //3 my exception
        String fileName = config.getProperty("ftl_exception_class") + ".java";
        savePath = MAVEN_JAVA_PATH +
                GenerateUtils.appendBias(Objects.firstNonNull(config.getProperty("ftl_package"), "").replace(".", "/")) + "exception";
        template = cfg.getTemplate(Objects.firstNonNull(config.getProperty("template.MyException.file"), ""));
        this.buildTemplate(ftlParams, savePath, fileName, template);

        //4 IBaseService&AbstractBaseService&IPaginationService
//        savePath = MAVEN_JAVA_PATH +
//                Objects.firstNonNull(config.getProperty("ftl_service_package"), "").replace(".", "/");
//        template = cfg.getTemplate(Objects.firstNonNull(config.getProperty("template.IBaseService.file"), ""));
//        this.buildTemplate(ftlParams, savePath, "IBaseService.java", template);
//
//        savePath = MAVEN_JAVA_PATH +
//                Objects.firstNonNull(config.getProperty("ftl_service_package"), "").replace(".", "/");
//        template = cfg.getTemplate(Objects.firstNonNull(config.getProperty("template.AbstractBaseService.file"), ""));
//        this.buildTemplate(ftlParams, savePath, "AbstractBaseService.java", template);

    }

    /**
     * 生成对应table的model，dao，service和mybatis配置文件
     */
    private void generateOther(Properties prop, Configuration cfg, Map<String, Object> ftlParams, GenParams genParams, DataSource ds) throws IOException {

        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(ds);

        int tableSubStrIndex = Integer.valueOf(Objects.firstNonNull(genParams.getTableFirstRemove(), 0).toString());
        List<String> tables = dbOperateService.getAllTable(genParams, jdbcTemplate);
        for (String v : tables) {
            List<DBFieldInfo> dbFieldInfos = dbOperateService.getFieldInfo(v, genParams.getDbMeta(), jdbcTemplate);
            String className = StringUtils.capitalize(GenerateUtils.convertCamel(v.substring(tableSubStrIndex)));
            ftlParams.put("tableName", v);
            ftlParams.put("className", className);
            ftlParams.put("classFields", dbFieldInfos);

            //model
            String fileName = className + ".java";
            String savePath = MAVEN_JAVA_PATH +
                    Objects.firstNonNull(prop.getProperty("ftl_model_package"), "").replace(".", "/");
            Template template = cfg.getTemplate(Objects.firstNonNull(prop.getProperty("template.Model.file"), ""));
            this.buildTemplate(ftlParams, savePath, fileName, template);

            // 第一次会生成，以后不再生成，避免覆盖用户脚本
            if (Boolean.TRUE.equals(genParams.getFirstGen())) {
                //dao
                fileName = "I" + className + "Dao.java";
                savePath = MAVEN_JAVA_PATH +
                        Objects.firstNonNull(prop.getProperty("ftl_dao_package"), "").replace(".", "/");
                template = cfg.getTemplate(Objects.firstNonNull(prop.getProperty("template.IDao.file"), ""));
                this.buildTemplate(ftlParams, savePath, fileName, template);

                //service
//                fileName = "I" + className + "Service.java";
//                savePath = MAVEN_JAVA_PATH +
//                        Objects.firstNonNull(prop.getProperty("ftl_service_package"), "").replace(".", "/");
//                template = cfg.getTemplate(Objects.firstNonNull(prop.getProperty("template.IService.file"), ""));
//                this.buildTemplate(ftlParams, savePath, fileName, template);
//
//                //service impl
//                fileName = className + "Service.java";
//                savePath = MAVEN_JAVA_PATH +
//                        Objects.firstNonNull(prop.getProperty("ftl_service_package"), "").replace(".", "/") + "/impl";
//                template = cfg.getTemplate(Objects.firstNonNull(prop.getProperty("template.Service.file"), ""));
//                this.buildTemplate(ftlParams, savePath, fileName, template);
            }

            //sql
            fileName = className + "_sql.xml";
            savePath = MAVEN_RESOURCES_PATH + "mybatis/";

            String filePrefix = "mybatis/";
            if (genParams.getUseOld()) {
                filePrefix += "old/";
            }
            template = cfg.getTemplate(filePrefix + Objects.firstNonNull(prop.getProperty("template.sql.file"), ""));
            this.buildTemplate(ftlParams, savePath, fileName, template);


            if (Boolean.TRUE.equals(genParams.getFirstGen())) {
                fileName = className + "_sqlex.xml";
                savePath = MAVEN_RESOURCES_PATH + "mybatis/ex/";
                template = cfg.getTemplate(filePrefix + Objects.firstNonNull(prop.getProperty("template.sqlex.file"), ""));
                this.buildTemplate(ftlParams, savePath, fileName, template);
            }
        }
    }
}
