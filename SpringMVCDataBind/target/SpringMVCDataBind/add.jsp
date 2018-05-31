<%--
  Created by IntelliJ IDEA.
  User: 刘绍源
  Date: 2018/5/10
  Time: 19:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
        body{
            overflow-x: hidden;
        }
    </style>
</head>
<body>
    <form class="form-horizontal" role="form" id="add" name="add" action="pojoType" method="post">
        <div class="form-group">
            <label class="col-sm-1 control-label">课程编号：</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" name="id" placeholder="请输入课程编号">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-1 control-label">课程名称：</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" name="name" placeholder="请输入课程名称">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-1 control-label">课程价格：</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" name="price" placeholder="请输入商品价格">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-1 control-label">讲师编号：</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" name="author.id" placeholder="请输入讲师编号">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-1 control-label">讲师姓名：</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" name="author.name" placeholder="请输入讲师姓名">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-1 colo-sm-3">
                <button type="submit" class="btn btn-default">提交</button>
            </div>
        </div>
    </form>
</body>
</html>
