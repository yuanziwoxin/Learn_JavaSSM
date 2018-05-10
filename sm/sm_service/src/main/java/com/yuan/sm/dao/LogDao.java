package com.yuan.sm.dao;

import com.yuan.sm.entity.Log;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 日志操作接口
 */
@Repository("logDao")
public interface LogDao {
    /**
     * 插入日志
     * @param log
     */
    void insert(Log log);

    /**
     * 根据日志类型，查询相应类型的所有日志
     * @param type
     * @return
     */
    List<Log> selectByType(String type);
}
