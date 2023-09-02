package com.tc.gen.vo;

import java.io.Serializable;
public class JsonData implements Serializable {
	private JsonMessage status;
	private Object data;
	
	public JsonData(JsonMessage status, Object data) {
		this.status = status;
		this.data = data;
	}
	
	public JsonData(JsonMessage status) {
		this(status,null);
	}

	public static JsonData getSucceed(String msg,Object data) {
	    return new JsonData(JsonMessage.getSucceed(msg), data);
	}
	
    /**
     * 获取指定错误消息的失败状态的JsonData
     *
     * @author jiaomingyang
     * @version v2.0.9
     * @date 2014年7月7日 下午6:12:28
     *
     * @param errorMsg
     * @return
     */
    public static JsonData getFailed(String errorMsg) {
        return new JsonData(JsonMessage.getFailed(errorMsg));
    }

	/**获取返回状态*/
	public JsonMessage getStatus() {
		return status;
	}

	/**设置返回状态*/
	public void setStatus(JsonMessage status) {
		this.status = status;
	}
	
	/**获取返回值对象*/
	public Object getData() {
		return data;
	}
	
	/**设置返回值对象*/
	public void setData(Object data) {
		this.data = data;
	}

   static class JsonMessage implements Serializable {

        public static final Integer STATUS_SUCCESS = 0;
        public static final Integer STATUS_FAIL = 1;

        // 0 - 成功， 其他值 - 失败
        private Integer status = STATUS_SUCCESS;
        private String msg;

        private JsonMessage() {
        }

        private JsonMessage(Integer status,String msg) {
            this.status = status;
            this.msg=msg;
        }

        public static JsonMessage getFailed(String msg) {
            return new JsonMessage(STATUS_FAIL,msg);
        }

        public static JsonMessage getSucceed(String msg) {
            return new JsonMessage(STATUS_SUCCESS, msg);
        }

        public Integer getStatus() {
            return status;
        }

        public JsonMessage setStatus(Integer status) {
            this.status = status;
            return this;
        }


        public String getMsg() {
            return msg;
        }

        public JsonMessage setMsg(String msg) {
            this.msg = msg;
            return this;
        }

    }
}
