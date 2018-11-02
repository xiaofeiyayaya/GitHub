layui.use([ 'form', 'layer' ], function() {
	var $ = layui.jquery,
	form = layui.form,
	layer = layui.layer;


	// 监听提交
	form.on('submit(save)', function(data) {
		save();
		return false;
	});

});

function save() {
	var json = $("#info_list_form").serialize();
	$.ajax({
		url : "../user/addUser",
		type : "POST",
		data : json,
		dataType : "json",
		success : function(data) {
			if ("true" == data.result) {
				layer.alert("操作成功！", {
					closeBtn : 0
				}, function() {
					parent.window.location.reload(true);
				});
			} else if ("false" == data.result) {}
		}
	})
}
