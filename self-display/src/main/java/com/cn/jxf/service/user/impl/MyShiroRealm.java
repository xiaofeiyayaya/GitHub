package com.cn.jxf.service.user.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.cn.jxf.domain.system.Res;
import com.cn.jxf.domain.system.UserRole;
import com.cn.jxf.domain.user.User;
import com.cn.jxf.service.system.ResourceService;
import com.cn.jxf.service.system.UserRoleService;
import com.cn.jxf.service.user.UserCacheService;

public class MyShiroRealm extends AuthorizingRealm {

	@Resource(name = "userRoleService")
	private UserRoleService userRoleService;
	
	@Resource(name = "resourceService")
	private ResourceService resourceService;
	
	@Resource(name = "userCacheService")
	private UserCacheService userCacheService;
	
	/**
	 * shiro的登录验证，当调用SecurityUtils.getSubject().login(new UsernamePasswordToken(userCode,userPassword));
	 * 会调用该方法，内部去判断用户名和密码是否正确
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String usercode = (String)token.getPrincipal();
		User user = userCacheService.getUserByCode(usercode);
		if (user == null) {
			return null;
		}

		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				user.getUserCode(), user.getPassWord(), user.getUserName());
		return authenticationInfo;
	}
	
	/**
	 * shiro的权限验证，在页面上用标签判断显示或隐藏
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		//User user = (User)principals.getPrimaryPrincipal();
		
		String userCode = (String) super.getAvailablePrincipal(principals);
		User user = userCacheService.getUserByCode(userCode);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", user.getUserId());
		List<UserRole> urs = userRoleService.searchUserRole(map);
		String roleId = new String();
		if (urs != null && urs.size() > 0) {
			UserRole userRole = urs.get(0);
			roleId = userRole.getRoleId();
		}
		
		List<String> permissions = new ArrayList<String>();
		if (StringUtils.isNotBlank(roleId)) {
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("roleId", roleId);
			map1.put("resLevel", 5);
			List<Res> list = resourceService.searchResource(map1);
			
			if(list != null && list.size()>0){
				for(Res res : list){
					permissions.add(res.getResLogo());
				}
			}
		}
		
		//给当前用户设置权限
		authorizationInfo.addStringPermissions(permissions); 
		return authorizationInfo;
	}
}
