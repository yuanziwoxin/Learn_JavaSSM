package com.yuan.sm.entity;

import java.util.Date;

/**
 * 日志实体类
 */
public class Log {

    private Date oprTime;//操作时间
    private String type;//日志类型
    private String operator;//操作者
    private String module;//操作模块
    private String operation;//操作
    private String result;//操作结果

    public Date getOprTime() {
        return oprTime;
    }

    public void setOprTime(Date oprTime) {
        this.oprTime = oprTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String moudle) {
        this.module = moudle;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


}
