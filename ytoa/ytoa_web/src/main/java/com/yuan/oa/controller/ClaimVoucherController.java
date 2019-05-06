package com.yuan.oa.controller;

import com.yuan.oa.biz.ClaimVoucherBiz;
import com.yuan.oa.dto.ClaimVoucherInfo;
import com.yuan.oa.entity.ClaimVoucher;
import com.yuan.oa.entity.DealRecord;
import com.yuan.oa.entity.Employee;
import com.yuan.oa.global.Constant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
//报销单处理的控制层
@Controller("claimVoucherController")
@RequestMapping("/claim_voucher")
public class ClaimVoucherController {

    @Resource(name = "claimVoucherBiz")
    private ClaimVoucherBiz claimVoucherBiz;

    @RequestMapping("/to_add")
    public String toAdd(Map<String,Object> map)
    {
        map.put("feeItems",Constant.getItems());//将费用类型封装到map集合中；
        map.put("info",new ClaimVoucherInfo());//将一个空的封装有报销单信息属性和报销单明细条目属性的实体类封装到map集合中；
        return "claim_voucher_add";
    }
    //添加报销单
    @RequestMapping("/add")
    public String add(HttpSession session,ClaimVoucherInfo info)
    {
        //从Session对象中获取当前用户对象，以用于后面获取报销单创建人的编号
        Employee employee= (Employee) session.getAttribute("employee");
        info.getClaimVoucher().setCreateSn(employee.getEname());//设置报销单的创建人
        //保存报销单信息（包括报销单主要信息和报销单明细信息）
//        ClaimVoucher claimVoucher=info.getClaimVoucher();
//        List<ClaimVoucherItem> lists= info.getItems();
        claimVoucherBiz.save(info.getClaimVoucher(),info.getItems());
        return "redirect:deal";//跳转到报销单待处理页面
    }
    //报销单详情页
    @RequestMapping("/details")
    public String details(int id,Map<String,Object> map)
    {
        map.put("claimVoucher",claimVoucherBiz.getOne(id));//获取报销单主要信息
        map.put("claimVoucherItems",claimVoucherBiz.getClaimVoucherItems(id));//获取报销单对应的所有明细条目信息
        map.put("dealRecords",claimVoucherBiz.getDealRecords(id));//获取报销单对应的所有处理记录
        return "claim_voucher_details";//跳转到报销单详情页面
    }
    //个人创建的所有报销单信息显示
    @RequestMapping("/self")
    public String self(HttpSession session,Map<String,Object> map)
    {
        //从Session对象中获取当前用户对象，以用于后面获取报销单创建人的编号
        Employee employee= (Employee) session.getAttribute("employee");
        List<ClaimVoucher> list=claimVoucherBiz.getForCreater(employee.getEname());
        map.put("list",list);
        return "claim_voucher_self";
    }
    //个人待处理的所有报销单信息显示
    @RequestMapping("/deal")
    public String deal(HttpSession session,Map<String,Object> map)
    {
        //从Session对象中获取当前用户对象，以用于后面获取待处理人的编号
        Employee employee= (Employee) session.getAttribute("employee");
        List<ClaimVoucher> list=claimVoucherBiz.getForDeal(employee.getEname());
        map.put("list",list);
        return "claim_voucher_deal";
    }

    @RequestMapping("/to_update")
    public String toUpdate(int id,Map<String,Object> map)
    {
        map.put("feeItems",Constant.getItems());//将费用类型封装到map集合中；
        ClaimVoucherInfo info = new ClaimVoucherInfo();
        info.setClaimVoucher(claimVoucherBiz.getOne(id));//将修改前的报销单主要信息回写到info对象中
        info.setItems(claimVoucherBiz.getClaimVoucherItems(id));//将修改前的报销单明细信息回写到info对象中
        map.put("info",info);//将一个封装有修改前的报销单主要信息和报销单明细条目属性的实体类封装到map集合中；
        return "claim_voucher_update";
    }
    //修改报销单
    @RequestMapping("/update")
    public String update(HttpSession session,ClaimVoucherInfo info)
    {
        //从Session对象中获取当前用户对象，以用于后面获取报销单创建人的编号
        Employee employee= (Employee) session.getAttribute("employee");
        info.getClaimVoucher().setCreateSn(employee.getEname());//设置报销单的创建人
        //修改报销单信息
        claimVoucherBiz.edit(info.getClaimVoucher(),info.getItems());
        return "redirect:deal";
    }
    //提交报销单
    @RequestMapping("/submit")
    public String submit(int id)
    {
        claimVoucherBiz.submit(id);//提交报销单
        return "redirect:deal";
    }

    //审核处理
    @RequestMapping("/to_check")
    public String toCheck(int id,Map<String,Object> map)
    {
        map.put("claimVoucher",claimVoucherBiz.getOne(id));//获取报销单主要信息
        map.put("claimVoucherItems",claimVoucherBiz.getClaimVoucherItems(id));//获取报销单对应的所有明细条目信息
        map.put("dealRecords",claimVoucherBiz.getDealRecords(id));//获取报销单对应的所有处理记录
        DealRecord dealRecord=new DealRecord();//实例化一个处理记录对象，用于封装一条处理记录信息
        dealRecord.setClaimVoucherId(id);//设置处理的报销单的编号
        map.put("dRecord",dealRecord);
        return "claim_voucher_check";
    }
    //审核处理
    @RequestMapping("/check")
    public String check(HttpSession session,DealRecord dealRecord)
    {
        //从Session对象中获取当前用户对象，也就是报销单的处理人
        Employee employee= (Employee) session.getAttribute("employee");

        dealRecord.setDealSn(employee.getEname());//设置处理人

        claimVoucherBiz.deal(dealRecord);
        return "redirect:deal";
    }
}
