package com.yuan.oa.dao;

import com.yuan.oa.entity.ClaimVoucherItem;
import org.springframework.stereotype.Repository;

import java.util.List;

//报销单明细持久层
@Repository("claimVoucherItemDao")
public interface ClaimVoucherItemDao {
    /**
     * 添加报销单明细条目
     * @param claimVoucherItem
     */
     void insert(ClaimVoucherItem claimVoucherItem);

    /**
     * 修改报销单明细条目
     * @param claimVoucherItem
     */
     void update(ClaimVoucherItem claimVoucherItem);

    /**
     * 根据报销单明细条目的编号删除一条报销单明细条目
     * @param id 报销单明细条目的编号
     */
     void delete(int id);
    /**
     * 根据报销单编号来查询其对应的所有报销单明细条目
     * @param cvid 报销单编号
     * @return
     */
     List<ClaimVoucherItem> selectByClaimVoucherId(int cvid);
}
