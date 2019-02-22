package com.cn.jxf.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.cn.jxf.Application;
import com.cn.jxf.util.ZipUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class ZipDemo {
	
    @Test
    public void demo1(){
    	
    	String str = "jiaxiaofei";
    	for(int i = 0;i<str.length();i++){
    		System.out.println(str.charAt(i));
    	}
    	System.out.println("------------");
    	int a[] = {1,2,3,};
    	String b[] = {"a","b","c"};
    	for (int i : a) {
			System.out.println(i);
		}
    	System.out.println("------------");
    	for(int j = 0;j<a.length;j++){
    		System.out.println(a[j]);
    	}
    	System.out.println("------------");
    	List<Integer> list = Arrays.stream( a ).boxed().collect(Collectors.toList());
    	for(int k = 0;k<list.size();k++){
    		System.out.println(list.get(k));
    	}
    	System.out.println("------------");
    	List<String> list2 = Arrays.asList(b);
    	for(int g = 0;g<list2.size();g++){
    		System.out.println(list2.get(g));
    	}
    	System.out.println("------------");
    }
    
    @Test
    public void zipFile(){
    	String zip = "D:/text/text.zip";
    	File file = new File("D:/text/text.txt");
    	List<File> fileList = new ArrayList<File>() ;
    	fileList.add(file);
    	ZipUtil.zipFile(zip, fileList);
    }
    
    @Test
    public void upZipFile(){
    	String zip = "D:\\text";
    	String zipPath = "C:\\Users\\Administrator\\Desktop\\项目资料\\CSTP\\cstp本币\\20181123\\盘前\\机构主从关系信息\\机构主从关系信息_20181123.xml.zip";
    	//File file = new File("D:/text/text.zip");
    	List<File> upzipFile = ZipUtil.upzip(zipPath,zip);
    	for (File file2 : upzipFile) {
    		 Reader reader = null;  
    	        try {  
    	            // 设置编码，可以读取汉字  
    	            reader = new InputStreamReader(new FileInputStream(file2), "GBK");  
    	            int read;  
    	            byte[] data = new byte[(int) file2.length()];  
    	            int len = 0;  
    	            while ((read = reader.read()) != -1) {  
    	                data[len++] = (byte) read;  
    	                // 对于windows,\r\n两个字符表示一个换行  
    	                // 分开显示时换行两次  
    	                if ((char) read != '\r') {  
    	                    System.out.println((char) read);  
    	                }  
    	            }  
    	        } catch (Exception e) {  
    	            e.printStackTrace();  
    	        } finally {  
    	            if (null != reader) {  
    	                try {  
    	                    reader.close();  
    	                } catch (IOException e) {  
    	                    e.printStackTrace();  
    	                }  
    	            }  
    	        }  
		}
    }
}
