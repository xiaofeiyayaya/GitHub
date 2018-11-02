package com.cn.jxf.test.weixin.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.cn.jxf.test.weixin.domain.AccessToken;
import com.cn.jxf.test.weixin.menu.Menu;
import com.cn.jxf.test.weixin.web.AccessTokenController;
import com.cn.jxf.util.HttpClientUtil;

public class Upload {
	
	@Autowired 
	HttpServletRequest request;
	
    public static String uploadFile(String uploadUrl, List<File> files, Map<String,String> params) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String result = "";
        try{
            HttpPost httpPost = new HttpPost(uploadUrl);
            httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2723.3 Safari/537.36");
            httpPost.setHeader("Accept-Language", "zh-cn,zh;q=0.5");
            httpPost.setHeader("Accept-Charset", "UTF-8,utf-8;q=0.7,;q=0.7");
            httpPost.setHeader("Connection", "keep-alive");
            
			// 设置代理服务器
			HttpHost proxy = new HttpHost("172.16.0.1", 3128);
			RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
			// 添加代理服务器
			httpPost.setConfig(config);
            
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            // 这里是解决中文名乱码的关键，很多地方说设置HttpMultipartMode.BROWSER_COMPATIBLE，到微信这里就不行了
            multipartEntityBuilder.setMode(HttpMultipartMode.RFC6532);
            // 这里是个坑，乱码问题首先想到的是设置编码，可是设置这个之后，微信就报41005错误了。大坑大坑。。具体原因我也不清楚
//          multipartEntityBuilder.setCharset(Consts.UTF_8);
            if(params != null && params.size() > 0){
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    multipartEntityBuilder.addTextBody(entry.getKey(), entry.getValue().toString(), ContentType.create("text/plain", Consts.UTF_8));
                }
            }
            if (files != null && files.size() > 0){
                for (File file : files) {
                    multipartEntityBuilder.addBinaryBody("media", file);
                }
            }
            httpPost.setEntity(multipartEntityBuilder.build());

            response = httpclient.execute(httpPost);
            entity = response.getEntity();
            if (response.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(response.getEntity());
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                if(entity != null){
                    EntityUtils.consume(entity);
                }
                if(response != null){
                    response.close();
                }
                if(httpclient != null){
                    httpclient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    
    public static void main(String[] args) throws ClientProtocolException, IOException {
    	JSONObject access_Token = AccessTokenController.getAccess_Token();
        String access_token = access_Token.getString("access_token");
       /* List<File> files = new ArrayList<File>();
        //新增临时图片
        files.add(new File("D:\\Workspace\\self-display\\src\\main\\resources\\static\\images\\login.jpg"));
        String result = uploadFile("https://api.weixin.qq.com/cgi-bin/media/upload?access_token="+access_token+"&type=image", files, null);
        
        
        //{"type":"file","media_id":"1cn4gBj-jg6phmUZSqqRjbOYye56CdgPaTmgunfJ7yvhW44yaD-Py0mbA9lyZ6Ckhjli9JKIKibFv8kq385eWhg","created_at":1468382218}
        System.out.println(result);*/
        Menu menu = MenuUtil.initMenu();
        String string = JSON.toJSONString(menu);
        System.out.println(string);
        String user_define_menu = "{\"button\":[{\"type\":\"click\",\"name\":\"项目管理\",\"key\":\"20_PROMANAGE\"},{\"type\":\"click\",\"name\":\"机构运作\",\"key\":\"30_ORGANIZATION\"},{\"name\":\"日常工作\",\"sub_button\":[{\"type\":\"click\",\"name\":\"待办工单\",\"key\":\"01_WAITING\"},{\"type\":\"click\",\"name\":\"已办工单\",\"key\":\"02_FINISH\"},{\"type\":\"click\",\"name\":\"我的工单\",\"key\":\"03_MYJOB\"},{\"type\":\"click\",\"name\":\"公告消息箱\",\"key\":\"04_MESSAGEBOX\"},{\"type\":\"click\",\"name\":\"签到\",\"key\":\"05_SIGN\"}]}]}";
      //此处改为自己想要的结构体，替换即可
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+access_token;
        //Map<String, String> paramsHashMap = new HashMap<>();
        //paramsHashMap.put("data", user_define_menu);
        //JSONObject post = HttpClientUtil.post(paramsHashMap, url, "UTF-8");
        //System.out.println(post.toString());
        JSONObject dopostStr = HttpClientUtil.dopostStr(url,string);
        System.out.println(dopostStr.toString());
        
    }
   
}
