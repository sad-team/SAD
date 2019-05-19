<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<html>
<head>
    <title>Ednews</title>
    <meta content="width=device-width, initial-scale=1" name="viewport">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="description" content="BHost template project">
    <link rel="stylesheet" type="text/css" href="styles/bootstrap-4.1.2/bootstrap.min.css">
    <link href="plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.carousel.css">
    <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
    <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/animate.css">
    <link rel="stylesheet" type="text/css" href="styles/main_styles.css">
    <link rel="stylesheet" type="text/css" href="styles/responsive.css">

</head>
<body>






<!-- Header -->
<div class="super_container">
    <header class="header trans_400" style="width: 100%">
        <div class="header_content d-flex flex-row align-items-center justify-content-start trans_400">
            <div class="logo"><a href="#"><span>S</span>AD</a></div>
            <div class="container">
                <div class="row">
                    <div class="col-lg-10 offset-lg-2">
                        <nav class="main_nav">
                            <ul class="d-flex flex-row align-items-center justify-content-start">
                                <li><a href="index.jsp">首页</a></li>
                                <li><a href="searchScholar.jsp" >专家</a></li>
                                <li><a href="news.jsp">资讯</a></li>
                                <li><a href="community.jsp">社区</a></li>
                                <li><a href="dynamic.jsp">动态</a></li>
                                <li><a href="#">消息</a></li>
                                <li><a href="recharge.jsp">充值</a></li>
                                <li class="dropdown">
                                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">我<b class="caret"></b></a>
                                    <ul class="dropdown-menu" style="background-color: rgba(18,5,52,0.69)">
                                        <li><a href="userInfo.jsp">个人信息</a></li>
                                        <li><a href="orderHistory.jsp">历史订单</a></li>
                                        <li><a href="myPoints.jsp">我的积分</a></li>
                                        <li><a href="myFollows.jsp">我的关注</a></li>
                                        <li><a href="myResource.jsp">我的资源</a></li>
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
                <!--<div class="hamburger"><i class="fa fa-bars" aria-hidden="true"></i></div>-->
            </div>
        </div>
    </header>

<!-- END OF MAIN SLIDER -->
</div>



<!-- SCIPTS -->


<script src="plugin-frameworks/tether.min.js"></script>

<script src="common/scripts.js"></script>
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