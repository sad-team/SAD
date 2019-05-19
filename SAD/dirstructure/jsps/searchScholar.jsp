<%@ page import="com.alibaba.fastjson.JSONArray" %>
<%@ page contentType="text/html; charset=utf-8" %>
<%
    String userName = (request.getParameter("userName") == null) ? "" : request.getParameter("userName");
    String resourceJson = (request.getParameter("resource") == null)
            ? "" : request.getParameter("resource");

    int size = 0;
    JSONArray resourceList = new JSONArray();
    if (!resourceJson.equals("")) {
        resourceList = JSONArray.parseArray(resourceJson);
        size = resourceList.size();
    }
    String role = (request.getParameter("role") == null) ? "0" : request.getParameter("role");

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>BHost</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="description" content="BHost template project">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="styles/bootstrap-4.1.2/bootstrap.min.css">
    <link href="plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.carousel.css">
    <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
    <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/animate.css">
    <link rel="stylesheet" type="text/css" href="styles/main_styles.css">
    <link rel="stylesheet" type="text/css" href="styles/responsive.css">
</head>
<body>

<div class="super_container">

    <!-- Header -->

    <header class="header trans_400">
        <div class="header_content d-flex flex-row align-items-center justify-content-start trans_400">
            <div class="logo"><a href="#"><span>S</span>AD</a></div>
            <div class="container">
                <div class="row">
                    <div class="col-lg-10 offset-lg-2">
                        <nav class="main_nav">
                            <ul class="d-flex flex-row align-items-center justify-content-start">
                                <li><a href="#" onclick="turnTo('Back','<%=userName%>')">首页</a></li>
                                <li><a href="#" onclick="turnTo('SearchScholar','<%=userName%>')">专家</a></li>
                                <li><a href="news.jsp">资讯</a></li>
                                <li><a href="community.jsp">社区</a></li>
                                <li><a href="dynamic.jsp">动态</a></li>
                                <li><a href="myMessages.jsp">消息</a></li>
                                <li><a href="#" onclick="turnTo('Recharge','<%=userName%>')">充值</a></li>
                                <li class="dropdown">
                                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">我<b class="caret"></b>
                                    </a>
                                    <ul class="dropdown-menu" style="background-color: rgba(18,5,52,0.69)">
                                        <li><a href="#" onclick="turnTo('QueryUserInfo','<%=userName%>')">个人信息</a></li>

                                        <li><a href="#" onclick="turnTo('OrderHistory','<%=userName%>')">历史订单</a></li>

                                        <li><a href="#" onclick="turnTo('MyPoint','<%=userName%>')">我的积分</a></li>

                                        <li ><a href="myFollows.jsp">我的关注</a></li>

                                        <li><a href="#" onclick="turnTo('MyResource','<%=userName%>')">我的资源</a></li>
                                    </ul>
                                </li>

                                <%
                                    if (role.equals("1")){
                                        out.println("<li>\n" +
                                                "<button class=\"btn-primary\" onclick=\"turnTo('MyHome','"+userName+ "')\">我的门户</button>\n" +
                                                "</li>");
                                    }
                                %>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
            <div class="header_right d-flex flex-row align-items-center justify-content-start">

                <div class="phone d-flex flex-row align-items-center justify-content-start">
                    <i class="fa " aria-hidden="true"></i>
                    <div>
                        <ul>
                            <li class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#" style="color: yellow"><%=userName%> <b class="caret"></b></a>

                                <ul class="dropdown-menu">
                                    <li> <a href="index.html" class="btn btn-default">注销</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>

                <!-- Hamburger -->
                <div class="hamburger"><i class="fa fa-bars" aria-hidden="true"></i></div>
            </div>
        </div>
    </header>



    <!-- Home -->

    <div class="home">
        <div class="parallax_background parallax-window" data-parallax="scroll" data-image-src="images/index.jpg" data-speed="0.8"></div>
        <div class="home_container">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <div class="home_content text-center">
                            <div class="home_icon ml-auto mr-auto d-flex flex-column align-items-center justify-content-center"><div><img src="images/icon_1.svg" alt="https://www.flaticon.com/authors/srip"></div></div>
                            <div class="home_title">专家查询</div>
                            <div class="domain_search">
                                <div class="domain_search_background"></div>
                                <form class="domain_search_form" id="domain_search_form" method="post" action="SearchScholar">
                                    <label for="searchWord"></label><input type="text" class="domain_search_input" placeholder="请输入搜索内容" required="required" name="searchWord" id="searchWord">
                                    <input type="hidden" name="userName" value="<%=userName%>">
                                    <button class="domain_search_button" type="submit">搜索</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<script>

function turnTo(target,userName) {
var form = document.createElement("form");
form.action = target.toString();
form.method = "post";
var input = document.createElement("input");
input.type = "hidden";
input.name = "userName";
input.value = userName;
form.appendChild(input)

document.body.appendChild(form);
form.submit();
document.body.removeChild(form);
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