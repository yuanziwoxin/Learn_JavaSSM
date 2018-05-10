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
    <title>${TYPE}日志列表</title>
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
                <span class="name">${TYPE}日志</span>
            </div>
            <div class="space_hx">&nbsp;</div>
            <!--列表-->
            <table cellpadding="0" cellspacing="0" class="list_hy">
                <tr>
                    <th scope="col">操作时间</th>
                    <th scope="col">操作人</th>
                    <th scope="col">模块</th>
                    <th scope="col">操作</th>
                    <th scope="col">结果</th>
                </tr>
                <c:forEach items="${LIST}" var="log">
                    <tr>
                        <td><fmt:formatDate value="${log.oprTime}" pattern="yyyy-MM-dd hh:mm:ss"/> </td>
                        <td>${log.operator}</td>
                        <td>${log.module}</td>
                        <td>${log.operation}</td>
                        <td>${log.result}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <!--会议列表-->
    </div>
</div>
</body>
</html>