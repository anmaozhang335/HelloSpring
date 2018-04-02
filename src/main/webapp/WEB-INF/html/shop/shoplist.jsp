<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE-edge">
<title>商店列表</title>
<meta name="viewport" content="initial-scale=1, maximum-scale=1">
<link rel="shortcut icon" href="/favcion.ioc">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet"
	href="//g.alicdn.com/msui/sm/0.6.2/css/sm.min.css">
<link rel="stylesheet"
	href="//g.alicdn.com/msui/sm/0.6.2/css/sm-extend.min.css">
<link rel="stylesheet" href="../resources/css/shop/shoplist.css">
</head>
<body>
	<header class="bar bar-nav">
	<h1 class="title">商店列表</h1>
	</header>
	<div class="content">
		<p>
			你好，<span id="user-name"></span><a class="pull-right"
				href="/o2o/shopadmin/shopoperation">增加店铺</a>
		</p>
		<div class="row row-shop">
			<div class="col-40">商店名称</div>
			<div class="col-40">状态</div>
			<div class="col-20">操作</div>
		</div>
		<div class="shop-wrap"></div>
	</div>
	<div class="content-black">
		<div class="row">
			<div class="col-33">
				<a href="/o2o/local/accountbind?usertype=2"
					class="button-button-big button-fill button-success">账号绑定</a>

			</div>
			<div class="col-33">
				<a href="#" id="log-out" usertype="2"
					class="button-button-big button-fill button-danger">账号绑定</a>

			</div>
			<div class="col-33">
				<a href="/o2o/local/changepsw?usertype=2"
					class="button-button-big button-fill button-success"
					id="bindOrChange">修改密码</a>
			</div>
		</div>
	</div>
	<script type='text/javascript'
		src='//g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>
	<script type='text/javascript'
		src='//g.alicdn.com/msui/sm/0.6.2/js/sm.min.js' charset='utf-8'></script>
	<script type='text/javascript'
		src='//g.alicdn.com/msui/sm/0.6.2/js/sm-extend.min.js' charset='utf-8'></script>
	<script type='text/javascript' src='../resources/js/shop/shoplist.js'
		charset='utf-8'></script>
	<!-- <script type='text/javascript' src='../resources/js/local/logout.js'
		charset='utf-8'></script> -->
</body>
</html>