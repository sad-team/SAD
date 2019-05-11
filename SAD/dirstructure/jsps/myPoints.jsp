<%@ page contentType="text/html; charset=utf-8" %>
<%
    String myPoints = (request.getParameter("userPoint") == null)
            ? "" : request.getParameter("userPoint");
    String userName = (request.getParameter("userName") == null)
            ? "" : request.getParameter("userName");
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        p.pointsTitle
        {
            font:42px arial,sans-serif;
            color: snow;
            text-align: center;
        }

        .border
        {
            border: 1px solid #000;
        }
    </style>
</head>
<body>

<div id="Layer1" style="position:fixed; left:0px; bottom:0px; width:100%; height:100%">
    <img src="images/points.jpg" width="100%" height="100%"/>
</div>

<div id="Layer2" style="position: fixed; left: 0px; top: 0px; width: 100%; height: 540px">
    <p class="pointsTitle">我的积分</p>
    <div class="container" style="text-align: center">
        <div class="row" id="row1" style="height: 180px">
            <div class="col-sm-4 border col-sm-offset-4 bg-info " style="height: 100%"> 积分余额 <br><br><%=myPoints%></div>
        </div>


    </div>

    <div style="margin-top: 30px; text-align: center">
        <form action="Back" method="post">
            <input type="hidden" name="userName" value="<%=userName%>">
            <button class="btn-default" type="submit">返回</button>
        </form>
    </div>
</div>



<!-- JavaScript 放置在文档最后面可以使页面加载速度更快 -->
<!-- 可选: 包含 jQuery 库 -->
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<!-- 可选: 合并了 Bootstrap JavaScript 插件 -->
<script src="https://apps.bdimg.com/libs/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</body>
</html>