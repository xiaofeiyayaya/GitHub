package com.cn.jxf.web.Import;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/import")
public class Import {

	@RequestMapping("/importFile")
	@ResponseBody
	public String importFile(MultipartFile file) throws IOException {
		String path = "D:/上传";

		// 创建文件
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		// String username = (String)
		// request.getSession().getAttribute("userName");
		// 返回原来在客户端的文件系统的文件名
		String fileName = file.getOriginalFilename();
		// username+后缀名

		String img = new Random().nextInt() + fileName.substring(fileName.lastIndexOf("."));// zhao.jpg
		FileOutputStream imgOut = new FileOutputStream(new File(dir, img));// 根据dir抽象路径名和img路径名字符串创建一个新File实例。

		imgOut.write(file.getBytes());// 返回一个字节数组文件的内容
		imgOut.close();
		Map<String, String> map = new HashMap<String, String>();

		String rpath = path + "/" + img;
		map.put("rPath", rpath);
		JSONObject jsonObject = JSONObject.fromObject(map);// 将json字符串转换为json对象
		String r = jsonObject.toString();
		String s = URLEncoder.encode(r, "utf-8");// 加密
		return s;

	}

	@RequestMapping("/downLoadFile")
	public String downloadFile() {
		try {
			// 构造URL
			URL url = new URL("");
			URLConnection con = url.openConnection();
			// 设置请求超时为5s
			con.setConnectTimeout(5 * 1000);
			// 输入流
			InputStream is = con.getInputStream();
			
			// 1K的数据缓冲
			byte[] bs = new byte[1024];
			// 读取到的数据长度
			int len;
			// 输出的文件流
			File sf = new File("D:\\上传");
			if (!sf.exists()) {
				sf.mkdirs();
			}
			OutputStream os = new FileOutputStream(sf.getPath() + "\\" + "1111");
			// 开始读取
			while ((len = is.read(bs)) != -1) {
				os.write(bs, 0, len);
			}
			// 完毕，关闭所有链接
			os.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
