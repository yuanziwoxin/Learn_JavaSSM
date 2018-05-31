package com.yuan.oa.biz.Impl;

import com.yuan.oa.biz.ClaimVoucherBiz;
import com.yuan.oa.dao.ClaimVoucherDao;
import com.yuan.oa.dao.ClaimVoucherItemDao;
import com.yuan.oa.dao.DealRecordDao;
import com.yuan.oa.dao.EmployeeDao;
import com.yuan.oa.entity.ClaimVoucher;
import com.yuan.oa.entity.ClaimVoucherItem;
import com.yuan.oa.entity.DealRecord;
import com.yuan.oa.entity.Employee;
import com.yuan.oa.global.Constant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("claimVoucherBiz")
public class ClaimVoucherBizImpl implements ClaimVoucherBiz {

    @Resource(name = "claimVoucherDao")
    private ClaimVoucherDao claimVoucherDao;

    @Resource(name = "claimVoucherItemDao")
    private ClaimVoucherItemDao claimVoucherItemDao;

    @Resource(name = "dealRecordDao")
    private DealRecordDao dealRecordDao;

    @Resource(name = "employeeDao")
    private EmployeeDao employeeDao;

    public void save(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items) {
        claimVoucher.setCreateTime(new Date());//设置报销单的创建时间
        //还未保存，保存的待处理人就是创建者，提交保存的信息的人是创建者
        claimVoucher.setNextDealSn(claimVoucher.getCreateSn());////设置报销单的待处理人
        claimVoucher.setStatus(Constant.CLAIMVOUCHER_CREATED);//设置报销单的状态为“新创建”
        claimVoucherDao.insert(claimVoucher);//添加报销单主要信息到报销表

        for (ClaimVoucherItem item:items)
        {
            //获取报销单的id，然后将该报销单的id赋值给报销单明细表中的报销单id字段
            item.setClaimVoucherId(claimVoucher.getId());
            claimVoucherItemDao.insert(item);//添加一条报销单明细条目信息
        }
    }

    public ClaimVoucher getOne(int cvid) {
        return claimVoucherDao.seleteOne(cvid);
    }

    public List<DealRecord> getDealRecords(int cvid) {
        return dealRecordDao.selectByClaimVoucherId(cvid);
    }

    public List<ClaimVoucherItem> getClaimVoucherItems(int cvid) {
        return claimVoucherItemDao.selectByClaimVoucherId(cvid);
    }

    public List<ClaimVoucher> getForCreater(String cname) {
        return claimVoucherDao.selectByCreater(cname);
    }

    public List<ClaimVoucher> getForDeal(String dealName) {
        return claimVoucherDao.selectByNextDealer(dealName);
    }

    public void edit(ClaimVoucher claimVoucher, List<ClaimVoucherItem> claimVoucherItems) {
        //设置报销单的创建时间，仍然是原创建时间
        claimVoucher.setCreateTime(claimVoucherDao.seleteOne(claimVoucher.getId()).getCreateTime());
        claimVoucher.setNextDealSn(claimVoucher.getCreateSn());//设置待处理人（修改后待处理人还是创建人，因为还需创建人去提交）
        claimVoucher.setStatus(Constant.CLAIMVOUCHER_CREATED);//修改后还是设置为新创建状态
        claimVoucherDao.update(claimVoucher);//修改报销单主要信息
        /*
        更新条目时注意理解，修改报销单条目一共有这几种情况：
        （1）原条目被修改了；
        （2）原条目被删除了；
        （3）增加了新条目；
         */
        //获取原报销单的所有原报销单明细条目
        List<ClaimVoucherItem> olds=claimVoucherItemDao.selectByClaimVoucherId(claimVoucher.getId());
        for (ClaimVoucherItem old:olds)//依次遍历所有原报销单明细条目
        {
            boolean isHave=false;//用于判断当前遍历的这条原报销单明细条目在修改后是否存在，不存在则删除
            //将这条原明细条目与所有修改后的新明细条目相匹配，如果该原明细条目仍存在，则设置isHave为true；
            for (ClaimVoucherItem item:claimVoucherItems)
            {
                if (item.getId()==old.getId())
                {
                    isHave=true;
                    break;
                }
            }
            if (!isHave)//如果修改后原明细条目不存在了，则删除该条原明细条目
            {
                claimVoucherItemDao.delete(old.getId());
            }
        }

        for (ClaimVoucherItem item:claimVoucherItems)
        {
            item.setClaimVoucherId(claimVoucher.getId());//设置报销单明细表中的该条报销单明细条目属于哪个报销单
            int id=item.getId();//获取当前条目的id
            if (String.valueOf(id)!=null && id>0)//如果该条报销单明细条目存在，则把修改后的结果更新到报销单明细表
            {
                claimVoucherItemDao.update(item);
            }
            else //如果不存在，则将该条目新插入到报销单明细表
            {
                claimVoucherItemDao.insert(item);
            }
        }

    }

    public void submit(int id) {
        ClaimVoucher claimVoucher=claimVoucherDao.seleteOne(id);
        /*
        注意：
        这里是不能使用claimVoucher.getCreater()的形式获取报销单的创建人的，
        因为控制层并没有为这个claimVoucher对象的creater这个对象属性赋值的，
        所以claimVoucher.getCreater()得到的是空对象，
        我们应该从数据库中获取该报销单的创建人，
         */
        Employee employee=employeeDao.selectOne(claimVoucher.getCreateSn());//获取创建人
        String deptName=employee.getDepartmentName();//获取创建人所在部门编号

        claimVoucher.setStatus(Constant.CLAIMVOUCHER_SUBMIT);//修改报销单状态为已提交
        /*
        设置待处理人（先传入部门编号和职务获得待处理的编号）
         注意：
         （1）因为employeeDao.selectByDepartmentAndPost()方法获取的是一个集合，
             所以我们要获取它的第一个元素（用get(0)获取），其实也只有一个元素（一个部门只有一个部门经理）
         */
        claimVoucher.setNextDealSn(employeeDao.selectByDepartmentAndPost(deptName,Constant.POST_FM).get(0).getEname());

        claimVoucherDao.update(claimVoucher);//(1)更新报销单信息

        DealRecord dealRecord=new DealRecord();//实例化一个处理记录对象
        dealRecord.setClaimVoucherId(id);//设置报销单id
        dealRecord.setDealSn(employee.getEname());//设置处理人(提交的人就是创建人)
        dealRecord.setDealTime(new Date());//设置处理时间
        dealRecord.setDealWay(Constant.DEAL_SUBMIT);//设置处理类型为提交
        dealRecord.setDealResult(Constant.CLAIMVOUCHER_SUBMIT);//设置处理结果为已提交
        dealRecord.setComment("无");

        dealRecordDao.insert(dealRecord);//(2)添加（保存）处理记录

    }
    //报销单处理
    public void deal(DealRecord dealRecord) {
        //根据报销单编号获取报销单对象
        ClaimVoucher claimVoucher=claimVoucherDao.seleteOne(dealRecord.getClaimVoucherId());
        //获取处理人对象
        Employee dealer=employeeDao.selectOne(dealRecord.getDealSn());
        //设置处理时间（因为后面无论是谁处理，都需要设置，所以提到这里统一设置）
        dealRecord.setDealTime(new Date());
        //获取报销单处理的状态（类型）：通过、打回、拒绝、打款
        String dealWay=dealRecord.getDealWay();

        /*
        如果报销单的处理类型（状态）是审核通过,主要分两种：
        (1)
        a.报销总金额小于部门经理处理额度，部门经理审核通过
        b.报销总金额大于部门经理处理额度，总经理审核通过；
        则这两种情况统一都是报销单状态和处理结果都修改为“已审核”，并交给财务处理；
        （2）如果报销金额大于部门经理处理额度，部门经理审核通过，
        则修改报销单状态和处理结果都修改为“待复审”，并上交给总经理复审；
         */
        if (dealWay.equals(Constant.DEAL_PASS))
        {
            /*
                (1)
                a.报销总金额小于部门经理处理额度，部门经理审核通过
                b.报销总金额大于部门经理处理额度，总经理审核通过；
                则这两种情况统一都是报销单状态和处理结果都修改为“已审核”，并交给财务处理；
            */
            if (claimVoucher.getTotalAmount()<=Constant.LIMIT_CHECK || dealer.getPost().equals(Constant.POST_GM))
            {
                //设置报销单的状态为已审核
                claimVoucher.setStatus(Constant.CLAIMVOUCHER_APPROVED);
                //设置待处理人为财务（这里不用管是哪个部门的财务，只要交给财务处理即可---这也是一个可拓展完善的地方：若每个部门都有财务或所有财务都属于一个部门）
                claimVoucher.setNextDealSn(employeeDao.selectByDepartmentAndPost(null,Constant.POST_CASHIER).get(0).getEname());

                //设置处理的结果为已审核
                dealRecord.setDealResult(Constant.CLAIMVOUCHER_APPROVED);
            }
            else //(2)如果报销金额大于部门经理处理额度，且部门经理审核通过
            {
                //设置报销单的状态为待复审
                claimVoucher.setStatus(Constant.CLAIMVOUCHER_RECHECK);
                //设置待处理人为总经理（总经理只有一个，所以可以通过get(0)的方法从集合中获取）
                claimVoucher.setNextDealSn(employeeDao.selectByDepartmentAndPost(null,Constant.POST_GM).get(0).getEname());
                //设置处理的结果为待复审
                dealRecord.setDealResult(Constant.CLAIMVOUCHER_RECHECK);
            }
        }
        else if (dealWay.equals(Constant.DEAL_BACK))
        {//如果报销单的处理类型（状态）是打回
            //设置报销单的状态为已打回
            claimVoucher.setStatus(Constant.CLAIMVOUCHER_BACK);
            //设置待处理人为报销单的创建人
            claimVoucher.setNextDealSn(claimVoucher.getCreateSn());

            //设置处理的结果为已审核
            dealRecord.setDealResult(Constant.CLAIMVOUCHER_BACK);
        }
        else if (dealWay.equals(Constant.DEAL_REJECT))
        {//如果报销单的处理类型是拒绝
            //设置报销单的状态为已拒绝
            claimVoucher.setStatus(Constant.CLAIMVOUCHER_TERMINATED);
            //设置待处理人为空（即拒绝了也就没有了待处理人了）
            claimVoucher.setNextDealSn(null);

            //设置处理的结果为已拒绝
            dealRecord.setDealResult(Constant.CLAIMVOUCHER_TERMINATED);
        }
        else if (dealWay.equals(Constant.DEAL_PAID))
        {//如果报销单的处理类型是打款
            //设置报销单的状态为已打款
            claimVoucher.setStatus(Constant.CLAIMVOUCHER_PAID);
            //设置待处理人为空（即打完款，也就完成了所有处理，也就没有了待处理人）
            claimVoucher.setNextDealSn(null);

            //设置处理的结果为已打款
            dealRecord.setDealResult(Constant.CLAIMVOUCHER_PAID);
        }


        claimVoucherDao.update(claimVoucher);//更新报销单主要信息
        dealRecordDao.insert(dealRecord);//添加处理记录

    }

}
