    <%@ page import="com.alibaba.fastjson.JSONArray" %>
    <%@ page import="java.util.Map" %>
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


    %>

    <html>
    <head>
        <meta http-equiv="content-type" content="text/html" charset="UTF-8">
        <title>Title</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
        <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <style>
            .searchTitle
            {
                font:42px arial,sans-serif;
                color: snow;
                text-align: center;
            }

            .searchSubtitle
            {
                font:20px arial,sans-serif;
                color: red;
                text-align: center;
            }

            .tableBg
            {
                background-color: powderblue;
            }

            .titleBg
            {
                background-color: darkorchid;
            }

            .resultBg
            {
                background-color: aliceblue;
            }

        </style>
    </head>
    <body>
    <div id="Layer1" style="position:fixed; left:0px; bottom:0px; width:100%; height:auto">

        <img src="images/wall.jpg" width="100%" height="100%"/>
    </div>
    <div id="Layer2" style="position: fixed; left: 0px; top: 0px; width: 100%;">
        <p class="searchTitle">科技专家资源共享平台</p>
        <div class="container" style="text-align: center">
            <form class="form-inline" role="form" action="Search" method="post">
                <div class="form-group" style="width: 61.8%;">

                    <select class="form-control" style="width: 20%" name="searchType">
                        <option value="1">全部</option>
                        <option value="2">论文</option>
                        <option value="3">专利</option>
                        <option value="4">项目</option>
                    </select>
                    <input type="hidden" name="userName" value="<%=userName%>">
                    <label class="sr-only" for="searchWord">关键词</label>
                    <input type="text" class="form-control" id="searchWord"
                           placeholder="请输入关键词" style="width: 79%" name="searchWord">
                </div>

                <button type="submit" class="btn btn-default">搜索</button>

            </form>
        </div>

        <div class="container" id="Layer3"style="width: 100%;height: 480px;overflow-y: auto" >
            <table class="table table-hover">

                <caption class="searchSubtitle tableBg">检索结果</caption>
                <thead class="titleBg">
                <tr>
                    <th style="text-align: center">题名</th>
                    <th style="text-align: center">作者</th>
                    <th style="text-align: center">种类</th>
                    <th style="text-align: center">下载链接</th>
                </tr>
                </thead>

                <tbody class="resultBg">

    <%
        for(int i=0;i<size;i++)
        {String type = ((Map<String,Object>)resourceList.get(i)).get("type").toString();
            String displayType = "PAPER";
            switch (type){
                case "PAPER":
                    displayType = "论文";
                    break;
                case "PROJECT":
                    displayType = "项目";
                    break;
                case "PATENT":
                    displayType = "专利";
                    break;
                default:
                    break;
            }

    %>
    <tr>
        <td align="center"><b><%=((Map<String,Object>)resourceList.get(i)).get("resourceName")%></b></td>
        <td align="center"><%=((Map<String,Object>)resourceList.get(i)).get("authorName")%></td>
        <td align="center"><%=displayType%></td>
        <td align="center"><%=((Map<String,Object>)resourceList.get(i)).get("resourceUrl")%></td>
    </tr>
    <%

        }
    %>

                </tbody>
            </table>
        </div>

        <div class="modal-footer" style="position: fixed; bottom: 30px; width: 100%; text-align: center">
            <form action="Back" method="post">
                <input type="hidden" name="userName" value="<%=userName%>">
                <button class="btn-default" type="submit">返回</button>
            </form>
        </div>
    </div>

    </body>

    </html>