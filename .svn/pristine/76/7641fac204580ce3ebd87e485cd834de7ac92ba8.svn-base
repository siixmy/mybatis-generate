package com.tc.gen.action;

import com.google.common.collect.Lists;
import com.tc.gen.service.DbOperateService;
import com.tc.gen.service.GenService;
import com.tc.gen.util.GenerateUtils;
import com.tc.gen.vo.DbInfo;
import com.tc.gen.vo.DbTypeEnum;
import com.tc.gen.vo.GenParams;
import com.tc.gen.vo.JsonData;
import freemarker.template.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Controller
@RequestMapping("/")
public class GenController {

    @Resource
    private DbOperateService dbOperateService;
    @Resource
    private GenService genService;

    @RequestMapping(value = "gen", method = RequestMethod.GET)
    public void iniData(ModelMap model) {
        List<String> dbTypes = Lists.newArrayList();
        for (DbTypeEnum.DbMeta v : DbTypeEnum.DbMeta.values()) {
            dbTypes.add(v.toString());
        }
        model.addAttribute("dbTypes", dbTypes);
    }

    @ResponseBody
    @RequestMapping(value = "connect-db", method = RequestMethod.POST)
    public JsonData connectDb(DbInfo dbInfo) {
        DataSource ds = null;
        try {
            ds = dbOperateService.connectDB(dbInfo);
        } catch (Exception e) {
            return JsonData.getFailed(e.getMessage());
        } finally {
            dbOperateService.closeDB(ds);
        }
        return JsonData.getSucceed("DB连接成功", null);
    }

    @ResponseBody
    @RequestMapping(value = "generate", method = RequestMethod.POST)
    public JsonData generate(GenParams genParams) {
        DataSource ds = null;
        try {
            ds = dbOperateService.connectDB(genParams);

            Properties prop = new Properties();
            prop.load(GenController.class.getClassLoader().getResourceAsStream("sys-config.properties"));

            Map<String, Object> ftlParams = genService.iniTemplateParams(prop, genParams);

            Configuration cfg = genService.iniFreeMarkerCfg();

            genService.genByTemplate(genParams,prop,cfg,ftlParams,ds);

        } catch (Exception ex) {
            return JsonData.getFailed("生成失败." + ex.getMessage());
        } finally {
            dbOperateService.closeDB(ds);
        }
        return JsonData.getSucceed("生成成功", null);

    }


    @RequestMapping("download")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //压缩
        String fileFullName = GenerateUtils.zip(GenService.targetDirectory,GenService.zipDirectory);

        //下载zip文件
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        java.io.BufferedInputStream bis = null;
        java.io.BufferedOutputStream bos = null;
        try {
            File file = new File(fileFullName);
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename="
                    + new String(file.getName().getBytes("gb2312"), "ISO8859-1"));
            response.setHeader("Content-Length", String.valueOf(file.length()));
            bis = new BufferedInputStream(new FileInputStream(fileFullName));
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
    }


}