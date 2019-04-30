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
<div id="Layer1" style="position:fixed; left:0px; bottom:0px; width:100%; height:100%">

    <img src="images/wall.jpg" width="100%" height="100%"/>
</div>
<div id="Layer2" style="position: fixed; left: 0px; top: 0px; width: 100%; height: 20%">
    <p class="searchTitle">科技专家资源共享平台</p>
    <div class="container" style="text-align: center">
        <form class="form-inline" role="form">
            <div class="form-group" style="width: 61.8%;">
                <select class="form-control" style="width: 20%">
                    <option>全部</option>
                    <option>论文</option>
                    <option>专利</option>
                    <option>项目</option>
                    <option>作者</option>
                </select>

                <label class="sr-only" for="name">关键词</label>
                <input type="text" class="form-control" id="name"
                       placeholder="请输入关键词" style="width: 79%">
            </div>

            <button type="submit" class="btn btn-default">搜索</button>

        </form>
    </div>

    <div class="container" id="Layer3" style="width: 100%; height: 50%">
        <table class="table table-hover">
            <caption class="searchSubtitle tableBg">检索结果</caption>
            <thead class="titleBg">
            <tr>
                <th>题名</th>
                <th>作者</th>
                <th>发表时间</th>
            </tr>
            </thead>
            <tbody class="resultBg">
            <tr>
                <td>Tanmay</td>
                <td>Bangalore</td>
                <td>2019-3-9</td>
            </tr>
            <tr>
                <td>Sachin</td>
                <td>Mumbai</td>
                <td>2019-3-10</td>
            </tr>
            <tr>
                <td>Uma</td>
                <td>Pune</td>
                <td>2019-3-11</td>
            </tr>
            </tbody>
        </table>
    </div>

    <div class="modal-footer" style="position: fixed; bottom: 30px; width: 100%; text-align: center">
        <a href="index.html" class="btn btn-default">返回</a>
    </div>
</div>

</body>

</html>