package com.yuan.oa.dao;

import com.yuan.oa.entity.ClaimVoucher;
import org.springframework.stereotype.Repository;

import java.util.List;

//报销单持久层
@Repository("claimVoucherDao")
public interface ClaimVoucherDao {
    /**
     * 添加报销单
     * @param claimVoucher
     */
    void insert(ClaimVoucher claimVoucher);

    /**
     * 修改报销单
     * @param claimVoucher
     */
    void update(ClaimVoucher claimVoucher);

    /**
     * 根据报销单编号删除报销单
     * @param cid
     */
    void delete(int cid);

    /**
     * 根据报销单编号查询报销单
     * @param cid
     */
    ClaimVoucher seleteOne(int cid);

    /**
     * 根据创建人编号查询其所有的报销单
     * @param createSn
     * @return
     */
    List<ClaimVoucher> selectByCreater(String createSn);

    /**
     * 根据待处理人编号查询其所有要处理的报销单
     * @param nextDealSn
     * @return
     */
    List<ClaimVoucher> selectByNextDealer(String nextDealSn);


}
