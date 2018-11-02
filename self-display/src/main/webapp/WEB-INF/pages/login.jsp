<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>登录</title>
<link href="${ctx }/css/login/login.css" rel="stylesheet"
	type="text/css"></link>
</head>
<body>
	<!-- 背景音乐 -->
	<!-- <embed src="../music/login.mp3" loop="true" autostar="true"
		hidden="true" /> -->
	<audio src="../music/login.mp3" autoplay="true" hidden="true"
		loop="true"> </audio>

	<form class="layui-form" action="${ctx }/user/login" id="loginForm"
		method="post">
		<div class="login-warp">
			<div class="login">
				<h1>用户登录</h1>
				<div class="layui-form-item">
					<input class="layui-input" id="userCode" name="userCode"
						type="text" autocomplete="off">
				</div>
				<div class="layui-form-item">
					<input class="layui-input" type="passWord" id="passWord"
						name="passWord"></input>
					<!-- <input class="layui-input" type="text" id="password-text"></input>
					<input class="layui-input" id="passWord" name="passWord"
						type="password" autocomplete="off" style="display: none;"> -->
				</div>
				<div class="layui-form-item">
					<input style="display:inline;" id="rememberme" name="rememberme"
						type="checkbox"></input>
					<span>记住我</span> 
				</div>
				<button class="layui-btn login_btn" lay-submit lay-filter="login"
					id="loginSubmitBtn">登录</button>
				<div id="ajaxMsg">
					<span style="color: red;">${error}</span>
				</div>
			</div>
		</div>
	</form>
</body>
</html>