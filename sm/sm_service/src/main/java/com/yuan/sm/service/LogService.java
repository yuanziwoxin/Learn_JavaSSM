package com.yuan.sm.service;

import com.yuan.sm.entity.Log;

import java.util.List;

/**
 * 日志业务逻辑接口
 */
public interface LogService {
    /**
     * 添加系统日志
     * @param log
     */
    public void addSystemlog(Log log);
    /**
     * 添加登录日志
     * @param log
     */
    public void addLoginlog(Log log);
    /**
     * 添加操作日志
     * @param log
     */
    public void addOperationlog(Log log);

    /**
     * 获取系统日志
     * @return
     */
    public List<Log> getSystemlog();
    /**
     * 获取登录日志
     * @return
     */
    public List<Log> getLoginlog();
    /**
     * 获取操作日志
     * @return
     */
    public List<Log> getOperationlog();
}
