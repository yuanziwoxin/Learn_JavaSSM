package com.yuan.sm.controller;

import com.yuan.sm.entity.Department;
import com.yuan.sm.entity.Staff;
import com.yuan.sm.service.DepartmentService;
import com.yuan.sm.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller("staffController")
public class StaffController{

    @Autowired
    private StaffService staffService;

    @Autowired
    private DepartmentService departmentService;

    /**
     * 员工列表显示
     * @param request
     * @param response
     */
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Staff> list=staffService.findAll();
        List<Department> departmentList=departmentService.findAll();//查询出所有部门
        request.setAttribute("SLIST",list);
        request.setAttribute("DLIST",departmentList);
        request.getRequestDispatcher("../staff_list.jsp").forward(request,response);
    }

    /**
     * 转发到添加用户页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void toAdd(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        List<Department> departmentList=departmentService.findAll();
        request.setAttribute("DLIST",departmentList);
        request.getRequestDispatcher("../staff_add.jsp").forward(request,response);
    }

    /**
     * 添加一条员工记录
     * @param request
     * @param response
     * @throws IOException
     */
    public void add(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String account=request.getParameter("account");
        Integer did=Integer.parseInt(request.getParameter("did"));//将字符转换为数字
        String name=request.getParameter("name");
        String sex=request.getParameter("sex");
        String idNumber=request.getParameter("idNumber");
        Date bornDate= null;
        try {
            //将获取的字符形式转化为日期形式
            bornDate =new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("bornDate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String info=request.getParameter("info");

        Staff staff=new Staff();//实例化一个Staff对象；

        staff.setAccount(account);
        staff.setDid(did);
        staff.setName(name);
        staff.setSex(sex);
        staff.setIdNumber(idNumber);
        staff.setBornDate(bornDate);
        staff.setInfo(info);

        staffService.add(staff);//增加一条员工记录到数据库

        response.sendRedirect("list.do");

    }

    /**
     * 转发到编辑页面
     * @param request
     * @param response
     */
    public void toEdit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        Integer id=Integer.parseInt(request.getParameter("id"));//获取要修改的员工记录的id
        Staff staff=staffService.findById(id);//获取该条要修改的员工记录
        List<Department> departmentList=departmentService.findAll();//获取所有部门记录
        request.setAttribute("STAFF",staff);
        request.setAttribute("DEPLIST",departmentList);
        request.getRequestDispatcher("../staff_edit.jsp").forward(request,response);
    }

    /**
     * 修改员工记录
     * @param request
     * @param response
     * @throws IOException
     */
    public void edit(HttpServletRequest request,HttpServletResponse response) throws IOException {

        Integer id=Integer.parseInt(request.getParameter("id"));//获取要修改的员工记录的id

        //获取修改的信息
        String account=request.getParameter("account");
        Integer did=Integer.parseInt(request.getParameter("did"));
        String name=request.getParameter("name");
        String sex=request.getParameter("sex");
        String idNumber=request.getParameter("idNumber");
        Date bornDate=null;

        try {
            bornDate=new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("bornDate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String info=request.getParameter("info");

        Staff staff=staffService.findById(id);//获取该条要修改的员工记录对象

        staff.setAccount(account);
        staff.setDid(did);
        staff.setName(name);
        staff.setSex(sex);
        staff.setIdNumber(idNumber);
        staff.setBornDate(bornDate);
        staff.setInfo(info);

        staffService.edit(staff);//将修改的信息回传到数据库中

        response.sendRedirect("list.do");//重定向到列表页面
    }

    /**
     * 删除一条员工记录
     * @param request
     * @param response
     */
    public void remove(HttpServletRequest request,HttpServletResponse response) throws IOException {
        Integer id=Integer.parseInt(request.getParameter("id"));//获取要删除员工记录的id
        staffService.remove(id);//删除员工记录
        response.sendRedirect("list.do");//重定向到员工列表显示页面
    }

    /**
     * 显示员工信息详情
     * @param request
     * @param response
     */
    public void details(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        Integer id=Integer.parseInt(request.getParameter("id"));
        Staff staff=staffService.findById(id);
        Department department=departmentService.findById(staff.getDid());
        request.setAttribute("OBJ",staff);
        request.setAttribute("DEP",department);
        request.getRequestDispatcher("../staff_details.jsp").forward(request,response);
    }
}
