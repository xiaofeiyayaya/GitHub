<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
<style type="text/css">
/*li {float:right;}*/
ul {float:right}
</style>
</head>
<body>

	<div class="layui-bg-cyan">
		<ul class="layui-nav ">
			<li class="layui-nav-item"><a href="">藏青导航</a></li>
			<li class="layui-nav-item layui-this"><a href="">产品</a></li>
			<li class="layui-nav-item"><a href="">大数据</a></li>
			<li class="layui-nav-item"><a href="javascript:;">解决方案</a>
				<dl class="layui-nav-child">
					<dd>
						<a href="">移动模块</a>
					</dd>
					<dd>
						<a href="">后台模版</a>
					</dd>
					<dd>
						<a href="">电商平台</a>
					</dd>
				</dl></li>
			<li class="layui-nav-item" ><a href="">社区</a></li>
		</ul>
	</div>
	<a href="${ctx}/user/toAddUser"> activeMq测试</a>
</body>
</html>