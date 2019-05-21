<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="com.alibaba.fastjson.JSONArray" %>
<%@ page import="com.alibaba.fastjson.JSONObject" %>

<%@ page contentType="text/html; charset=utf-8" %>
<%
    String      resourceJson = (request.getParameter("resource") == null)
            ? "" : request.getParameter("resource");
    JSONArray resourceList = JSONArray.parseArray(resourceJson);
    int size = resourceList.size();
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
        p.searchTitle
        {
            font:42px arial,sans-serif;
            color: darkorchid;
            text-align: center;
        }

        .border
        {
            border: 1px solid #000;
        }
        .table-hover>tbody>tr:hover {

            background-color: powderblue;

        }
    </style>
</head>
<body>

<div id="Layer1" style="position:fixed; left:0px; bottom:0px; width:100%; height:100%">
    <img src="images/resource.jpg" width="100%" height="100%"/>
</div>

<div id="Layer2" style="position: fixed; left: 0px; top: 0px; width: 100%; height: 540px">
    <p class="searchTitle">已购资源</p>
    <div class="container" id="Layer3" style="width: 100%; height: 480px;overflow-y: auto">
        <table class="table table-hover" id="resourceTable" >
            <caption class="searchSubtitle tableBg"></caption>
            <thead class="titleBg">
            <tr>
                <th>资源名称</th>
                <th>资源种类</th>
            </tr>
            </thead>
            <tbody class="resultBg">
         <% for (int i = 0; i < size; i++)
            {%>
            <tr>
                <td align="center"><b><%=((Map<String,Object>)resourceList.get(i)).get("resourceName")%></b></td>
                <td align="center"><%=((Map<String,Object>)resourceList.get(i)).get("resourceUrl")%></td>
            </tr>
            <%}
         %>
            </tbody>
        </table>

    </div>

    <div style="margin-top: 30px; text-align: center">
        <a href="index.jsp">返回</a>
    </div>
</div>

<script>

</script>


<!-- JavaScript 放置在文档最后面可以使页面加载速度更快 -->
<!-- 可选: 包含 jQuery 库 -->
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<!-- 可选: 合并了 Bootstrap JavaScript 插件 -->
<script src="https://apps.bdimg.com/libs/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</body>
</html>

