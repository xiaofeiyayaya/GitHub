<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>

<html lang="zh-cn">
<head>
<title>新增用户</title>
<script type="text/javascript" src="${ctx }/js/user/addUser.js"></script>

</head>
<body>
	<div class="padding_div">
		<div class="nrq">
			<form class="layui-form tip_form" id="info_list_form"
				onsubmit="return false;">
				<div id="self_cust" class="padding_div" style="clear: left;">
					<fieldset class="layui-elem-field">
						<div class="tab_s layui-field-box">
							<div class="layui-form-item">
								<div class="layui-inline">
									<label class="layui-form-label"><span
										style='color: red;'>*</span>&nbsp;&nbsp;姓名：</label>
									<div class="layui-input-inline">
										<input type="text" id="userName" name="userName" lay-verify=""
											maxlength="100" autocomplete="off" class="layui-input" />
									</div>
								</div>
							</div>
							<div class="layui-form-item">
								<div class="layui-inline">
									<label class="layui-form-label"><span
										style='color: red;'>*</span>&nbsp;&nbsp;账户：</label>
									<div class="layui-input-inline">
										<input type="text" id="userCode" name="userCode" lay-verify=""
											maxlength="100" autocomplete="off" class="layui-input" />
									</div>
								</div>
							</div>
							<div class="layui-form-item">
								<div class="layui-inline">
									<label class="layui-form-label"><span
										style='color: red;'>*</span>&nbsp;&nbsp;密码：</label>
									<div class="layui-input-inline">
										<input type="password" id="passWord" name="passWord" lay-verify=""
											maxlength="100" autocomplete="off" class="layui-input" />
									</div>
								</div>
							</div>
						</div>
					</fieldset>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block text_align_right">
						<button class="layui-btn layui-btn-normal layui-btn-small"
							lay-submit="" lay-filter="save">保存</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
