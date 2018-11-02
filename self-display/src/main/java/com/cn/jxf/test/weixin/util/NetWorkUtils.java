package com.cn.jxf.test.weixin.util;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * 访问网络用到的工具类
 * 
 * @author Administrator
 *
 */
public class NetWorkUtils {
	/**
	 * 发起Https请求
	 * 
	 * @param reqUrl
	 *            请求的URL地址
	 * @param requestMethod
	 * @return 响应后的字符串
	 */
	public String getHttpsResponse(String reqUrl, String requestMethod) {
		URL url;
		InputStream is;
		String resultData = "";
		try {
			// 通知Java您要通过代理进行连接
			System.getProperties().put("proxySet", "true");
			// 指定代理所在的服务器
			System.getProperties().put("proxyHost", "172.16.0.1");
			// 指定代理监听的端口
			System.getProperties().put("proxyPort", "3128");
			url = new URL(reqUrl);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			TrustManager[] tm = { xtm };

			SSLContext ctx = SSLContext.getInstance("TLS");
			ctx.init(null, tm, null);

			con.setSSLSocketFactory(ctx.getSocketFactory());
			con.setHostnameVerifier(new HostnameVerifier() {
				@Override
				public boolean verify(String arg0, SSLSession arg1) {
					return true;
				}
			});

			con.setDoInput(true); // 允许输入流，即允许下载

			// 在android中必须将此项设置为false
			con.setDoOutput(false); // 允许输出流，即允许上传
			con.setUseCaches(false); // 不使用缓冲
			if (null != requestMethod && !requestMethod.equals("")) {
				con.setRequestMethod(requestMethod); // 使用指定的方式
			} else {
				con.setRequestMethod("GET"); // 使用get请求
			}
			is = con.getInputStream(); // 获取输入流，此时才真正建立链接
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader bufferReader = new BufferedReader(isr);
			String inputLine;
			while ((inputLine = bufferReader.readLine()) != null) {
				resultData += inputLine + "\n";
			}
			System.out.println(resultData);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultData;
	}

	X509TrustManager xtm = new X509TrustManager() {
		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		@Override
		public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {

		}

		@Override
		public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {

		}
	};
}
