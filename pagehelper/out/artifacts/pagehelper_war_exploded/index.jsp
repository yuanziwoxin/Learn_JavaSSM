<%--
  Created by IntelliJ IDEA.
  User: 刘绍源
  Date: 2018/5/2
  Time: 19:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <title>首页</title>
  </head>
  <body>
  <jsp:forward page="/servlet/PersonServlet">
      <jsp:param value="all" name="method"/>

  </jsp:forward>

  <%--<jsp:forward page="/servlet/UserServlet">--%>
    <%--<jsp:param value="all" name="method"/>--%>

  <%--</jsp:forward>--%>
  </body>
</html>
