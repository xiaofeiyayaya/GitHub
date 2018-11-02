<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
<style type="text/css">
.head {
	width: 100%;
	height: 100px;
	background-color: #bdccde !important;
	text-align: center;
	margin-bottom: 5px;
}

.head-title {
	color: #40562f;
	font-family: "Arial", "Microsoft Yahei", "SimSun";
	font-size: 30px;
	line-height: 100px;
}

.layui-breadcrumb.navigation {
	float: right;
	padding-top: 76px;
}
.left{
	width : 300px;
	height : 100%;
	overflow : auto;
	background: #FAFAFA;
	border: 1px solid #ccc;
	float : left;
}
.board{
	font-size: 20px;
	font-style:italic;
	color: #291cd4bd;
	text-align: center;
}
.main{
    margin-left: 310px;
    background-color: #f3f3f3;
    padding-top: 20px;
    padding-left: 10px;
    height: 100%;
}
.lunbo{
	margin-top:10px;
}
#callboard { height:24px; line-height:24px; overflow:hidden;} 
#callboard ul { padding:0;} 
#callboard li { padding:0;} 
</style>
</head>
<body>
	<div class="head">
		<span class="head-title">小飞</span> 
		<span class="layui-breadcrumb navigation" lay-separator="|">
		<a href="">娱乐</a> 
		<a href="">八卦</a> 
		<a href="">体育</a> 
		<a href="">搞笑</a>
		<a href="">视频</a> 
		<a href="">游戏</a> 
		<a href="">综艺</a>
		</span>
	</div>
	
	<div class="left">
		<p class="board">留言板</p>
		<table>
			<thead>
				<tr>
					<th align="left">最近的吐槽：</th>
				</tr>
			</thead>
			<tbody id="xx" class="xxx">
				<tr>
					<td align="left">xiaofei：真的是垃圾啊</td> 
				</tr>
				<tr>
					<td align="left">xiaofei：真的是垃圾啊</td>
				</tr>
				<tr>
					<td align="left">xiaofei：真的是垃圾啊</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="mainbackground">
		<div class="main">
			<div id="callboard"> 
				<ul> 
				<li> 
				<a href="http://www.qianduanzu.com/2012042036.html">公告：垃圾啊，垃圾啊！</a> 
				</li> 
				<li> 
				<span style="color:red;">公告：页面根本搞不定啊！</span> 
				</li> 
				</ul> 
			</div>
			<div class="lunbo" id="lunbo">
				<div class="layui-carousel" id="test10">
				  <div carousel-item="">
				    <div><img src="../images/lunbo6.jpg"></div>
				    <div><img src="../images/lunbo6.jpg"></div>
				    <div><img src="../images/lunbo6.jpg"></div>
				    <div><img src="../images/lunbo6.jpg"></div>
				    <div><img src="../images/lunbo6.jpg"></div>
				    <div><img src="../images/lunbo6.jpg"></div>
				  </div>
				</div>
			</div>
			<div class="">
				<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
				  <legend>个人逗比时刻</legend>.
				  <span style="color:red;">上传个人糗事，让大家一起开心一下！</span> 
				</fieldset>
				<button id="button" >点一下</button>
			</div>
		</div>
	</div>
	

</body>
<script>
	layui.use(['carousel','element'], function() {
		var element = layui.element, //导航的hover效果、二级菜单等功能，需要依赖element模块
		carousel = layui.carousel;
		//监听导航点击
		element.on('nav(demo)', function(elem) {
			//console.log(elem)
			layer.msg(elem.text());
		});
	 	//图片轮播
	  carousel.render({
	    elem: '#test10'
	    ,width: '716px'
	    ,height: '301px'
	    ,interval: 3000
	  });
	 	
	/*  $(".xxx").find("tr").each(function(){
		 var tdarr = $(this).children();
		 alert(tdarr.text())
	 }) */
	 
	});
	
	$("#button").click(function(){
		$("#button").hide();
		
	});
	
	 
	(function (win){ 
		var callboarTimer; 
		var callboard = $('#callboard'); 
		var callboardUl = callboard.find('ul'); 
		var callboardLi = callboard.find('li'); 
		var liLen = callboard.find('li').length; 
		var initHeight = callboardLi.first().outerHeight(true); 
		win.autoAnimation = function (){ 
		if (liLen <= 1) return; 
		var self = arguments.callee; 
		var callboardLiFirst = callboard.find('li').first(); 
		callboardLiFirst.animate({ 
		marginTop:-initHeight 
		}, 500, function (){ 
		clearTimeout(callboarTimer); 
		callboardLiFirst.appendTo(callboardUl).css({marginTop:0}); 
		callboarTimer = setTimeout(self, 5000); 
		}); 
		} 
		callboard.mouseenter( 
		function (){ 
		clearTimeout(callboarTimer); 
		}).mouseleave(function (){ 
		callboarTimer = setTimeout(win.autoAnimation, 5000); 
		}); 
		}(window)); 
		setTimeout(window.autoAnimation, 5000); 
</script>
</html>