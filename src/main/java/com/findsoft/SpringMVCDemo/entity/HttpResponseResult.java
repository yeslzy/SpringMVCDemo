package com.findsoft.SpringMVCDemo.entity;

public class HttpResponseResult {
    private int resultFlag;// ��������־λ0-�ɹ�1-ʧ��
    private String resultDesc;// ����������
    private Object resultObj;// �������

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
