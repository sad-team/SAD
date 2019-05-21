<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%
    String userName = (session.getAttribute("userName") == null) ? "" : (String)session.getAttribute("userName");
    String phoneNumber = (request.getParameter("phoneNumber") == null)
            ? "" : request.getParameter("phoneNumber");
    String email = (request.getParameter("email") == null)
            ? "" : request.getParameter("email");
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
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
    <p class="userInfoTitle">个人信息</p>

                    <form class="form-horizontal" role="form" action="ChangeInfo" method="post">
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="name">用户名</label>
                            <div class="col-sm-6">
                                <input class="form-control" id="name" placeholder="<%=userName%>"
                                       type="text" name="userName">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="tel">手机号</label>
                            <div class="col-sm-6">
                                <input class="form-control" id="tel" placeholder="<%=phoneNumber%>"
                                       type="tel" name="phoneNumber">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="email">邮箱</label>
                            <div class="col-sm-6">
                                <input class="form-control" id="email" placeholder="<%=email%>"
                                       type="email" name="email">
                            </div>
                        </div>


                        <div class="form-group">
                            <div class="col-sm-offset-4 col-sm-6">
                                <button class="btn btn-primary" type="submit">确认</button>
                            </div>
                        </div>
                    </form>

    <div style="margin-top: 30px; text-align: center">
        <a href="index.jsp">返回</a>
    </div>

</div>
<!-- JavaScript 放置在文档最后面可以使页面加载速度更快 -->
<!-- 可选: 包含 jQuery 库 -->
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<!-- 可选: 合并了 Bootstrap JavaScript 插件 -->
<script src="https://apps.bdimg.com/libs/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</body>
</html>