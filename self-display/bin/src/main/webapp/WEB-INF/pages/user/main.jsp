<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	helloworld!!!<br/>
	<b id="b" onclick="dianji()">111111111</b>
</body>
<script type="text/javascript">
	function dianji(){
		//this.innerHTML = 22222222;
		$("#b").val("22222");
		console.log($(this));
		alert($("#b").val());
	}
	
</script>
</html>