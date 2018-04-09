<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>登录</title>
<meta name="viewport" content="initial-scale=1, maximum-scale=1">
<link rel="shortcut icon" href="/favicon.ico">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet"
	href="//g.alicdn.com/msui/sm/0.6.2/css/sm.min.css">
<link rel="stylesheet"
	href="//g.alicdn.com/msui/sm/0.6.2/css/sm-extend.min.css">
</head>
<body>

	<header class="bar bar-nav">
	<h1 class="title">登录</h1>
	</header>
	<div class="content">
		<div class="list-block">
			<ul>
				<li>
					<div class="item-content">
						<div class="item-media">
							<i class="icon icon-form-name"></i>
						</div>
						<div class="item-inner">
							<div class="item-input">
								<input type="text" id="username" placeholder="用户名">
							</div>
						</div>
					</div>
				</li>
				<li>
					<div class="item-content">
						<div class="item-media">
							<i class="icon icon-form-email"></i>
						</div>
						<div class="item-inner">
							<div class="item-input">
								<input type="password" id="psw" placeholder="密码">
							</div>
						</div>
					</div>
				</li>
				<li id="verifyPart" hidden="true">
					<div class="item-content">
						<div class="item-media">
							<i class="icon icon-form-email"></i>
						</div>
						<div class="item-inner">
							<label for="j_captcha" class="item-title label">验证码</label> <input
								id="j_captcha" name="j_captcha" type="text"
								class="form-control in" placeholder="验证码" />
							<div class="item-input">
								<img id="captcha_img" alt="点击更换" title="点击更换"
									onclick="changeVerifyCode(this)" src="../Kaptcha" />
							</div>
						</div>
					</div>
				</li>
			</ul>
		</div>
		<div class="content-block">
			<div class="row">
				<div class="col-100">
					<a href="#" class="button button-big button-fill" id="submit">登录</a>
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
	<script type='text/javascript' src='../resources/js/common/common.js'
		charset='utf-8'></script>
	<script type='text/javascript' src='../resources/js/local/login.js'
		charset='utf-8'></script>
</body>
</html>