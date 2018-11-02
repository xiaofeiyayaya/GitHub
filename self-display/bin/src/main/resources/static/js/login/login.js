$(function() {
	/* 用户名和密码输入框的监听，现实和隐藏提示 */
	$('#userCode').val("用户名").focus(function() {
		if ($('#userCode').val() == "用户名") {
			$('#userCode').val('');
		}
	}).blur(function() {
		if ($('#userCode').val() == "") {
			$('#userCode').val("用户名");
		}
	});

	$('#password-text').val("密  码").focus(function() {
		if ($('#password-text').val() == "密  码") {
			$('#password-text').hide();
			$('#userPassword').show().focus();
		}
	});

	$('#userPassword').blur(function() {
		if ($('#userPassword').val() == "") {
			$('#userPassword').hide();
			$('#password-text').val("密  码").show();
		}
	});
});

layui.config({
	base : 'layui/build/js/'
}).use([ 'form', 'layer' ], function() {
	var form = layui.form, layer = layui.layer, $ = layui.jquery;

	// 登录按钮事件
	// lay-filter='login'
	form.on("submit(login)", function(data) {
		return logon();
	});
})

function logon() {

	var userCode = $("#userCode").val();
	var userPassword = $('#userPassword').val();
	if (userCode == "" || userPassword == "") {
		showMsg("用户名或密码不能为空");
		return false;
	}

	showMsg("正在验证用户");
	setTimeout("return true;", 500);
}

function showMsg(msg) {
	$('#ajaxMsg').html(msg);
	$('#ajaxMsg').show();
	setTimeout("$('#ajaxMsg').hide();", 2000);
}
