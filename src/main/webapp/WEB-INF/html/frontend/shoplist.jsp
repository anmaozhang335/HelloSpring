<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>商店列表</title>
<meta name="viewport" content="initial-scale=1, maximum-scale=1">
<link rel="shortcut icon" href="/favicon.ico">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet"
	href="//g.alicdn.com/msui/sm/0.6.2/css/sm.min.css">
<link rel="stylesheet"
	href="//g.alicdn.com/msui/sm/0.6.2/css/sm-extend.min.css">
<link rel="stylesheet" href="../resources/css/frontend/shoplist.css">
</head>
<body>
	<div class="page-group">
		<div class="page">
			<header class="bar bar-nav"> <a
				class="button button-link button-nav pull-left" external
				href="index" data-transition='slide-out'> <span
				class="icon icon-left"></span> 返回
			</a>
			<h1 class="title">商店列表</h1>
			</header>
			<div class="bar bar-header-secondary">
				<div class="searchbar">
					<a class="searchbar-cancel">取消</a>
					<!-- 搜索栏 -->
					<div class="search-input">
						<label class="icon icon-search" for="search"></label> <input
							type="search" id='search' placeholder='输入关键字...' />
					</div>
				</div>
			</div>
			<nav class="bar bar-tab"> <a class="tab-item"
				href="/o2o/frontend/index" external> <span
				class="icon icon-home"></span> <span class="tab-label">首页</span>
			</a> <a class="tab-item" href="#" id="me"> <span class="icon icon-me"></span>
				<span class="tab-label">我</span>
			</a> </nav>
			<div class="content infinite-scroll infinite-scroll-bottom"
				data-distance="100">
				<!-- 类别列表展示区 -->
				<div class="shoplist-button-div" id="shoplist-search-div">
					<!-- <a href="#" class="button">所有货物</a>
                        <a href="#" class="button">吃的</a>
                        <a href="#" class="button">喝的</a>
                        <a href="#" class="button">Usual Button 1</a>
                        <a href="#" class="button">Usual Button 1</a>
                        <a href="#" class="button">Usual Button 1</a> -->
				</div>
				<div class="select-wrap">
					<!-- 区域列表展示区 -->
					<select class="select" id="area-search"></select>
				</div>
				<!-- 店铺列表在此添加 -->
				<div class="list-div shop-list">
					<!-- <div class="card">
                            <div class="card-header">传统火锅店</div>
                            <div class="card-content">
                                <div class="list-block media-list">
                                    <ul>
                                        <li class="item-content">
                                            <div class="item-media">
                                                <img src="http://gqianniu.alicdn.com/bao/uploaded/i4//tfscom/i3/TB10LfcHFXXXXXKXpXXXXXXXXXX_!!0-item_pic.jpg_250x250q60.jpg" width="44">
                                            </div>
                                            <div class="item-inner">
                                                <div class="item-subtitle"></div>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="card-footer">
                                <span>2015/01/15</span>
                                <span>5 评论</span>
                            </div>
                        </div> -->
				</div>
				<div class="infinite-scroll-preloader">
					<div class="preloader"></div>
				</div>
			</div>
		</div>
	</div>
	<!--侧边栏  -->
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

	<script type='text/javascript'
		src='//g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>
	<script type='text/javascript'
		src='//g.alicdn.com/msui/sm/0.6.2/js/sm.min.js' charset='utf-8'></script>
	<script type='text/javascript'
		src='//g.alicdn.com/msui/sm/0.6.2/js/sm-extend.min.js' charset='utf-8'></script>
	<script type='text/javascript' src='../resources/js/common/common.js'
		charset='utf-8'></script>
	<script type='text/javascript'
		src='../resources/js/frontend/shoplist.js' charset='utf-8'></script>
	<script type='text/javascript' src='../resources/js/local/login.js'
		charset='utf-8'></script>

</body>
</html>