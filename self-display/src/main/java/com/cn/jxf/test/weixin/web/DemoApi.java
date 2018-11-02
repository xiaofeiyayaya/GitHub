package com.cn.jxf.test.weixin.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.apache.commons.httpclient.util.HttpURLConnection;

public class DemoApi {
	/**
	 * 模拟form表单的形式 ，上传文件 以输出流的形式把文件写入到url中，然后用输入流来获取url的响应
	 * 
	 * @param url
	 *            请求地址 form表单url地址
	 * @param filePath
	 *            文件在服务器保存路径
	 * @param title
	 *            视频标题
	 * @param introduction
	 *            视频描述
	 * @return
	 */
	public static String uploadVideo(String url, String filePath, String title, String introduction) {
		String result = null;

		HttpURLConnection downloadCon = null;
		InputStream inputStream = null;
		try {
			URL urlFile = new URL(filePath);
			downloadCon = (HttpURLConnection) urlFile.openConnection();
			inputStream = downloadCon.getInputStream();

			// 通知Java您要通过代理进行连接
			System.getProperties().put("proxySet", "true");
			// 指定代理所在的服务器
			System.getProperties().put("proxyHost", "172.16.0.1");
			// 指定代理监听的端口
			System.getProperties().put("proxyPort", "3128");
			URL urlObj = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(30000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Cache-Control", "no-cache");
			String boundary = "-----------------------------" + System.currentTimeMillis();
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

			OutputStream output = conn.getOutputStream();
			output.write(("--" + boundary + "\r\n").getBytes());
			String regex = ".*/([^\\.]+)";
			output.write(String.format("Content-Disposition: form-data; name=\"media\"; filename=\"%s\"\r\n",
					filePath.replaceAll(regex, "$1")).getBytes());
			output.write("Content-Type: video/mp4 \r\n\r\n".getBytes());
			int bytes = 0;
			byte[] bufferOut = new byte[1024];
			while ((bytes = inputStream.read(bufferOut)) != -1) {
				output.write(bufferOut, 0, bytes);
			}
			inputStream.close();

			output.write(("--" + boundary + "\r\n").getBytes());
			output.write("Content-Disposition: form-data; name=\"description\";\r\n\r\n".getBytes());
			output.write(String.format("{\"title\":\"%s\", \"introduction\":\"%s\"}", title, introduction).getBytes());
			output.write(("\r\n--" + boundary + "--\r\n\r\n").getBytes());
			output.flush();
			output.close();
			inputStream.close();
			InputStream resp = conn.getInputStream();
			StringBuffer sb = new StringBuffer();
			while ((bytes = resp.read(bufferOut)) > -1)
				sb.append(new String(bufferOut, 0, bytes, "utf-8"));
			resp.close();
			result = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	public static void main(String[] args) throws IOException {
		String videoUrl = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=14_Zi9tdcH8KfxHogp0uFfj9YFRU2bMJHjR7y9UAed0fUtIcjPzg4q_3aisS2TgVNyyTeRmZxCejavTWbVGZTRzEoiuMxFP8qr_RsYCGp6ukb2zCoi2qHn2mo_iCGB4m2I-qFjIsrn_ZhmohzaUHLRhAJAAEK&type=image"; // ACCESS_TOKEN改成自己的
		String filePath = "http://p2tdux.natappfree.cc/images/login.jpg"; // 服务器路径，比如说localhost:8080/WeChat/file/xxx.mp4
		System.out.println(uploadVideo(videoUrl, filePath, "标题", "描述"));
	}

}
