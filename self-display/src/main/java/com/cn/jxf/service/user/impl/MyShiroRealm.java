package com.cn.jxf.service.user.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
import com.cn.jxf.mapper.system.ResourceMapper;
import com.cn.jxf.mapper.system.UserRoleMapper;
import com.cn.jxf.mapper.user.UserMapper;

public class MyShiroRealm extends AuthorizingRealm {

	@Resource
	private UserMapper userMapper;
	
	@Resource
	private UserRoleMapper userRoleMapper;
	
	@Resource
	private ResourceMapper resourceMapper;
	
	
	/**
	 * shiro的登录验证，当调用SecurityUtils.getSubject().login(new UsernamePasswordToken(userCode,userPassword));
	 * 会调用该方法，内部去判断用户名和密码是否正确
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
	    System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
	    //获取用户的输入的账号.
	    String userCode = (String)token.getPrincipal();
	    System.out.println(token.getCredentials());
	    //通过username从数据库中查找 User对象，如果找到，没找到.
	    //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
	    User userInfo = userMapper.findUserByCode(userCode);
	    System.out.println("----->>userInfo="+userInfo);
	    if(userInfo == null){
	        return null;
	    }
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				userInfo.getUserCode(), userInfo.getPassWord(), userInfo.getUserName());
	    return authenticationInfo;
	}
	
	/**
	 * shiro的权限验证，在页面上用标签判断显示或隐藏
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		   System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
		    SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		    User userInfo  = (User)principals.getPrimaryPrincipal();
		    
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", userInfo.getUserId());
			List<UserRole> urs = userRoleMapper.searchUserRole(map);
			Integer roleId = null;
			if (urs != null && urs.size() > 0) {
				UserRole userRole = urs.get(0);
				roleId = userRole.getRoleId();
			}
			List<String> permissions = new ArrayList<String>();
			if (roleId!=null) {
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("roleId", roleId);
				//map1.put("resLevel", 5);
				List<Res> list = resourceMapper.searchAllResource(map1);
				
				if(list != null && list.size()>0){
					for(Res res : list){
						permissions.add(res.getResLogo());
					}
				}
			}
		  /*  for(UserRole role:urs){
		        authorizationInfo.addRole(role.);
		        for(SysPermission p:role.getPermissions()){
		            authorizationInfo.addStringPermission(p.getPermission());
		        }
		    }*/
			//给当前用户设置权限
			authorizationInfo.addStringPermissions(permissions); 
		    return authorizationInfo;
	}
}
