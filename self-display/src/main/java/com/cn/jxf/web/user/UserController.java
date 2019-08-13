package com.cn.jxf.web.user;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.jxf.config.Constants;
import com.cn.jxf.domain.system.Page;
import com.cn.jxf.domain.system.Res;
import com.cn.jxf.domain.system.Role;
import com.cn.jxf.domain.system.UserRole;
import com.cn.jxf.domain.user.User;
import com.cn.jxf.service.system.ResourceService;
import com.cn.jxf.service.system.RoleCacheService;
import com.cn.jxf.service.system.UserRoleService;
import com.cn.jxf.service.user.UserCacheService;
import com.cn.jxf.service.user.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "userCacheService")
	private UserCacheService userCacheService;

	@Resource(name = "userRoleService")
	private UserRoleService userRoleService;

	@Resource(name = "resourceService")
	private ResourceService resourceService;

	@Resource(name = "roleCacheService")
	private RoleCacheService roleCacheService;

	/**
	 * 退出
	 */
	@RequestMapping("/logout")
	public void logout() {
		Subject user = SecurityUtils.getSubject();
		if (user.isAuthenticated()) {
			user.logout();
		}
	}

	/**
	 * 登陆超时
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/dologin")
	public String dologin() {
		return "/user/dologin";
	}

	/**
	 * 登录方法
	 * 
	 * @param userCode
	 * @param userPassword
	 * @param rememberme
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/login")
	public String logon(
			Model model,
			@RequestParam(value = "userCode", required = false) String userCode,
			@RequestParam(value = "passWord", required = false) String passWord,
			boolean rememberme, HttpSession session, HttpServletRequest request) {
		Subject user = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(userCode,
				passWord);
		token.setRememberMe(rememberme);
		try {
			user.login(token);

			User usersys = userCacheService.getUserByCode(userCode);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", usersys.getId());
			List<UserRole> urs = userRoleService.searchUserRole(map);
			String roleId = new String();
			if (urs != null && urs.size() > 0) {
				UserRole userRole = urs.get(0);
				roleId = userRole.getRoleId();
				/*if (StringUtils.isNotBlank(roleId)) {
					Role role = roleCacheService.getRoleByKey(roleId);
					int flag = role.getFlag();
					if (0 == flag) {
						roleId = "";
					}
				}*/

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

	/*** 主框架 ***/
	@RequestMapping("/main")
	public String main(Model model, HttpSession session,
			@RequestParam(value = "resId", required = false) String resId) {

		User user = userCacheService.getCurrentUser();
		model.addAttribute("userName", user.getUserName());

		String roleId = (String) session.getAttribute("roleId");
		if (StringUtils.isNotBlank(roleId)) {
			List<Res> toplist = new ArrayList<Res>();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("roleId", roleId);
			map.put("resLevel", 2);
			toplist = resourceService.searchResource(map);
			model.addAttribute("toplist", toplist);

			if (!StringUtils.isNotBlank(resId)) {
				if (toplist != null && toplist.size() > 0) {
					resId = String.valueOf(toplist.get(0).getResId());
				}
			}

			model.addAttribute("resId", resId);

			List<Res> list = new ArrayList<Res>();
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("roleId", roleId);
			map1.put("resLevel", 3);
			map1.put("parentId", resId);
			List<Res> list1 = resourceService.searchResource(map1);

			if (list1 != null && list1.size() > 0) {
				for (Res res1 : list1) {
					Map<String, Object> map2 = new HashMap<String, Object>();
					map2.put("roleId", roleId);
					map2.put("resLevel", 4);
					map2.put("parentId", res1.getResId());
					List<Res> list2 = resourceService.searchResource(map2);
					if (list2 != null && list2.size() > 0) {
						res1.setRess(list2);
						list.add(res1);
					}
				}
			}

			model.addAttribute("list", list);
			model.addAttribute("firstResLogo", list.get(0).getRess().get(0)
					.getResLogo());
			model.addAttribute("firstResName", list.get(0).getRess().get(0)
					.getResName());
			model.addAttribute("firstResUrl", list.get(0).getRess().get(0)
					.getUrl());
			model.addAttribute("firstResId", list.get(0).getRess().get(0)
					.getResId());
		} else {
			model.addAttribute("error", "此用户无权限");
			return "login";
		}

		return "/user/main";
	}

	/**
	 * 用户查询列表
	 * 
	 * @param model
	 * @param userName
	 * @return
	 */

	@RequestMapping("/userList")
	public String userList() {
		return "user/userList";
	}

	@RequestMapping("/userPage")
	@ResponseBody
	public Page<User> userPage(
			Model model,
			@RequestParam(value = "userCode", required = false) String userCode,
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "limit", required = false, defaultValue = Constants.PAGE_SIZE) String limit)
			throws UnsupportedEncodingException {

		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(userCode)) {
			map.put("userCode", URLDecoder.decode(userCode, "utf-8"));
			model.addAttribute("userCode", userCode);
		}
		if (StringUtils.isNotBlank(userName)) {
			map.put("userName", URLDecoder.decode(userName, "utf-8"));
			model.addAttribute("userName", userName);
		}

		map.put("orderby", "update_time");
		map.put("order", "desc");
		map.put("page", page);
		map.put("limit", limit);

		Page<User> pages = userService.listUser(map);
		return pages;
	}

	@RequestMapping("/toAddUser")
	public String toAddUser() {
		return "/user/addUser";
	}

	@RequestMapping("/userAdd")
	@ResponseBody
	public String userAdd(
			Model model,
			@RequestParam(value = "userCode", required = false) String userCode,
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "roleId", required = false) String roleId,
			@RequestParam(value = "passWord", required = false) String passWord,
			@RequestParam(value = "deptId", required = false) String deptId) {

		User user = userCacheService.getUserByCode(userCode);
		if (user != null) {// 判断usercode是否可用
			return "{\"result\":\"false\"}";
		} else {
			user = new User();
			user.setId(RandomStringUtils
					.randomAlphanumeric(Constants.KEY_LENGTH));
			user.setUserCode(userCode);
			user.setUserName(userName);
			user.setPassWord(passWord);
			///user.setIsSys("0");
			//user.setDeptId("");
			userCacheService.addUser(user);

			this.addUserRole(roleId, user.getId());
			return "{\"result\":\"true\"}";
		}
	}

	private void addUserRole(String roleId, String userId) {
		UserRole userRole = new UserRole();
		userRole.setId(RandomStringUtils
				.randomAlphanumeric(Constants.KEY_LENGTH));
		userRole.setUserId(userId);
		userRole.setRoleId(roleId);
		userRoleService.insertUserRole(userRole);
	}

	@RequestMapping("/toEditUser")
	public String toUserUpd(Model model,
			@RequestParam(value = "id", required = false) String id) {
		User user = userCacheService.getUserByCode(id);
		model.addAttribute("user", user);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", user.getId());
		List<UserRole> urs = userRoleService.searchUserRole(map);
		UserRole userRole = new UserRole();
		if (urs != null && urs.size() > 0) {
			userRole = urs.get(0);
		}
		model.addAttribute("userRole", userRole);
		return "/user/editUser";
	}

	@RequestMapping("/editUser")
	@ResponseBody
	public String editUser(Model model,
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "roleId", required = false) String roleId,
			@RequestParam(value = "userName", required = false) String userName) {

		User user = userCacheService.getUserByCode(id);
		user.setUserName(userName);
		userCacheService.updateUser(user);

		if (user != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", user.getId());
			userRoleService.deleteUserRoleByMap(map);

			this.addUserRole(roleId, user.getId());
		}

		return "{\"result\":\"true\"}";
	}

	@RequestMapping("/deleteUser")
	@ResponseBody
	public String deleteUser(Model model,
			@RequestParam(value = "id", required = false) String id) {

		User user = userCacheService.getUserByCode(id);
		String isSys = null;
		//String isSys = user.getIsSys();
		if ("0".equals(isSys)) {
			userCacheService.deleteUser(user);

			if (user != null) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userId", user.getId());
				userRoleService.deleteUserRoleByMap(map);
			}
			return "{\"result\":\"true\"}";
		} else {// 系统用户无法删除
			return "{\"result\":\"false\"}";
		}
	}

	@RequestMapping("/toResetUserPsd")
	public String toResetUserPsd(Model model,
			@RequestParam(value = "id", required = false) String id) {
		User user = userCacheService.getUserByCode(id);
		model.addAttribute("user", user);

		return "/user/resetUserPsd";
	}

	@RequestMapping("/resetUserPsd")
	@ResponseBody
	public String resetUserPsd(Model model,
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "passWord", required = false) String passWord) {
		User user = userCacheService.getUserByCode(id);
		user.setPassWord(passWord);
		userCacheService.updateUser(user);

		return "{\"result\":\"true\"}";
	}

	@RequestMapping("/toResetPsd")
	public String toResetPsd(Model model) {
		String curUserId = userCacheService.getCurrentUserId();
		User user = userCacheService.getUserByCode(curUserId);
		model.addAttribute("user", user);

		return "/user/resetPsd";
	}

	@RequestMapping("/resetPsd")
	@ResponseBody
	public String resetPsd(Model model,
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "passWord", required = false) String passWord) {
		User user = userCacheService.getUserByCode(id);
		user.setPassWord(passWord);
		userCacheService.updateUser(user);

		return "{\"result\":\"true\"}";
	}

}
