<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>jQuery 二级联动</title>
<style type="text/css">
.box {
	width: 100px;
	height: 100px;
	border: 2px solid red;
	padding: 10px;
	text-align: center;
}

.child {
	width: 70px;
	height: 20px;
	border: 2px solid red;
	text-align: center;
}
</style>

<script type="text/javascript">
	function showCitys() {
		var allCity = [ [], [ "广州", "深圳" ], [ "南宁", "桂林", "我不住地" ] ];
		var allProvice = document.getElementById("allProvice");
		//获取选择的省份
		var i = parseInt(allProvice.selectedIndex);
		//取出这个省份中的城市
		var data = allCity[i];

		//填入到
		var citys = document.getElementById("citys");
		citys.options.length = 1;
		//var optionNode = document.createElement("option");
		for (var i = 0; i < data.length; i++) {
			var optionNode = document.createElement("option");
			optionNode.innerHTML = data[i];
			citys.appendChild(optionNode);
		}
	};

	$(document).ready(function() {
		$(".a").blur(function() {
			$("span.text").remove();
			if ($(this).val().length <= 0) {
				var span = "<span style='color: red;' class='text'>内容不能为空</span>"
				$(this).after(span);
			}
		});
			
		
		$("#html").click(function(){
			//$('#middle').html('<div class="child">html()');     
			$('#middle').append('<div class="child">append()</div>');
			//$('#middle').after('<div class="child">after()</div>');
			//$('#middle').empty();
			//$('#middle').remove();
			//$(".box").attr("name","a");
			//$("div[name='a']").html('<div class="child">html()'); 
		});
		
		$("#send").click(function(){
			var json = $("#aaa").serialize();
			$.ajax({
				url : "../user/demo",
				type : "POST",
				data : json,
				dataType : "json",
				success : function(data) {
					console.log(data);
					if ("true" == data.result) {
						alert(data.name);
					} else if ("false" == data.result) {}
				}
			})
			
		})
	})
</script>

<link rel="stylesheet" href="${ctx }/formSelects-v4.css" />
<script src="${ctx }/layui.all.js" type="text/javascript" charset="utf-8"></script>
<script src="${ctx }/formSelects-v4.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    var formSelects = layui.formSelects;
     
</script>
</head>
<body>
	<!-- 省份
	<select name="" id="allProvice" onchange="showCitys()">
		<option value="">省份</option>
		<option value="">广东</option>
		<option value="">广西</option>
	</select> 城市
	<select name="" id="citys">
		<option>城市</option>
	</select>
	<br>

	<div>
		<form action="" id="aaa">
			<div class="int">
				<label for="name"><span style='color: red;'>*</span>姓名：</label> <input
					type="text" id="name" class="a" name = "name"/>
			</div>
			<div class="int">
				<label for="email"><span style='color: red;'>*</span>邮箱：</label> <input
					type="text" id="email" class="a"  name = "email"/>
			</div>
			<div class="int">
				<label for="address"><span style='color: red;'>*</span>住址：</label> <input
					type="text" id="address" class="a"  name = "address"/>
			</div>
			<div class="int">
				<input type="button" value="提交" id="send" style="margin-left: 70px;" />
				<input type="reset" value="重置" id="res" />
			</div>
		</form>
	</div>
	
	
	<div id="top" class="box">
		<span class="text">top</span>
	</div>
	<div id="middle" class="box">
		<span>middle</span>
	</div>
	<div id="bottom" class="box">
		<span>bottom</span>
	</div>
	
	<button id="html">html</button>
 -->
 
 	

<select name="city" xm-select="select1">
    <option value="1" disabled="disabled">北京</option>
    <option value="2" selected="selected">上海</option>
    <option value="3">广州</option>
    <option value="4" selected="selected">深圳</option>
    <option value="5">天津</option>
</select>
</body>
</html>
