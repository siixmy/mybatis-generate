package com.tc.gen.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class GenerateUtils {
    /**
     * 转换成驼峰
     * 例如：hello_word->helloWorld
     *
     * @param source
     * @return
     */
    public static String convertCamel(String source) {

        StringBuilder result = new StringBuilder();
        if (source == null || source.isEmpty()) return "";

        if (!source.contains("_")) {
            return source.substring(0, 1).toLowerCase() + source.substring(1);
        }
        String[] columns = source.split("_");
        for (String columnSplit : columns) {
            if (columnSplit.isEmpty()) {
                continue;
            }
            if (result.length() == 0) {
                result.append(columnSplit.toLowerCase());
            } else {
                result.append(columnSplit.substring(0, 1).toUpperCase()).append(columnSplit.substring(1).toLowerCase());
            }
        }
        return result.toString();

    }

    /**
     * 给字符串末尾增加斜线，用于拼接文件路径
     * @param source
     * @return
     */
    public static String appendBias(String source){
        if(source.endsWith("/"))
            return source;
        else
            return source+"/";

    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir
     * @return
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    /*
    * inputFileName 输入一个文件夹
    * zipFileName 输出一个压缩文件夹
    */
    public static String zip(String inputFileName,String targetDirectory) throws Exception {
        String outZipFileName = GenerateUtils.appendBias(targetDirectory) + "gen" + new SimpleDateFormat("yyMMddHHmmss").format(new Date()) + ".zip"; //打包后文件名字

        File _tmpDirectory = new File(targetDirectory);
        if (!_tmpDirectory.exists() && !_tmpDirectory.isDirectory()) {
            _tmpDirectory.mkdir();
        }

        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outZipFileName));
        zip(out, new File(inputFileName), "");
        out.close();
        return outZipFileName;
    }

    private static void zip(ZipOutputStream out, File f, String base) throws Exception {
        if (f.isDirectory()) {
            File[] fl = f.listFiles();
//            out.putNextEntry(new ZipEntry(base + "/"));
            base = base.length() == 0 ? "" : base + "/";
            for (int i = 0; i < fl.length; i++) {
                zip(out, fl[i], base + fl[i].getName());
            }
        } else {
            out.putNextEntry(new ZipEntry(base));
            FileInputStream in = new FileInputStream(f);
            int b;
            System.out.println(base);
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            in.close();
        }
    }
}
