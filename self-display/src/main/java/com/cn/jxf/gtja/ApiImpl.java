package com.cn.jxf.gtja;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;

public class ApiImpl {

	public static void main(String[] args) throws Exception {
		Map<String,String> map = new HashMap<>();
		// 1.基础模糊接口查询 名字查询企业名称 返回企业名称
		// map.put("key","元素征信");
		// map.put("userid","Z2HO3fdC");
		// String url = "http://open.elecredit.com/mohu/";

		// 模糊查询
		// 2.返回name 匹配到的企业名称
		// uniscid 信用代码/注册号
		// faren 法人姓名
		// regcap 注册资金
		// esdate 开业日期
		//


		map.put("key", "襄城县农村信用合作社联合社");
		//1 当可以确定唯一是返回一个匹配结果， 否则返回为空
		//2 当可以确定唯一是返回一个匹配结果， 否则最大返回 3 个
		map.put("type", "2");
		map.put("userid", "Z2HO3fdC");

		String url = "http://open.elecredit.com/intelligence_mohu/";
		map.put("url", url);

		// 3.代码查询返回企业名称

		// map.put("key","911101080628135175");
		// map.put("userid","Z2HO3fdC");

		// String url = "http://open.elecredit.com/comname/";
		String res = SendPost(map);
		String resultStr = "";
		if (res != null) {
			resultStr = JSONObject.fromObject(res).toString();
		}
		System.out.println(resultStr);

	}

	public static String SendPost(Map<String, String> map) {

		java.lang.String res = "";
		java.lang.String authorization1 = HMACSHA1.formatUrlMap(map);
		System.out.println(authorization1);
		try {
			java.lang.String authorization = HMACSHA1.hmacSHA1("jeEZwWbR", authorization1);
			System.out.println(authorization);
			res = doPost(map.get("url"), map, "UTF-8", authorization);
		} catch (Exception ex) {

		}

		return res;

	}

	public static String doPost(String url, Map<String, String> map, String charset, String Auth) {
		CloseableHttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		try {
			httpClient = HttpClients.createDefault();
			httpPost = new HttpPost(url);
			httpPost.addHeader("content-type", "application/x-www-form-urlencoded");
			httpPost.addHeader("authorization", "" + Auth + "");
			httpPost.addHeader("cache-control", "no-cache");
			httpPost.addHeader("postman-token", "77099710-d45b-7ebb-71c8-d3f2a22af9f0");
			httpPost.setHeader("charset", "UTF-8");

			// 设置代理服务器
			HttpHost proxy = new HttpHost("172.16.0.1", 3128);
			RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
			// 添加代理服务器
			httpPost.setConfig(config);
			
			// 设置参数
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			Iterator iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, String> elem = (Map.Entry<String, String>) iterator.next();
				list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
			}
			if (list.size() > 0) {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
				httpPost.setEntity(entity);
			}
			HttpResponse response = httpClient.execute(httpPost);

			if (response != null) {

				HttpEntity resEntity = response.getEntity();

				Header header = resEntity.getContentType();
				if (resEntity != null) {

					result = EntityUtils.toString(resEntity, charset);

					// String res1 =
					// "\\u5143\\u7d20\\u5f81\\u4fe1\\u6709\\u9650\\u8d23\\u4efb\\u516c\\u53f8";
					// String res2 = decodeUnicode(res1);
					return result;

				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

}
