package com.springboot.test.exception;
/**
* my exception
*
* @author sijie
* @date 2023/09/02 13:21
* @version 0.0.1
*/
public class DBFailException extends Exception {
    public DBFailException(){
    super("未知错误");
    }

    public DBFailException(String errorMsg){
    super(errorMsg);
    }

    public DBFailException(String errorMsg, Throwable cause){
    super(errorMsg, cause);
    }

}
