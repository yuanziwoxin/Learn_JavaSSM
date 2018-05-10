<%--
  Created by IntelliJ IDEA.
  User: 刘绍源
  Date: 2018/4/25
  Time: 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  <!--用于迭代-->
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <!--用于格式转换-->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>部门列表</title>
    <link rel="stylesheet" type="text/css" href="../css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="../css/common.css"/>
    <link rel="stylesheet" type="text/css" href="../css/thems.css">
    <script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript">
        $(function(){
            //自适应屏幕宽度
            window.onresize=function(){ location=location };

            var main_h = $(window).height();
            $('.hy_list').css('height',main_h-45+'px');

            var search_w = $(window).width()-40;
            $('.search').css('width',search_w+'px');
            //$('.list_hy').css('width',search_w+'px');
        });
    </script>
    <!--框架高度设置-->
</head>

<body onLoad="Resize();">
<div id="right_ctn">
    <div class="right_m">
        <div class="hy_list">
            <div class="box_t">
                <span class="name">部门列表</span>
            </div>
            <div class="space_hx">&nbsp;</div>
            <!--列表-->
            <table cellpadding="0" cellspacing="0" class="list_hy">
                <tr>
                    <th scope="col">姓名</th>
                    <th scope="col">性别</th>
                    <th scope="col">出生日期</th>
                    <th scope="col">入职日期</th>
                    <th scope="col">部门</th>
                    <th scope="col">状态</th>
                    <th scope="col">操作</th>
                </tr>
                <c:forEach items="${SLIST}" var="staff">
                    <tr>
                        <td>${staff.name}</td>
                        <td>${staff.sex}</td>
                        <td><fmt:formatDate value="${staff.bornDate}" pattern="yyyy-MM-dd"/> </td>
                        <td><fmt:formatDate value="${staff.workTime}" pattern="yyyy-MM-dd hh:mm"/> </td>
                        <!--这里之所以可以直接使用“staff.department.name”获得部门名称，
                        是因为StaffDao.xml中resultMap中的association已经通过staff的did关联到了department对象-->
                        <td>${staff.department.name}</td>
                        <td>${staff.status}</td>
                        <td>
                            <a href="toEdit.do?id=${staff.id}" class="btn">编辑</a>
                            <a href="remove.do?id=${staff.id}" class="btn">删除</a>
                            <a href="details.do?id=${staff.id}" class="btn">查看</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <!--列表-->
            <!--右边底部-->
            <div class="r_foot">
                <div class="r_foot_m">
                    <a href="toAdd.do" class="btn">添加</a>
                </div>
            </div>
            <!--右边底部-->
        </div>
        <!--会议列表-->
    </div>
</div>
</body>
</html>