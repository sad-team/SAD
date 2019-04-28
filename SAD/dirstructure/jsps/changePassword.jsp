<%@ page contentType="text/html; charset=utf-8" %>
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
        .userInfoTitle
        {
            font:36px arial,sans-serif;
            color: midnightblue;
            text-align: center;
        }

    </style>
</head>
<body>

<div id="Layer1" style="position: fixed; left: 0px; top: 0px; width: 100%; height: 20%">
    <p class="userInfoTitle">修改密码</p>

    <form class="form-horizontal" role="form">
        <div class="form-group">
            <label class="col-sm-2 control-label" for="oldPassword">请输入旧密码</label>
            <div class="col-sm-10">
                <input class="form-control" id="oldPassword" placeholder=""
                       type="password">
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label" for="newPassword">请输入新密码</label>
            <div class="col-sm-10">
                <input class="form-control" id="newPassword" placeholder=""
                       type="password">
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label" for="confirmPassword">请确认新密码</label>
            <div class="col-sm-10">
                <input class="form-control" id="confirmPassword" placeholder=""
                       type="password">
            </div>
        </div>


        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button class="btn btn-primary" type="submit">确认</button>
            </div>
        </div>
    </form>


    <div class="modal-footer">
        <a href="../htmls/index.html" class="btn btn-default">返回</a>
    </div>
</div>
<!-- JavaScript 放置在文档最后面可以使页面加载速度更快 -->
<!-- 可选: 包含 jQuery 库 -->
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<!-- 可选: 合并了 Bootstrap JavaScript 插件 -->
<script src="https://apps.bdimg.com/libs/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</body>
</html>