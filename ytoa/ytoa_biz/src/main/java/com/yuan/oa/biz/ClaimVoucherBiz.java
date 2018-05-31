package com.yuan.oa.biz;

import com.yuan.oa.entity.ClaimVoucher;
import com.yuan.oa.entity.ClaimVoucherItem;
import com.yuan.oa.entity.DealRecord;

import java.util.List;

//报销单的业务接口
public interface ClaimVoucherBiz {

    /**
     * 保存报销单：包括保存报销单主要信息和其对应的报销单明细条目
     * @param claimVoucher 报销单信息
     * @param items 报销单明细条目信息
     */
    void save(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items);

    /**
     * 根据报销单id获取报销单信息
     * @param cvid 报销单编号
     */
    ClaimVoucher getOne(int cvid);

    /**
     * 根据报销单id获取其所有相应的处理记录
     * @param cvid 报销单编号
     */
    List<DealRecord> getDealRecords(int cvid);

    /**
     * 根据报销单id获取其相应的所有报销单明细条目
     * @param cvid 报销单编号
     * @return
     */
    List<ClaimVoucherItem> getClaimVoucherItems(int cvid);

    /**
     * 根据创建人的编号查询其创建的所有报销单
     * @param cname 创建人编号
     * @return
     */
    List<ClaimVoucher> getForCreater(String cname);

    /**
     * 根据待处理人的编号查询其待处理的报销单
     * @param dealName 报销单编号
     * @return
     */
    List<ClaimVoucher> getForDeal(String dealName);

    /**
     * 修改报销单信息（包括报销单主要信息和报销单明细条目信息）
     * @param claimVoucher
     * @param claimVoucherItems
     */
    void edit(ClaimVoucher claimVoucher,List<ClaimVoucherItem> claimVoucherItems);

    /**
     * 根据报销单编号进行提交
     * @param id 报销单编号
     */
    void submit(int id);

    /**
     * 处理报销单
     * @param dealRecord 报销单处理记录对象
     */
    void deal(DealRecord dealRecord);
}
