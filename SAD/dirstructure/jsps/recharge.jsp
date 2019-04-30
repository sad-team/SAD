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
        .rechargeTitle
        {
            font:36px arial,sans-serif;
            color: snow;
            text-align: center;
        }

        .rechargeSubtitle
        {
            font:24px arial,sans-serif;
            color: snow;
            text-align: center;
        }

        .rechargeOption
        {
            font:12px arial,sans-serif;
            color: snow;
            text-align: center;
        }

    </style>
</head>
<body>
<div id="Layer1" style="position:fixed; left:0px; bottom:0px; width:100%; height:100%">
    <img src="images/rechargeBg.jpg" width="100%" height="100%"/>
</div>
<div id="Layer2">
<div class="container" style="position:fixed; left:0px; bottom:0px; width:100%; height:100%">
    <div class="row rechargeTitle" style="height: 10%; margin-top: 5%">
        购买积分
    </div>

    <div class="row rechargeSubtitle" style="height: 10%">
        请选择购买数量
    </div>

    <div class="row" style="height: 10%; text-align: center">
        <div class="col-xs-2 col-xs-offset-3">
            <label class="radio-inline rechargeOption">
                <input type="radio" name="number" id="inlineRadio1" value="option1"> 10积分
            </label>

        </div>
        <div class="col-xs-2">
            <label class="radio-inline rechargeOption">
                <input type="radio" name="number" id="inlineRadio2" value="option2"> 20积分
            </label>

        </div>
        <div class="col-xs-2">
            <label class="radio-inline rechargeOption">
                <input type="radio" name="number" id="inlineRadio3" value="option3"> 50积分
            </label>
        </div>
    </div>

    <div class="row" style="height: 10%; text-align: center">
        <div class="col-xs-2 col-xs-offset-3">
            <label class="radio-inline rechargeOption">
                <input type="radio" name="number" id="inlineRadio4" value="option4"> 100积分
            </label>

        </div>
        <div class="col-xs-2">
            <label class="radio-inline rechargeOption">
                <input type="radio" name="number" id="inlineRadio5" value="option5"> 200积分
            </label>

        </div>
        <div class="col-xs-2">
            <label class="radio-inline rechargeOption">
                <input type="radio" name="number" id="inlineRadio6" value="option6">500积分
            </label>
        </div>
    </div>

    <div class="row rechargeSubtitle" style="height: 10%; text-align: center">
        请选择支付方式
    </div>
    <div class="row" style="height: 10%; text-align: center">
        <div class="col-xs-4 col-xs-offset-2">
            <label class="radio-inline rechargeOption">
                <input type="radio" name="paymentMode" id="inlineRadio7" value="option7"> 微信
            </label>

        </div>
        <div class="col-xs-4">
            <label class="radio-inline rechargeOption">
                <input type="radio" name="paymentMode" id="inlineRadio8" value="option8"> 支付宝
            </label>

        </div>
    </div>

    <div class="row rechargeSubtitle">
        <button class="btn-primary" type="submit">
            确定
        </button>
    </div>

    <div class="modal-footer" style="margin-top: 30px; text-align: center">
        <a href="index.html" class="btn btn-default">返回</a>
    </div>
</div>
</div>
</div>
<!-- JavaScript 放置在文档最后面可以使页面加载速度更快 -->
<!-- 可选: 包含 jQuery 库 -->
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<!-- 可选: 合并了 Bootstrap JavaScript 插件 -->
<script src="https://apps.bdimg.com/libs/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</body>
</html>