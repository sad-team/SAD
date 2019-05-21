<%@ page contentType="text/html; charset=utf-8" %>
<%
    String status = (request.getParameter("status") == null)
            ? "" : request.getParameter("status");
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
        .loginTitle
        {
            font:42px arial,sans-serif;
            color: snow;
            text-align: center;
        }

        .loginStatus
        {
            font:21px arial,sans-serif;
            color: aliceblue;
            text-align: center;
        }
        ul {
            display: flex;
            /*flex-direction: row;*/
            /*flex-wrap: nowrap;*/
            flex-flow: row nowrap;
            justify-content: center;
        }
        ul li {
            list-style: none;
            text-align: center;
            line-height: 40px;
            height: 40px;
            width: 100px;
            margin: 0px;
        }

    </style>
</head>
<body>

<div id="Layer2" style="position: fixed; left: 0px; top: 0px; width: 100%; height: 540px">
    <div class="container" style="text-align: center">

        <div id="Layer1" style="position: fixed; left: 0px; top: 0px; width: 100%; height: 80%">

            <div class="row" style="text-align: center; width: 100%" >
                 <label class="loginTitle label-primary" style="text-align: center; width: 90%">SAD</label>
            </div>

            <ul id="myTab" class="nav nav-tabs" >
                    <li class="active">
                        <a href="#login" data-toggle="tab">登录</a>
                    </li>
                    <li><a href="#signUp" data-toggle="tab">注册</a></li>
            </ul>

            <div id="myTabContent" class="tab-content">
                <div class="tab-pane fade in active" id="login">
                    <div class="row" style="text-align: center; width: 100%" >
                        <label class="loginStatus label-default" style="text-align: center; width: 30%">
                            <%=status%>
                        </label>
                    </div>

                    <form id="loginForm" class="form-horizontal" role="form" action="Login" method="post">
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="name">用户名</label>
                            <div class="col-sm-6">
                                <input class="form-control" id="name" placeholder="用户名"
                                       type="text" name="signInName">
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="signInPasswd">密码</label>
                            <div class="col-sm-6">
                                <input class="form-control" id="signInPasswd" placeholder="请输入密码"
                                       type="password" name="signInPasswd">
                            </div>
                        </div>

                        <div class="form-group form-inline" id="identfyingCode">
                            <span> <label class="col-sm-4 control-label" style="text-align: end">验证码</label> </span>
                            <div class="col-sm-8">
                                <label>
                                    <input id="checkCode"  class="col-sm-3 form-control" placeholder="请输入图形验证码" type="text"/>
                                </label>
                                <canvas class="col-sm-3" height="45 " id="canvas" width="120"></canvas>
                                <a class="col-sm-2" href="#" id="changeImg">看不清<br>换一张</a>
                            </div>
                        </div>

                        <div class="form-group form-inline" style="text-align: center;">
                            <div class="col-sm-2 col-xs-offset-4">
                                <button class="btn btn-primary" onclick="check()">登录</button>
                            </div>

                            <div class="col-sm-2">
                                <a href="index.html" class="btn btn-default">返回</a>
                            </div>
                        </div>

                    </form>
                </div>

                <div class="row" style="text-align: center; width: 100%" >
                    <label class="loginStatus label-default" style="text-align: center; width: 30%">

                    </label>
                </div>

                <div class="tab-pane fade" id="signUp">
                    <form class="form-horizontal" role="form" action="SignUp" method="post">
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="signUpName">用户名</label>
                            <div class="col-sm-6">
                                <input class="form-control" id="signUpName" placeholder="请输入用户名"
                                       type="text" name="signUpName">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="signUpPasswd">设置密码</label>
                            <div class="col-sm-6">
                                <input class="form-control" id="signUpPasswd" placeholder="密码为6-15个字符"
                                       type="password" name="signUpPasswd">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="signUpEmail">您的邮箱</label>
                            <div class="col-sm-6">
                                <input class="form-control" id="signUpEmail" placeholder="输入正确的邮箱地址"
                                       type="email" name="signUpEmail">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="cellphoneNumber">您的手机号</label>
                            <div class="col-sm-6">
                                <input class="form-control" id="cellphoneNumber" placeholder="输入您的手机号码"
                                       type="tel" name="cellphoneNumber">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="identification">您的身份证号码</label>
                            <div class="col-sm-6">
                                <input class="form-control" id="identification" placeholder="输入您的身份证号码"
                                       type="number" name="identification">
                            </div>
                        </div>

                        <div class="form-group form-inline" id="identfyingCode2">

                            <span> <label class="col-sm-4 control-label" style="text-align: end">验证码</label> </span>
                            <div class="col-sm-8">
                                <label>
                                    <input id="checkCode2" class="col-sm-3 form-control" placeholder="请输入图形验证码" type="text"/>
                                </label>
                                <canvas class="col-sm-3" height="45 " id="canvas2" width="120"></canvas>
                                <a class="col-sm-2" href="#" id="changeImg2">看不清<br>换一张</a>
                            </div>
                        </div>

                        <div class="form-group">
                            <div style="text-align: center">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox"> 我已阅读并同意隐私条款
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group form-inline" style="text-align: center;">
                            <div class="col-sm-2 col-xs-offset-4">
                                <button class="btn btn-primary" onclick="check2()">注册</button>
                            </div>

                            <div class="col-sm-2">
                                <a href="index.html" class="btn btn-default">返回</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>




        </div>

    </div>


</div>

<script>
    $(function () {
        $('#myTab li:eq(0) a').tab('show');
    });
</script>

<script>

    $(function () {
        $("#siginInButton.btn").click(function () {
            drawPic("canvas2")

        });
    });
    $(function () {
        $("#signUpButton.btn").click(function () {
            drawPic("canvas")
        });
    });

    /**生成一个随机数**/
    function randomNum(min, max) {
        return Math.floor(Math.random() * (max - min) + min);
    }

    /**生成一个随机色**/
    function randomColor(min, max) {
        var r = randomNum(min, max);
        var g = randomNum(min, max);
        var b = randomNum(min, max);
        return "rgb(" + r + "," + g + "," + b + ")";
    }

    drawPic("canvas");
    drawPic("canvas2");
    document.getElementById("changeImg").onclick = function (e) {
        e.preventDefault();
        drawPic("canvas");
    }
    document.getElementById("changeImg2").onclick = function (e) {
        e.preventDefault();
        drawPic("canvas2");
    }

    var code1 = "";
    var code2 = "";
    /**绘制验证码图片**/
    function drawPic(canvasID) {
        var canvas = document.getElementById(canvasID);
        var width = canvas.width;
        var height = canvas.height;
        var ctx = canvas.getContext('2d');
        ctx.textBaseline = 'bottom';

        /**绘制背景色**/
        ctx.fillStyle = randomColor(180, 240); //颜色若太深可能导致看不清
        ctx.fillRect(0, 0, width, height);
        /**绘制文字**/
        var str = 'ABCEFGHJKLMNPQRSTWXYZabcdefghijklmnopqrstuvwxyz0123456789';
        for (var i = 0; i < 4; i++) {
            var txt = str[randomNum(0, str.length)];
            ctx.fillStyle = randomColor(50, 160);  //随机生成字体颜色
            ctx.font = randomNum(15, 40) + 'px SimHei'; //随机生成字体大小
            var x = 10 + i * 25;
            var y = randomNum(25, 45);
            var deg = randomNum(-45, 45);
            //修改坐标原点和旋转角度
            ctx.translate(x, y);
            ctx.rotate(deg * Math.PI / 180);
            ctx.fillText(txt, 0, 0);
            //恢复坐标原点和旋转角度
            ctx.rotate(-deg * Math.PI / 180);
            ctx.translate(-x, -y);
        }
        if(canvasID === "canvas") {
            code1 = ctx.text;
        } else {
            code2 = ctx.text;
        }


        /**绘制干扰线**/
        for (var i = 0; i < 8; i++) {
            ctx.strokeStyle = randomColor(40, 180);
            ctx.beginPath();
            ctx.moveTo(randomNum(0, width), randomNum(0, height));
            ctx.lineTo(randomNum(0, width), randomNum(0, height));
            ctx.stroke();
        }
        /**绘制干扰点**/
        for (var i = 0; i < 100; i++) {
            ctx.fillStyle = randomColor(0, 255);
            ctx.beginPath();
            ctx.arc(randomNum(0, width), randomNum(0, height), 1, 0, 2 * Math.PI);
            ctx.fill();
        }
    }

    function check() {
        var inputCode = document.getElementById("checkCode").text;
        if(code1!==inputCode){
            document.getElementById("checkCode").style.color = "red";
        } else {
           var form = document.getElementById("loginForm");
           form.submit();
        }
    }

    function check2() {
        document.getElementById("checkCode2");
    }
</script>


</body>
</html>