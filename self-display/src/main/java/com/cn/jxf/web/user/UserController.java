package com.cn.jxf.web.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.jxf.domain.system.Res;
import com.cn.jxf.domain.system.UserRole;
import com.cn.jxf.domain.user.User;
import com.cn.jxf.mapper.system.ResourceMapper;
import com.cn.jxf.mapper.system.UserRoleMapper;
import com.cn.jxf.mapper.user.UserMapper;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserMapper userMapper;

	@Resource
	private UserRoleMapper userRoleMapper;

	@Resource
	private ResourceMapper resourceMapper;

	public static final String BUSINESS1 = "activemq.user";

	@RequestMapping("/tologin")
	public String tologin() {
		return "login";
	}

	@RequestMapping("/login")
	public String login(Model model, @RequestParam(value = "userCode", required = false) String userCode,
			@RequestParam(value = "passWord", required = false) String passWord, boolean rememberme,
			HttpSession session, HttpServletRequest request) {
		Subject user = SecurityUtils.getSubject();
		rememberme = true;
		UsernamePasswordToken token = new UsernamePasswordToken(userCode, passWord,rememberme);
		token.setRememberMe(rememberme);
		try {
			user.login(token);

			User usersys = userMapper.findUserByCode(userCode);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", usersys.getUserId());
			List<UserRole> urs = userRoleMapper.searchUserRole(map);
			Integer roleId = null;
			if (urs != null && urs.size() > 0) {
				UserRole userRole = urs.get(0);
				roleId = userRole.getRoleId();
				/*
				 * if (roleId!=null) { Role role =
				 * roleCacheService.getRoleByKey(roleId); int flag =
				 * role.getFlag(); if (0 == flag) { roleId = ""; } }
				 */
			}
			session.setAttribute("roleId", roleId);
			return "redirect:/user/main";
		} catch (AuthenticationException e) {
			e.printStackTrace();
			token.clear();
			model.addAttribute("error", "用户名或密码错误");
			return "login";
		}
	}
	
	@RequestMapping("/main")
	public String main(HttpSession session){
		String roleId = session.getAttribute("roleId").toString();
		Subject currentUser = SecurityUtils.getSubject();
		String account = currentUser.getPrincipal().toString();
		User user = userMapper.findUserByCode(account);
		Map<String,Object> map = new HashMap<>();
		map.put("roleId", roleId);
		List<Res> resList = resourceMapper.searchResource(map);
		
		return "user/test2";
	}
	
	
	@RequestMapping("/query")
	@ResponseBody
	public String query(String keys [],String values []){
		
		
		return "helloworld!!!";
	}
	
	@RequestMapping("/toAddUser")
	public String toAddUser(){
		
		return "/user/addUser";
	}
	
	@RequestMapping("/addUser")
	public String addUser(User user){
		
		int insert = userMapper.insert(user);
		if(insert!=0){
			Map<String, String> msg = new HashMap<>();
			msg.put("id", user.getUserName());
		}
		
		return "hello springboot";
	}
	
	@RequestMapping("/demo")
	@ResponseBody
	public String demo(String name,String email,String address){
		
		return "{\"result\":\"true\",\"name\":\""+name+"\"}";
	}
}
