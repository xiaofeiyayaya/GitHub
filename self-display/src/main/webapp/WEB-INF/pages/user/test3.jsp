<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>jQuery 二级联动</title>
</head>
<body>

	<div style="padding: 20px; height: 600px;">
		<form class="layui-form" action="">
			<div style="width: 300px; display: inline-block; margin-right: 10px;">
				<select name="city" xm-select="method-value-example1" style="width: 200px;">
					<option value="1" >北京</option>
					<option value="2" >上海</option>
					<option value="3">广州</option>
					<option value="4" >深圳</option>
					<option value="5">天津</option>
				</select>
			</div>
		</form>
		<button class="layui-btn"
			onclick="on()">获取已选中数据的字符串值</button>
	</div>
	
	省份
	<select name="" id="allProvice" onChange="showCitys()">
		<option value="">省份</option>
		<option value="">广东</option>
		<option value="">广西</option>
	</select> 

</body>
<link rel="stylesheet" href="${ctx }/formSelects-v4.css" />
<script src="${ctx}/layui.all.js" type="text/javascript" charset="utf-8"></script>
<script src="${ctx}/formSelects-v4.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    var formSelects = layui.formSelects;
</script>
<script>
/* layui.formSelects.filter('method-value-example1', function(id, inputVal, val, isDisabled){
    if(
        PY.fullPY(val.name).toLowerCase().indexOf(inputVal) != -1 ||    //拼音全拼是否包含
        PY.fullPY(val.name, true).indexOf(inputVal) != -1 ||            //拼音简拼是否包含
        val.name.indexOf(inputVal) != -1                                //文本是否包含
    ){
        return false;
    }
    return true;
}); */
	formSelects.value('method-value-example1', ['2','4']);//赋值选中，为option的value值，数组形式，给上海和深圳选中
</script>
<script>
function showCitys(){
	alert("111111111");
}
</script>

<script>
function on(){
	var a=JSON.stringify(formSelects.value('method-value-example1', 'valStr'));//值为2,4（选中为上海，深圳）
	alert(a);
}
</script>
</html>
