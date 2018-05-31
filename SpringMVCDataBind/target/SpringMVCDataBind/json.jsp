<%--
  Created by IntelliJ IDEA.
  User: Administrator.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
 $(function () {
     var course={
         "id":"1",
         "name":"SpringMVC初级",
         "price":"299"
     };
     /*
     (1)JSON.stringify() 方法是将一个JavaScript值(对象或者数组)转换为一个 JSON字符串
     (2)contentType:"application/json;charset=UTF-8"表示告诉当前服务发送的是一个json格式的数据
     (3)dataType:"json" 是服务器返回的数据格式，即后台的course对象被解析为json格式返回给前台
      */
     $.ajax({
             url:"jsonType",
             data:JSON.stringify(course),
             type:"post",
             contentType:"application/json;charset=UTF-8",
             dataType:"json",
             //表示成功回调函数
             success:function(data){
                 alert(data.name+":"+data.price);
             }
         })
 })
</script>
<body>

</body>
</html>
