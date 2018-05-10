<%--
  Created by IntelliJ IDEA.
  User: 刘绍源
  Date: 2018/5/2
  Time: 22:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>My JSP 'users.jsp' starting page</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->

</head>
<body>
 <table align="center" width="80%" border="1">
     <tr><td>id</td><td>username</td><td>email</td></tr>

     <c:forEach items="${requestScope.persons}" var="person">
         <tr><td>${person.id}</td><td>${person.username}</td><td>${person.email}</td></tr>
     </c:forEach>


 </table>



 <div align="center">

     共${requestScope.pageInfo.total}条/共${requestScope.pageInfo.pages}页
     <a href="${pageContext.request.contextPath}/servlet/PersonServlet?method=all&num=1">首页</a>

     <a href="${pageContext.request.contextPath}/servlet/PersonServlet?method=all&num=${requestScope.pageInfo.prePage}">上一页</a>
     <c:forEach  items="${requestScope.pageInfo.navigatepageNums}" var="num">
         <a href="${pageContext.request.contextPath}/servlet/PersonServlet?method=all&num=${num}">${num}</a>

     </c:forEach>

     <a href="${pageContext.request.contextPath}/servlet/PersonServlet?method=all&num=${requestScope.pageInfo.nextPage}">下一页</a>
     <a href="${pageContext.request.contextPath}/servlet/PersonServlet?method=all&num=${requestScope.pageInfo.pages}">末页</a>

     跳转到
     <input id="number" type="text" name="hello" size="6">页<input type="button" value="跳转" onclick="changeNumber()"></input>

     <script>
         function changeNumber()
         {
             var num=document.getElementById("number").value;

             //是否是数字  输入的数字一定是整数或者是小于总页数的值
             window.location.href="${pageContext.request.contextPath}/servlet/PersonServlet?method=all&num="+num;

         }
     </script>


 </div>
</body>
</html>
