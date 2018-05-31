<%--
  Created by IntelliJ IDEA.
  User: Administrator.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
        body{
            overflow-x:hidden;
        }
        #main{
            width:1200px;
            height:600px;
            margin-left:500px;
        }
    </style>
</head>
<body>

<div id="main">
    <!-- 标题 -->
    <div class="row">
        <div class="col-md-12">
            <h1>添加课程</h1>
        </div>
    </div>

    <form class="form-horizontal" role="form" action="mapType" method="post">
        <div class="form-group">
            <label class="col-sm-1 control-label">课程1编号</label>
            <div class="col-sm-3">
                <!--courses['one'].id 这里的one表示Map<String,Course>的key值-->
                <input type="text" class="form-control" name="courses['one'].id" placeholder="请输入课程编号">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-1 control-label">课程1名称</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" name="courses['one'].name" placeholder="请输入课程名称">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-1 control-label">课程1价格</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" name="courses['one'].price" placeholder="请输入课程价格">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-1 control-label">讲师编号</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" name="courses['one'].author.id" placeholder="请输入讲师编号">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-1 control-label">讲师姓名</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" name="courses['one'].author.name" placeholder="请输入讲师姓名">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-1 control-label">课程2编号</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" name="courses['two'].id" placeholder="请输入课程编号">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-1 control-label">课程2名称</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" name="courses['two'].name" placeholder="请输入课程名称">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-1 control-label">课程2价格</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" name="courses['two'].price" placeholder="请输入课程价格">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-1 control-label">讲师编号</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" name="courses['two'].author.id" placeholder="请输入讲师编号">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-1 control-label">讲师姓名</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" name="courses['two'].author.name" placeholder="请输入讲师姓名">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-1 col-sm-3">
                <button type="submit" class="btn btn-default">提交</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
