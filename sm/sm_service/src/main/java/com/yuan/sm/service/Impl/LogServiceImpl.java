package com.yuan.sm.service.Impl;

import com.yuan.sm.dao.LogDao;
import com.yuan.sm.entity.Log;
import com.yuan.sm.service.LogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("logService")
public class LogServiceImpl implements LogService{
    @Resource(name = "logDao")
    private LogDao logDao;

    public void addSystemlog(Log log) {
        log.setOprTime(new Date());//设置日志添加时间
        log.setType("system");//设置日志类型
        logDao.insert(log);
    }

    public void addLoginlog(Log log) {
        log.setOprTime(new Date());//设置日志添加时间
        log.setType("login");//设置日志类型
        logDao.insert(log);
    }

    public void addOperationlog(Log log) {
        log.setOprTime(new Date());//设置日志添加时间
        log.setType("operation");//设置日志类型
        logDao.insert(log);
    }

    public List<Log> getSystemlog() {
        return logDao.selectByType("system");
    }

    public List<Log> getLoginlog() {
        return logDao.selectByType("login");
    }

    public List<Log> getOperationlog() {
        return logDao.selectByType("operation");
    }
}
