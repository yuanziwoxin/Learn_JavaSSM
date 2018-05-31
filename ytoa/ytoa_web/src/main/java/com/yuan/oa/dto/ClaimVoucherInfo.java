package com.yuan.oa.dto;

import com.yuan.oa.entity.ClaimVoucher;
import com.yuan.oa.entity.ClaimVoucherItem;

import java.util.List;
//报销单信息实体类：用于封装报销单信息和报销单明细条目信息
public class ClaimVoucherInfo {

    private ClaimVoucher claimVoucher;//报销单对象
    private List<ClaimVoucherItem> items;//报销单明细条目

    public ClaimVoucher getClaimVoucher() {
        return claimVoucher;
    }

    public void setClaimVoucher(ClaimVoucher claimVoucher) {
        this.claimVoucher = claimVoucher;
    }

    public List<ClaimVoucherItem> getItems() {
        return items;
    }

    public void setItems(List<ClaimVoucherItem> items) {
        this.items = items;
    }
}
