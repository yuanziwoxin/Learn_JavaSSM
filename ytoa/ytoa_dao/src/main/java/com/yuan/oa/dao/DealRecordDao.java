package com.yuan.oa.dao;

import com.yuan.oa.entity.DealRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

//处理记录的持久层
@Repository("dealRecordDao")
public interface DealRecordDao {
    /**
     * 添加处理记录
     * @param dealRecord
     */
    void insert(DealRecord dealRecord);

    /**
     * 根据报销单的编号查询其的所有处理记录
     * @param cvid 报销单编号
     * @return
     */
    List<DealRecord> selectByClaimVoucherId(int cvid);
}
