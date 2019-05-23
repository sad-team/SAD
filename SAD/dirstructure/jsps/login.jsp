<%@ page contentType="text/html; charset=utf-8" %>
<%
String status = (request.getParameter("status") == null)
? "" : request.getParameter("status");
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
    <meta content="width=device-width, initial-scale=1" name="viewport">
    <meta name="description" content="BHost template project">
    <link rel="stylesheet" type="text/css" href="styles/bootstrap-4.1.2/bootstrap.min.css">
    <link href="plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.carousel.css">
    <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
    <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/animate.css">
    <link rel="stylesheet" type="text/css" href="styles/main_styles.css">
    <link rel="stylesheet" type="text/css" href="styles/responsive.css">
    <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Open+Sans:600'>

    <style>
        body{
            margin:0;
            color:#6a6f8c;
            background:#c8c8c8;
            font:600 16px/18px 'Open Sans',sans-serif;
        }
        *,:after,:before{box-sizing:border-box}
        .clearfix:after,.clearfix:before{content:'';display:table}
        .clearfix:after{clear:both;display:block}
        a{color:inherit;text-decoration:none}

        .login-wrap{
            width:100%;
            margin:auto;
            max-width:525px;
            min-height:670px;
            position:relative;
            background:url(https://raw.githubusercontent.com/khadkamhn/day-01-login-form/master/img/bg.jpg) no-repeat center;
            box-shadow:0 12px 15px 0 rgba(0,0,0,.24),0 17px 50px 0 rgba(0,0,0,.19);
        }
        .login-html{
            width:100%;
            height:100%;
            position:absolute;
            padding:90px 70px 50px 70px;
            background:rgba(40,57,101,.9);
        }
        .login-html .sign-in-htm,
        .login-html .sign-up-htm{
            top:0;
            left:0;
            right:0;
            bottom:0;
            position:absolute;
            -webkit-transform:rotateY(180deg);
            transform:rotateY(180deg);
            -webkit-backface-visibility:hidden;
            backface-visibility:hidden;
            transition:all .4s linear;
        }
        .login-html .sign-in,
        .login-html .sign-up,
        .login-form .group .check{
            display:none;
        }
        .login-html .tab,
        .login-form .group .label,
        .login-form .group .button{
            text-transform:uppercase;
        }
        .login-html .tab{
            font-size:22px;
            margin-right:15px;
            padding-bottom:5px;
            margin:0 15px 10px 0;
            display:inline-block;
            border-bottom:2px solid transparent;
        }
        .login-html .sign-in:checked + .tab,
        .login-html .sign-up:checked + .tab{
            color:#fff;
            border-color:#1161ee;
        }
        .login-form{
            min-height:345px;
            position:relative;
            -webkit-perspective:1000px;
            perspective:1000px;
            -webkit-transform-style:preserve-3d;
            transform-style:preserve-3d;
        }
        .login-form .group{
            margin-bottom:15px;
        }
        .login-form .group .label,
        .login-form .group .input,
        .login-form .group .button{
            width:100%;
            color:#fff;
            display:block;
        }
        .login-form .group .input,
        .login-form .group .button{
            border:none;
            padding:15px 20px;
            border-radius:25px;
            background:rgba(255,255,255,.1);
        }
        .login-form .group input[data-type="password"]{
            text-security:circle;
            -webkit-text-security:circle;
        }
        .login-form .group .label{
            color:#aaa;
            font-size:12px;
        }
        .login-form .group .button{
            background:#1161ee;
        }
        .login-form .group label .icon{
            width:15px;
            height:15px;
            border-radius:2px;
            position:relative;
            display:inline-block;
            background:rgba(255,255,255,.1);
        }
        .login-form .group label .icon:before,
        .login-form .group label .icon:after{
            content:'';
            width:10px;
            height:2px;
            background:#fff;
            position:absolute;
            transition:all .2s ease-in-out 0s;
        }
        .login-form .group label .icon:before{
            left:3px;
            width:5px;
            bottom:6px;
            -webkit-transform:scale(0) rotate(0);
            transform:scale(0) rotate(0);
        }
        .login-form .group label .icon:after{
            top:6px;
            right:0;
            -webkit-transform:scale(0) rotate(0);
            transform:scale(0) rotate(0);
        }
        .login-form .group .check:checked + label{
            color:#fff;
        }
        .login-form .group .check:checked + label .icon{
            background:#1161ee;
        }
        .login-form .group .check:checked + label .icon:before{
            -webkit-transform:scale(1) rotate(45deg);
            transform:scale(1) rotate(45deg);
        }
        .login-form .group .check:checked + label .icon:after{
            -webkit-transform:scale(1) rotate(-45deg);
            transform:scale(1) rotate(-45deg);
        }
        .login-html .sign-in:checked + .tab + .sign-up + .tab + .login-form .sign-in-htm{
            -webkit-transform:rotate(0);
            transform:rotate(0);
        }
        .login-html .sign-up:checked + .tab + .login-form .sign-up-htm{
            -webkit-transform:rotate(0);
            transform:rotate(0);
        }

        .hr{
            height:2px;
            margin:60px 0 50px 0;
            background:rgba(255,255,255,.2);
        }
        .foot-lnk{
            text-align:center;
        }
    </style>


</head>
<body>
<div class="super_container">
    <header class="header trans_400">
        <div class="header_content d-flex flex-row align-items-center justify-content-start trans_400">
            <div class="logo"><a href="#"><span>S</span>AD</a></div>
            <div class="container">
                <div class="row">
                    <div class="col-lg-10 offset-lg-2">
                        <nav class="main_nav">
                            <ul class="d-flex flex-row align-items-center justify-content-start">
                                <li><a href="index.jsp">首页</a></li>
                                <li><a href="searchScholar.jsp">专家</a></li>
                                <li><a href="news.jsp">资讯</a></li>
                                <li><a href="community.jsp">社区</a></li>
                                <li><a href="dynamic.jsp">动态</a></li>
                                <li><a href="myMessages.jsp">消息</a></li>
                                <li><a href="recharge.jsp">充值</a></li>
                                <li class="dropdown">
                                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">我<b class="caret"></b>
                                    </a>
                                    <ul class="dropdown-menu" style="background-color: rgba(18,5,52,0.69)">
                                        <li><a href="#" onclick="turnTo('QueryUserInfo')">个人信息</a></li>

                                        <li><a href="#" onclick="turnTo('OrderHistory')">历史订单</a></li>

                                        <li><a href="#" onclick="turnTo('MyPoint')">我的积分</a></li>

                                        <li ><a href="myFollows.jsp">我的关注</a></li>

                                        <li><a href="#" onclick="turnTo('MyResource')">我的资源</a></li>
                                    </ul>
                                </li>

                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
            <div class="header_right d-flex flex-row align-items-center justify-content-start">

                <div class="phone d-flex flex-row align-items-center justify-content-start">
                    <i class="fa " aria-hidden="true"></i>
                    <div>
                        <a href="login.jsp" style="color: yellow">登录/注册</a>
                    </div>
                </div>

                <!-- Hamburger -->
                <div class="hamburger"><i class="fa fa-bars" aria-hidden="true"></i></div>
            </div>
        </div>
    </header>

    <div class="home"  style="overflow-y: auto">
        <div class="parallax_background parallax-window" data-parallax="scroll" data-image-src="images/index.jpg" data-speed="0.8"></div>
        <div class="home_container">
            <div style="text-align: center">
                <%=status%>
            </div>
            <div class="login-wrap" style="height: 920px">
                <div class="login-html" style="text-align: center">
                    <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Sign In</label>
                    <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Sign Up</label>
                    <div class="login-form">
                        <div class="sign-in-htm">
                            <form class="form" role="form" action="Login" method="post">
                                <div class="group">
                                    <label for="user" class="label">Username</label>
                                    <input id="user" type="text" class="input" required="required" name="signInName">
                                </div>
                                <div class="group">
                                    <label for="loginPasswd" class="label">Password</label>
                                    <input id="loginPasswd" type="password" class="input" data-type="password"  required="required" name="signInPasswd">
                                </div>
                                <div class="group">
                                    <input id="check" type="checkbox" class="check" checked>
                                    <label for="check"><span class="icon"></span> Keep me Signed in</label>
                                </div>
                                <div class="group">
                                    <input type="submit" class="button" value="Sign In">
                                </div>
                                <div class="hr"></div>
                                <div class="foot-lnk">
                                    <a href="#forgot">Forgot Password?</a>
                                </div>
                            </form>
                        </div>

                        <div class="sign-up-htm">
                            <form class="form-horizontal" role="form" action="SignUp" method="post" onsubmit="return signUpCheck()" >
                                <div class="group">
                                    <label for="user" class="label">Username</label>
                                    <input name="signUpName" id="signUpName" type="text" class="input" required="required" oninput="checkOnInput(this, '请输入用户名')">
                                </div>
                                <div class="group">
                                    <label for="signUpPasswd" class="label">Password</label>
                                    <input name="signUpPasswd" id="signUpPasswd" type="password" class="input" data-type="password" required="required" pattern="^(?!([a-zA-Z]+|\d+)$)[a-zA-Z\d]{6,20}$" oninput="checkOnInput(this, '密码格式不正确')">
                                </div>
                                <div class="group">
                                    <label for="confirmPasswd" class="label">Repeat Password</label>
                                    <input id="confirmPasswd" type="password" class="input" data-type="password" required="required" >
                                </div>
                                <div class="group">
                                    <label for="email" class="label">Email Address</label>
                                    <input id="email" type="text" class="input" name="signUpEmail" required="required" pattern="^([0-9A-Za-z\-_\.]+)@([0-9a-z]+\.[a-z]{2,3}(\.[a-z]{2})?)$" oninput="checkOnInput(this, '邮箱格式不正确')">
                                </div>
                                <div class="group">
                                    <label class="label" for="cellphoneNumber">cellphone Number</label>
                                    <input class="input" id="cellphoneNumber"
                                           type="tel" name="cellphoneNumber" required="required" pattern="[0-9]{11}" oninput="checkOnInput(this, '手机号码为11位')">
                                </div>

                                <div class="group">
                                    <label class="label" for="identification">ID Number</label>
                                    <input class="input" id="identification"
                                           type="text" pattern="[0-9]{17}[0-9|X]{1}" name="identification" required="required" oninput="checkOnInput(this, '身份证号码为18位')">
                                </div>
                                <div class="group">
                                    <input type="submit" class="button" value="Sign Up">
                                </div>
                                <div class="hr"></div>
                                <div class="foot-lnk">
                                    <label for="tab-1">Already Member?</label>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<script>

    function signUpCheck() {
        if(document.getElementById("privacyPolicy").checked === false){
            alert("请先阅读并同意相关服务条款和隐私政策");
            return false;
        } else if(document.getElementById("signUpPasswd").value!==document.getElementById("confirmPasswd").value) {
            alert("密码不一致");
            return false;
        } else {
            return true;
        }

    }

    function checkOnInput(input, tip) {
        if (input.validity.patternMismatch === true) {
            input.setCustomValidity(tip);
        } else {
            input.setCustomValidity('');
        }
    }

</script>
<script src="js/jquery-3.2.1.min.js"></script>
<script src="styles/bootstrap-4.1.2/popper.js"></script>
<script src="styles/bootstrap-4.1.2/bootstrap.min.js"></script>
<script src="plugins/greensock/TweenMax.min.js"></script>
<script src="plugins/greensock/TimelineMax.min.js"></script>
<script src="plugins/scrollmagic/ScrollMagic.min.js"></script>
<script src="plugins/greensock/animation.gsap.min.js"></script>
<script src="plugins/greensock/ScrollToPlugin.min.js"></script>
<script src="plugins/OwlCarousel2-2.2.1/owl.carousel.js"></script>
<script src="plugins/easing/easing.js"></script>
<script src="plugins/progressbar/progressbar.min.js"></script>
<script src="plugins/parallax-js-master/parallax.min.js"></script>
<script src="js/custom.js"></script>

</body>
</html>