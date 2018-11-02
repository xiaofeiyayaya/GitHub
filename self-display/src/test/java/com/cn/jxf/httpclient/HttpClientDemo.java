package com.cn.jxf.httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParamConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cn.jxf.Application;
import com.cn.jxf.util.HttpClientUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class HttpClientDemo {


	public JSONObject postDemo(Map<String, String> paramsHashMap, String url, String encoding) {
		 //创建httpClient连接
        CloseableHttpClient httpClient = HttpClients.createDefault();

        JSONObject result = null;
        List<NameValuePair> nameValuePairArrayList = new ArrayList<NameValuePair>();
        // 将传过来的参数添加到List<NameValuePair>中
        if (paramsHashMap != null && !paramsHashMap.isEmpty()) {
            //遍历map
            for (Map.Entry<String, String> entry : paramsHashMap.entrySet()) {
                nameValuePairArrayList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        UrlEncodedFormEntity entity = null;
        try {
            // 利用List<NameValuePair>生成Post请求的实体数据
            // UrlEncodedFormEntity 把输入数据编码成合适的内容
            entity = new UrlEncodedFormEntity(nameValuePairArrayList, encoding);
            HttpPost httpPost = new HttpPost(url);
            // 为HttpPost设置实体数据
            httpPost.setEntity(entity);
            // HttpClient 发送Post请求
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                // CloseableHttpResponse
                HttpEntity httpEntity = httpResponse.getEntity();
                if (httpEntity != null) {
                    BufferedReader reader = null;
                    try {
                        reader = new BufferedReader(new InputStreamReader(httpEntity.getContent(), "UTF-8"), 10 * 1024);
                        StringBuilder strBuilder = new StringBuilder();
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            strBuilder.append(line);
                        }
                        // 用fastjson的JSON将返回json字符串转为json对象
                        result = JSON.parseObject(strBuilder.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (reader != null) {
                            try {
                                //关闭流
                                reader.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
		
	}
	
	
	public JSONObject getDemo(String url){
		JSONObject resultJsonObject=null;
	       
        //创建httpClient连接
        CloseableHttpClient httpClient = HttpClients.createDefault();

        StringBuilder urlStringBuilder=new StringBuilder(url);
        StringBuilder entityStringBuilder=new StringBuilder();
        //利用URL生成一个HttpGet请求
        HttpGet httpGet=new HttpGet(urlStringBuilder.toString());
        // HttpClient 发送Post请求
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse=httpClient.execute(httpGet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //得到httpResponse的状态响应码
        if (httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_OK) {
            //得到httpResponse的实体数据
            HttpEntity httpEntity=httpResponse.getEntity();
            if (httpEntity!=null) {
                BufferedReader reader=null;
                try {
                    reader=new BufferedReader(new InputStreamReader(httpEntity.getContent(), "UTF-8"), 8*1024);
                    String line=null;
                    while ((line=reader.readLine())!=null) {
                        entityStringBuilder.append(line);
                    }
                    // 从HttpEntity中得到的json String数据转为json
                    String json=entityStringBuilder.toString();
                    resultJsonObject=JSON.parseObject(json);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            //关闭流
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return resultJsonObject;
	}
	
	@Test
	public void test(){
		JSONObject get = HttpClientUtil.get("http://localhost:8080/student/testList?id=1");
		Map<String,String> map = new HashMap<>();
		map.put("id", "1");
		JSONObject post = HttpClientUtil.post(map,"http://localhost:8080/student/testList","UTF-8");
		System.out.println(get);
		System.out.println(post);
	}
}
