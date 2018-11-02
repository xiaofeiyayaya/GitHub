package com.cn.jxf.gtja;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
import org.apache.tools.ant.taskdefs.Length;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class ApiImpl {

	public static void main(String[] args) throws Exception {

		
		//getIntelligence_mohu("昌黎县农村信用合作联社");
		//getElecreditName("天弘");
		//getName("91150100736129456G");
		//getServiceName();
		getEnterpriseLogo();
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
			httpPost.addHeader("Host", "77099710-d45b-7ebb-71c8-d3f2a22af9f0");

			// 设置代理服务器
			HttpHost proxy = new HttpHost("172.16.0.20", 3128);
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

			System.setProperty("http.proxyHost", "172.16.0.20");
			System.setProperty("http.proxyPort", "3128");
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

	// unicode转String
	/*
	 * public static String unicode2String(String unicode){
	 * if(StringUtils.isBlank(unicode))return null; StringBuilder sb = new
	 * StringBuilder(); int i = -1; int pos = 0;
	 * 
	 * while((i=unicode.indexOf("\\u", pos)) != -1){
	 * sb.append(unicode.substring(pos, i)); if(i+5 < unicode.length()){ pos =
	 * i+6; sb.append((char)Integer.parseInt(unicode.substring(i+2, i+6), 16));
	 * } } return sb.toString(); }
	 */

	private static void getIntelligence_mohu(String key) {
		Map<String, String> map = new HashMap<String, String>();

		map.put("key", key);
		map.put("type", "2");
		map.put("userid", "Z2HO3fdC");

		String url = "http://open.elecredit.com/intelligence_mohu/";
		map.put("url", url);

		String res = SendPost(map);
		System.out.println(res);

		JSONObject obj = JSONObject.fromObject(res);
		System.out.println("-------------"+obj.toString());
		String code = obj.getString("code");
		String data = obj.getString("data");
		JSONArray arr = JSONArray.fromObject(data);
		System.out.println(data);

		if ("200".equals(code)) {
			List<YsTrade> list = (List<YsTrade>) JSONArray.toList(arr, new YsTrade(), new JsonConfig());
			if (list != null) {
				for (YsTrade trade : list) {
					System.out.println("出参---------------------------：" + trade.getName());
					System.out.println(trade.getUniscid());
				}
			}
		} else {
			System.out.println("---------------------------" + code);
		}
	}

	private static String getElecreditName(String key) {
		String result_str = "";
		String url = "";

		Map<String, String> map = new HashMap<String, String>();
		map.put("key", key);
		map.put("userid", "Z2HO3fdC");

		url = "http://open.elecredit.com/mohu/";

		map.put("url", url);
		String res = SendPost(map);
		JSONObject obj2 = null;
		try {
			obj2 = JSONObject.fromObject(res);
			System.out.println("-------------"+obj2.toString());
		} catch (JSONException e) {
			return result_str;
		}

		// 模糊匹?接口?回值
		JSONArray jsonArray2 = JSONArray.fromObject(obj2.get("data").toString());
		if (jsonArray2.size() > 0) {
			for (int i = 0; i < jsonArray2.size(); i++) {
				JSONObject job = jsonArray2.getJSONObject(i);
				if (i >= 1)
					result_str += ",";
				result_str += job.get("ENTNAME");

			}
		}

		System.out.println("result_str=" + result_str);
		return result_str;
	}

	public static void getName(String key) {
		String result_str = "";
		String url = "";
		JSONObject obj = null;
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", key);
		map.put("userid", "Z2HO3fdC");
		url = "http://open.elecredit.com/comname/";
		map.put("url", url);
		String res = SendPost(map);

		try {
			obj = JSONObject.fromObject(res);
			System.out.println("-------------"+obj.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}

		if ("200".equals(obj.get("code").toString())) {
			result_str = obj.get("data").toString();
			System.out.println("result_str=" + result_str);
		} else {
			System.out.println("result_str=" + obj.toString());
		}
	}
	
	public static void getServiceName() {
		String result_str = "";
		String url = "";
		JSONObject obj = null;
		Map<String, String> map = new HashMap<String, String>();
		map.put("entid", "GHaf8AY6jC");
		map.put("userid", "Z2HO3fdC");
		map.put("version", "A1");
		url = "http://open.elecredit.com/elsaic/?isjson=1";
		map.put("url", url);
		String res = SendPost(map);

		try {
			obj = JSONObject.fromObject(res);
			System.out.println("-------------"+obj.toString());
			String meta = obj.getString("meta");
			JSONObject metaJson = JSONObject.fromObject(meta);
			String code = metaJson.getString("code");
			System.out.println(code);
		} catch (JSONException e) {
			e.printStackTrace();
		}
/*
		if ("200".equals(obj.get("code").toString())) {
			result_str = obj.get("data").toString();
			System.out.println("result_str=" + result_str);
		} else {
			System.out.println("result_str=" + obj.toString());
		}*/
	}
	public static void getEnterpriseLogo() {
		String result_str = "";
		String url = "";
		JSONObject obj = null;
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "元素征信有限责任公司");
		map.put("type", "3");
		map.put("userid", "Z2HO3fdC");
		url = "http://open.elecredit.com/getentid/";
		map.put("url", url);
		String res = SendPost(map);

		try {
			obj = JSONObject.fromObject(res);
			System.out.println("-------------"+obj.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}

	/*	if ("200".equals(obj.get("code").toString())) {
			result_str = obj.get("data").toString();
			System.out.println("result_str=" + result_str);
		} else {
			System.out.println("result_str=" + obj.toString());
		}*/
	}
	public static void aaa() {
		String result_str = "";
		String url = "";
		JSONObject obj = null;
		Map<String, String> map = new HashMap<String, String>();
		map.put("ent_name", "广东省医师协会");
		map.put("ent_org_code", "74553594X");
		map.put("ent_reg_no", "5144000074553594X3");
		map.put("userid", "Z2HO3fdC");
		url = "http://open.elecredit.com/ys_nacao/";
		map.put("url", url);
		String res = SendPost(map);

		try {
			obj = JSONObject.fromObject(res);
			System.out.println("-------------"+obj.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}

		if ("200".equals(obj.get("code").toString())) {
			result_str = obj.get("data").toString();
			System.out.println("result_str=" + result_str);
		} else {
			System.out.println("result_str=" + obj.toString());
		}
	}
}
