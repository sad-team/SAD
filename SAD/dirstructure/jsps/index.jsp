<%@ page contentType="text/html; charset=utf-8" %>
<%
    String name = (request.getParameter("name") == null)
            ? "" : request.getParameter("name");
%>



<html>
<head>
    <meta charset="utf-8">
    <title>Title</title>
    <meta content="width=device-width, initial-scale=1" name="viewport">
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.4.0/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
   <div>
     <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container-fluid">
            <div>
                <ul class="nav navbar-nav">
                    <li><a href="news.jsp">资讯</a></li>
                    <li><a href="community.jsp">社区</a></li>
                    <li><a href="dynamic.jsp">动态</a></li>
                    <li>
                        <div class="navbar-nav" style="alignment: center;text-align: center">
                        <form action="Recharge" method="post" style="position: relative;top: 10px;text-align: center;alignment: center">
                            <input type="hidden" name="userName" value="<%=name%>">
                            <button class="btn-default" type="submit">充值</button>
                        </form>
                        </div>
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            我<b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                          <!--  <li><a href="userInfo.jsp">个人信息</a></li> -->
                            <li>
                                <form action="QueryUserInfo" method="post">
                                    <input type="hidden" name="userName" value="<%=name%>">
                                    <button class="btn-primary" type="submit">个人信息</button>
                                </form>
                            </li>
                            <li>
                                <form action="OrderHistory" method="post">
                                    <input type="hidden" name="userName" value="<%=name%>">
                                    <button class="btn-primary" type="submit">历史订单</button>
                                </form>
                            </li>
                            <li>
                                <form action="MyPoint" method="post">
                                    <input type="hidden" name="userName" value="<%=name%>">
                                    <button class="btn-primary" type="submit">我的积分</button>
                                </form>
                            </li>
                            <li><a href="myFollows.jsp">我的关注</a></li>
                            <li>
                                    <form action="MyResource" method="post">
                                        <input type="hidden" name="userName" value="<%=name%>">
                                        <button class="btn-primary" type="submit">我的资源</button>
                                    </form>
                            </li>
                        </ul>
                    </li>



                </ul>




                <ul class="nav navbar-nav navbar-right">
                    <li><a href="myMessages.jsp">消息<b class="caret"></b></a></li>
                </ul>
                <ul class="nav navbar-form navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <%=name%> <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li> <a href="index.html" class="btn btn-default">注销</a></li>
                    </ul>
                </li>
                </ul>


            </div>
        </div>
    </nav>


   </div>

<div class="container">
    <div class="jumbotron">
        <h1>科技专家资源共享平台</h1>
        <h2>欢迎您</h2>
    </div>

    <div class="row">
        <form action="TurnToSearch" method="post">

        <div class="col-sm-4">
            <h3>论文</h3>
            <input type="hidden" name="userName" value="<%=name%>">
            <p>  <button class="btn-primary" type="submit">搜索</button> </p>
        </div>
        <div class="col-sm-4">
            <h3>专利</h3>
            <input type="hidden" name="userName" value="<%=name%>">
            <p>  <button class="btn-primary" type="submit">搜索</button> </p>
        </div>
        <div class="col-sm-4">
            <h3>项目</h3>
            <input type="hidden" name="userName" value="<%=name%>">
            <p>  <button class="btn-primary" type="submit">搜索</button> </p>
        </div>
        </form>
    </div>

    <h2>资讯</h2>
    <p>了解最新的学术动态</p>
    <img alt="Cinque Terre" class="img-responsive img-thumbnail" height="236" src="images/bookstore.jpg" width="304">
</div>
</body>
</html>
