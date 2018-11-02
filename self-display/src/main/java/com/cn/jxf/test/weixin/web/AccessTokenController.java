package com.cn.jxf.test.weixin.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.servlet4preview.ServletContext;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cn.jxf.test.weixin.commom.AccessTokenInfo;
import com.cn.jxf.test.weixin.domain.AccessToken;
import com.cn.jxf.test.weixin.util.NetWorkUtils;
import com.cn.jxf.util.HttpClientUtil;

//@Component // 被spring容器管理
//@Order(1) // 如果多个自定义ApplicationRunner，用来标明执行顺序
public class AccessTokenController implements ApplicationRunner {

	private static final String APPID = "wx4cd6995af1190e5c";
	private static final String APPSECRET = "9225f3347821e7064b80f3ab6848d57f";
	private static String data = "";
	
	
	@Autowired
	HttpServletRequest request; // 这里可以获取到request

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("启动WebServlet");
		// 开启一个新的线程
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						// 获取accessToken
						AccessTokenInfo.accessToken = getAccessToken(APPID, APPSECRET);
						// 获取成功
						if (AccessTokenInfo.accessToken != null) {
							// 获取到access_token 休眠7000秒,大约2个小时左右
							Thread.sleep(7000 * 1000);
							// Thread.sleep(10 * 1000);//10秒钟获取一次
						} else {
							// 获取失败
							Thread.sleep(1000 * 3); // 获取的access_token为空 休眠3秒
						}
					} catch (Exception e) {
						System.out.println("发生异常：" + e.getMessage());
						e.printStackTrace();
						try {
							Thread.sleep(1000 * 10); // 发生异常休眠1秒
						} catch (Exception e1) {

						}
					}
				}

			}
		}).start();

	}

	/**
	 * 获取access_token
	 *
	 * @return AccessToken
	 */
	private AccessToken getAccessToken(String appid, String appsecret) {
		// NetWorkUtils netHelper = new NetWorkUtils();
		/**
		 * 接口地址为https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET，其中grant_type固定写为client_credential即可。
		 */
		String Url = String.format(
				"https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", APPID,
				APPSECRET);
		// 此请求为https的get请求，返回的数据格式为{"access_token":"ACCESS_TOKEN","expires_in":7200}
		JSONObject json = HttpClientUtil.get(Url);
		// String result = netHelper.getHttpsResponse(Url, "");
		System.out.println("获取到的access_token=" + json.getString("access_token"));

		AccessToken token = new AccessToken();
		token.setAccessToken(json.getString("access_token"));
		token.setExpiresin(json.getInteger("expires_in"));

		return token;
	}

	public static JSONObject getAccess_Token() {
		String Url = String.format(
				"https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", APPID,
				APPSECRET);
		// 此请求为https的get请求，返回的数据格式为{"access_token":"ACCESS_TOKEN","expires_in":7200}
		JSONObject json = HttpClientUtil.get(Url);
		return json;
	}
}
