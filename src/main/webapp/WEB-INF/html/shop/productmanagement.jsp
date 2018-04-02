<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>商品管理</title>
<meta name="viewport" content="initial-scale=1, maximum-scale=1">
<link rel="shortcut icon" href="/favicon.ico">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet"
	href="//g.alicdn.com/msui/sm/0.6.2/css/sm.min.css">
<link rel="stylesheet"
	href="//g.alicdn.com/msui/sm/0.6.2/css/sm-extend.min.css">
<link rel="stylesheet"
	href="../resources/css/shop/productmanagement.css">
</head>
<body>

	<header class="bar bar-nav">
	<h1 class="title">商品管理</h1>
	</header>
	<div class="content">
		<div class="content-block">
			<div class="row row-product">
				<div class="col-33">商品名称</div>
				<div class="col-20">优先级</div>
				<div class="col-40">操作</div>
			</div>
			<div class="product-wrap">
			</div>
		</div>
		<div class="content-block">
			<div class="row">
				<div class="col-50">
					<a href="/o2o/shopadmin/shopmanagement"
						class="button button-big button-fill button-danger">返回</a>
				</div>
				<div class="col-50">
					<a href="/o2o/shopadmin/productoperation"
						class="button button-big button-fill button-success" id="new">新增</a>
				</div>
			</div>
		</div>
	</div>

	<script type='text/javascript'
		src='//g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>
	<script type='text/javascript'
		src='//g.alicdn.com/msui/sm/0.6.2/js/sm.min.js' charset='utf-8'></script>
	<script type='text/javascript'
		src='//g.alicdn.com/msui/sm/0.6.2/js/sm-extend.min.js' charset='utf-8'></script>
	<script type='text/javascript'
		src='../resources/js/shop/productmanagement.js' charset='utf-8'></script>
</body>

</html>