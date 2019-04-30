<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
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
        p.searchTitle
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
    <img src="images/news.jpg" width="100%" height="100%"/>
</div>

<div id="Layer2" style="position: fixed; left: 0px; top: 0px; width: 100%; height: 540px">
    <p class="searchTitle">科研资讯</p>
    <div class="container" style="text-align: center">
        <div class="row bg-info border" id="row1" style="height: 180px">
             <div class="col-sm-4 border" style="height: 100%"> news1 </div>
             <div class="col-sm-4 border" style="height: 100%"> news2 </div>
             <div class="col-sm-4 border" style="height: 100%"> news3 </div>
        </div>

        <div class="row bg-primary border" id="row2" style="height: 180px">
             <div class="col-sm-4 border" style="height: 100%"> news4 </div>
             <div class="col-sm-4 border" style="height: 100%"> news5 </div>
             <div class="col-sm-4 border" style="height: 100%"> news6 </div>
        </div>

        <div class="row bg-success border" id="row3" style="height: 180px">
             <div class="col-sm-4 border" style="height: 100%"> news7 </div>
             <div class="col-sm-4 border" style="height: 100%"> news8 </div>
             <div class="col-sm-4 border" style="height: 100%"> news9 </div>
        </div>
    </div>

    <div style="margin-top: 30px; text-align: center">
        <a href="index.html" class="btn btn-default">返回</a>
    </div>
</div>



<!-- JavaScript 放置在文档最后面可以使页面加载速度更快 -->
<!-- 可选: 包含 jQuery 库 -->
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<!-- 可选: 合并了 Bootstrap JavaScript 插件 -->
<script src="https://apps.bdimg.com/libs/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</body>
</html>