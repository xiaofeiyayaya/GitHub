<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.8.3.min.js"></script>
<title>Insert title here</title>
<style type="text/css">
		a {
		   width: 48px;
		}
		
		.title {
		   width: 24px;
		}
		
		.select{padding:5px 10px;border:#ddd 1px solid;border-radius:4px;width:60%;margin:5% auto;font-size:12px}
		.select li{list-style:none;padding:10px 0 5px 100px}
		.select .select-list{border-bottom:#eee 1px dashed}
		.select dl{zoom:1;position:relative;line-height:24px;}
		.select dl:after{content:" ";display:block;clear:both;height:0;overflow:hidden}
		.select dt{width:100px;margin-bottom:5px;position:absolute;top:0;left:-100px;text-align:right;color:#666;height:24px;line-height:24px}
		.select dd{float:left;display:inline;margin:0 0 5px 5px;}
		.select a{display:inline-block;white-space:nowrap;height:24px;padding:0 10px;text-decoration:none;color:#039;border-radius:2px;}
		.select a:hover{color:#f60;background-color:#f3edc2}	
		.select .selected a{color:#fff;background-color:#f60}
		.select .selecteds a{color:#fff;background-color:#f60}
		.select-result dt{font-weight:bold}
		.select-no{color:#999}
		.select .select-result a{padding-right:20px;background:#f60 url("../../images/close.gif") right 9px no-repeat}
		.select .select-result a:hover{background-position:right -15px}
		.eliminateCriteria{line-height:21px;margin-top:4px;width:80px;float:left;color:#118ecc;cursor:pointer;}
		</style>

</head>
<script type="text/javascript">
	$(document).ready(function(){
							
	$("#select1 dd").click(function () {
		$(this).addClass("selected").siblings().removeClass("selected");
		if ($(this).hasClass("select-all")) {
			$("#selectA").remove();
		} else {
			var copA = $(this).clone();
			var copyThisA = copA.removeClass("selected").addClass("selecteds");
			if ($("#selectA").length > 0) {
				$("#selectA a").html($(this).text());
			} else {
				$(".select-result dl").append(copyThisA.attr("id", "selectA"));
			}
		}
	});
	
	$("#select2 dd").click(function () {
		$(this).addClass("selected").siblings().removeClass("selected");
		if ($(this).hasClass("select-all")) {
			$("#selectB").remove();
		} else {
			var copB = $(this).clone();
			var copyThisB = copB.removeClass("selected").addClass("selecteds");
			if ($("#selectB").length > 0) {
				$("#selectB a").html($(this).text());
			} else {
				$(".select-result dl").append(copyThisB.attr("id", "selectB"));
			}
		}
	});
	
	$("#select3 dd").click(function () {
		$(this).addClass("selected").siblings().removeClass("selected");
		if ($(this).hasClass("select-all")) {
			$("#selectC").remove();
		} else {
			var copC = $(this).clone();
			var copyThisC = copC.removeClass("selected").addClass("selecteds");
			if ($("#selectC").length > 0) {
				$("#selectC a").html($(this).text());
			} else {
				$(".select-result dl").append(copyThisC.attr("id", "selectC"));
			}
		}
	});
	
	$("#select4 dd").click(function () {
	
		$(this).addClass("selected").siblings().removeClass("selected");
		if ($(this).hasClass("select-all")) {
			$("#selectD").remove();
		} else {
			var copD = $(this).clone();
			var copyThisD = copD.removeClass("selected").addClass("selecteds");
			if ($("#selectD").length > 0) {
				$("#selectD a").html($(this).text());
			} else {
				$(".select-result dl").append(copyThisD.attr("id", "selectD"));
			}
		}
	});
	
	$("#selectA").live("click", function () {
		$(this).remove();
		$("#select1 .select-all").addClass("selected").siblings().removeClass("selected");
	});
	
	$("#selectB").live("click", function () {
		$(this).remove();
		$("#select2 .select-all").addClass("selected").siblings().removeClass("selected");
	});
	
	$("#selectC").live("click", function () {
		$(this).remove();
		$("#select3 .select-all").addClass("selected").siblings().removeClass("selected");
	});
	
	$("#selectD").live("click", function () {
		$(this).remove();
		$("#select4 .select-all").addClass("selected").siblings().removeClass("selected");
	});
	
	$(".select dd").live("click", function () {
		if ($(".select-result dd").length > 1) {
			$(".select-no").hide();
		} else {
			$(".select-no").show();
		}
	});
	
	$("#deleteAll").click(function () {
		var classes = $('#mydl').children('dd').length;
		if(classes>0){
			$("#select1 .select-all").addClass("selected").siblings().removeClass("selected");
			$("#select2 .select-all").addClass("selected").siblings().removeClass("selected");
			$("#select3 .select-all").addClass("selected").siblings().removeClass("selected");
			$("#select4 .select-all").addClass("selected").siblings().removeClass("selected");
			$('#mydl').children('dd').remove();
		}else {
			return;
		}

	})
	
	$("#test").click(function(){
		var keys = [];
		var values = [];
		var list = document.getElementsByClassName("selected");
		for(var i = 0;i<list.length;i++){
			keys.push(list[i].attributes.name.value);	
			values.push(list[i].innerText);			
		}
		//console.log(keys);
		//console.log(values);
	 $.ajax({
			url : "../user/query?keys="+keys+"&"+"values="+values,
			type : "POST",
			dataType : "json",
			success : function(data) {
				if ("true" == data.result) {}
			}
		});
	})
	
});

</script>
</head>

<body>
<form>
	<ul class="select" id="selectList">
		<li class="select-result">
				<dl id="mydl" >
					<div style="float:right;cursor:pointer;" id="deleteAll">【清空全部】 </div>
					<dt>已选条件：</dt>
					<!--<dd class="select-no">暂时没有选择过滤条件</dd>-->
				</dl>
		</li>

		<li class="select-list">
			<dl id="select3">
				<dt>总资产：</dt>
				<dd class="select-all selected" name="总资产"><a href="#" class="title">全部</a></dd>
				<dd name="总资产"><a href="#" >500万</a></dd>
				<dd name="总资产"><a href="#" >1000万</a></dd>
				<dd name="总资产"><a href="#" >5000万</a></dd>
				<dd name="总资产"><a href="#" >10000万</a></dd>
			</dl>
		</li>
		
		<li class="select-list">
			<dl id="select1">
				<dt>公司类型：</dt>
				<dd class="select-all selected" name="公司类型"><a href="#" class="title">全部</a></dd>
				<dd name="公司类型"><a href="#">私有企业</a></dd>
				<dd name="公司类型"><a href="#">国有企业</a></dd>
				<dd name="公司类型"><a href="#">融资企业</a></dd>
				<dd name="公司类型"><a href="#">外国企业</a></dd>
			</dl>
		</li>
		
		<li class="select-list">
			<dl id="select2">
				<dt>公司规模：</dt>
				<dd class="select-all selected" name="公司规模"><a href="#" class="title">全部</a></dd>
				<dd name="公司规模"><a href="#"><100</a></dd>
				<dd name="公司规模"><a href="#" >100-500</a></dd>
				<dd name="公司规模"><a href="#">500-1000</a></dd>
				<dd name="公司规模"><a href="#">1000<</a></dd>
			</dl>
		</li>
		

		
		<li class="select-list">
			<dl id="select4">
				<dt>公司主营业务：</dt>
				<dd class="select-all selected" name="公司主营业务"><a href="#" class="title">全部</a></dd>
				<dd name="公司主营业务"><a href="#">电子科技</a></dd>
				<dd name="公司主营业务"><a href="#">智能家居</a></dd>
				<dd name="公司主营业务"><a href="#">风险投资</a></dd>
				<dd name="公司主营业务"><a href="#">人工智能</a></dd>
			</dl>
		</li>
		<div>
			<input id="test" type="button" style="width:30px;" value="查询"></input>
		</div>
	</ul>
	</form>
</body>
</html>