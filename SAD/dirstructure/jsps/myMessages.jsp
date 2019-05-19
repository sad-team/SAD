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
    <img src="images/resource.jpg" width="100%" height="100%"/>
</div>

<div id="Layer2" style="position: fixed; left: 0px; top: 0px; width: 100%; height: 540px">
    <p class="searchTitle">我的消息</p>
    <div class="container" id="Layer3" style="width: 100%; height: 50%">
        <table class="table table-hover">
            <caption class="searchSubtitle tableBg"></caption>
            <thead class="titleBg">
            <tr>
                <th>标题</th>
                <th>来自</th>
                <th>时间</th>
            </tr>
            </thead>
            <tbody class="resultBg">
            <tr>
                <td>hello</td>
                <td>Bangalore</td>
                <td>2019-3-9</td>
            </tr>
            <tr>
                <td>hi</td>
                <td>Mumbai</td>
                <td>2019-3-10</td>
            </tr>
            <tr>
                <td>welcome</td>
                <td>Pune</td>
                <td>2019-3-11</td>
            </tr>
            </tbody>
        </table>
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