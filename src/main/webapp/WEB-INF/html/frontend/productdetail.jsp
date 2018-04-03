<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>商品详情</title>
<meta name="viewport" content="initial-scale=1, maximum-scale=1">
<link rel="shortcut icon" href="/favicon.ico">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet"
	href="//g.alicdn.com/msui/sm/0.6.2/css/sm.min.css">
<link rel="stylesheet"
	href="//g.alicdn.com/msui/sm/0.6.2/css/sm-extend.min.css">
<link rel="stylesheet"
	href="../resources/css/frontend/productdetail.css">
</head>
<body>
	<div class="page-group">
		<div class="page">
			<header class="bar bar-nav"> <a
				class="button button-link button-nav pull-left" external href="#"
				onClick="javascript :history.back(-1);" data-transition='slide-out'>
				<span class="icon icon-left"></span> 返回
			</a>
			<h1 class="title" id="product-name">商品详情</h1>
			</header>
			<nav class="bar bar-tab"> <a class="tab-item"
				href="/o2o/frontend/index" external> <span
				class="icon icon-home"></span> <span class="tab-label">首页</span>
			</a> <a class="tab-item" href="#" id="me"> <span class="icon icon-me"></span>
				<span class="tab-label">我</span>
			</a> </nav>
			<div class="content infinite-scroll infinite-scroll-bottom"
				data-distance="100">
				<!-- 这里是页面内容区 -->
				<div class="shop-detail-dev">
					<div class="card">
						<div valign="bottom"
							class="card-header color-white no-border no-padding">
							<img class="card-cover" id="product-img" src="" alt="" />
						</div>
						<div class="card-content">
							<div class="card-content-inner">
								<p class="color-gray">
									<span id="product-time">2015/01/15</span> <span
										class="pull-right" id="product-point"></span>
								</p>
								<p id="price" hidden="true">
									<span class="color-gray" id="normalPrice"> </span> <span><font
										color="red" size="4" id="promotionPrice"></font></span>
								</p>
								<p id="product-desc"></p>
							</div>
						</div>
						<div class="card-img" id="imgList">
							<!-- <a href="#" class="link">赞</a> -->
							<!-- <a href="#" class="link">更多</a> -->
						</div>
					</div>
				</div>
			</div>

			<div class="panel-overlay"></div>
			<div class="panel panel-right panel-reveal" id="panel-right-demo">
				<div class="content-block">
					<p>
						<a href="/o2o/local/accountbind?usertype=1" class="close-panel">绑定帐号</a>
					</p>
					<p>
						<a href="/o2o/local/changepsw?usertype=1" class="close-panel">修改密码</a>
					</p>
					<p>
						<a href="/o2o/frontend/myrecord" class="close-panel">消费记录</a>
					</p>
					<p>
						<a href="/o2o/frontend/mypoint" class="close-panel">我的积分</a>
					</p>
					<p>
						<a href="/o2o/frontend/pointrecord" class="close-panel">兑换记录</a>
					</p>
					<p>
						<a href="#" usertype="1" class="close-panel" id="log-out">登出系统</a>
					</p>
					<!-- Click on link with "close-panel" class will close panel -->
				</div>
			</div>
		</div>


		<script type='text/javascript'
			src='//g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>
		<script type='text/javascript'
			src='//g.alicdn.com/msui/sm/0.6.2/js/sm.min.js' charset='utf-8'></script>
		<script type='text/javascript'
			src='//g.alicdn.com/msui/sm/0.6.2/js/sm-extend.min.js'
			charset='utf-8'></script>
		<script type='text/javascript' src='../resources/js/common/common.js'
			charset='utf-8'></script>
		<script type='text/javascript'
			src='../resources/js/frontend/productdetail.js' charset='utf-8'></script>
		<script type='text/javascript' src='../resources/js/local/login.js'
			charset='utf-8'></script>
</body>
</html>