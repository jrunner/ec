package com.t2t.top.base.model.dto;



import com.t2t.top.base.constant.Option;

import java.util.Date;

/**
 * {"ecode":200,"ocode":1,"success":"Y","result","在线用户数2人"}
 */
public class ResponseDto extends BaseModel {

    private static final long serialVersionUID = 7806290222194779596L;

    private long time;//服务器时间
    private int ecode;//错误编码,"JIPIAO-00001"
    private int ocode;//操作编码,"JIPIAO-USER-ADD"
    private boolean success;//true,false
    private String flag;//标示
    private Object data;//返回结果

    public static final String DEFAULT_SUCCESS_MESSAGE = "yes";
    public static final int DEFAULT_SUCCESS_OCODE = 0;
    public static final int DEFAULT_SUCCESS_ECODE = 0;
    public static final boolean DEFAULT_SUCCESS = true;

    public static final String DEFAULT_FAILD_MESSAGE = "no";
    public static final int DEFAULT_FAILD_OCODE = 0;
    public static final int DEFAULT_FAILD_ECODE = 0;
    public static final boolean DEFAULT_FAILD = false;
    public static final String EMPTY = "";

    public static final String OPER_CODE_QUERY_USER = "OPER-00001";//用户查询
    public static final String ERROR_CODE_QUERY_USER = "ERROR-00001";//用户查询失败

    public static ResponseDto bulidSuccessResult() {
        return bulidResult(DEFAULT_SUCCESS_MESSAGE, DEFAULT_SUCCESS_OCODE, DEFAULT_SUCCESS_ECODE, DEFAULT_SUCCESS, EMPTY);
    }

    /**
     * 动作标识
     *
     * @param flag
     */
    public static ResponseDto bulidSuccess(String flag) {
        return bulidResult(DEFAULT_SUCCESS_MESSAGE, DEFAULT_SUCCESS_OCODE, DEFAULT_SUCCESS_ECODE, DEFAULT_SUCCESS, flag);
    }

    public static ResponseDto bulidSuccessResult(String result) {
        return bulidResult(result, DEFAULT_SUCCESS_OCODE, DEFAULT_SUCCESS_ECODE, DEFAULT_SUCCESS, EMPTY);
    }

    public static ResponseDto bulidSuccessResult(Option op) {
        return bulidResult(op.getValue(), op.getCode(), DEFAULT_SUCCESS_ECODE, DEFAULT_SUCCESS, EMPTY);
    }

    public static ResponseDto bulidFailResult() {
        return bulidResult(DEFAULT_FAILD_MESSAGE, DEFAULT_FAILD_OCODE, DEFAULT_FAILD_ECODE, DEFAULT_FAILD, EMPTY);
    }

    public static ResponseDto bulidFailResult(String result, int ecode) {
        return bulidResult(result, ecode, DEFAULT_FAILD_ECODE, DEFAULT_FAILD, EMPTY);
    }

    public static ResponseDto bulidFailResult(String result) {
        return bulidResult(result, DEFAULT_FAILD_OCODE, DEFAULT_FAILD_ECODE, DEFAULT_FAILD, EMPTY);
    }

    public static ResponseDto bulidResult(String data, int ocode, int ecode, boolean success, String flag) {
        ResponseDto dto = new ResponseDto();
        dto.setData(data);
        dto.setEcode(ecode);
        dto.setOcode(ocode);
        dto.setSuccess(success);
        dto.setFlag(flag);
        return dto;
    }

    protected ResponseDto() {
        this.time = new Date().getTime();
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getEcode() {
        return ecode;
    }

    public ResponseDto setEcode(int ecode) {
        this.ecode = ecode;
        return this;
    }

    public int getOcode() {
        return ocode;
    }

    public ResponseDto setOcode(int ocode) {
        this.ocode = ocode;
        return this;
    }

    public Object getData() {
        return data;
    }

    public ResponseDto setData(Object data) {
        this.data = data;
        return this;
    }

    public ResponseDto setData(Option op) {
        this.ocode = op.getCode();
        this.data = op.getValue();
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public ResponseDto setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getFlag() {
        return flag;
    }

    public ResponseDto setFlag(String flag) {
        this.flag = flag;
        return this;
    }
}
