package com.findsoft.SpringMVCDemo.entity;

public class HttpResponseResult {
    private int resultFlag;// 请求结果标志位0-成功1-失败
    private String resultDesc;// 请求结果描述
    private Object resultObj;// 请求对象

    public int getResultFlag() {
        return resultFlag;
    }

    public void setResultFlag(int resultFlag) {
        this.resultFlag = resultFlag;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public Object getResultObj() {
        return resultObj;
    }

    public void setResultObj(Object resultObj) {
        this.resultObj = resultObj;
    }
}
