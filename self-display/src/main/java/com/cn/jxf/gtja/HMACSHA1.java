package com.cn.jxf.gtja;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;

public class HMACSHA1 {
	
	   private static final String MAC_NAME = "HmacSHA1";  
	    private static final String ENCODING = "UTF-8";  
	    /**
	     * base64加密
	     * @param bstr
	     * @return
	     */
	    private static String encode(byte[] bstr){
	        return new BASE64Encoder().encode(bstr);
	    }
	/**
	 * 加密
	 * @param key
	 * @param text
	 * @return
	 * @throws Exception
	 */
	    /* Signature algorithm using HMAC-SHA1 */
	    public static String hmacSHA1(String key, String text) throws Exception
	    {
	        Mac mac = Mac.getInstance(MAC_NAME);
	        mac.init(new SecretKeySpec(key.getBytes(ENCODING), MAC_NAME));
	        return bytes2Hex(mac.doFinal(text.getBytes(ENCODING)));
	    }
		public static String bytes2Hex(byte[] bytes) {
			String hex = "";
			for (int n = 0; n < bytes.length; n++) {
				String byteStr = (Integer.toHexString(bytes[n] & 0XFF));
				if (byteStr.length() == 1) {
					hex = hex + "0" + byteStr;
				} else {
					hex = hex + byteStr;
				}
			}
			return hex.toLowerCase();
		}
	    /**
	     * 排序
	     * @param params
	     * @return
	     */
	    public static String formatUrlMap(Map<String,String> params){
	    	 List<Entry<String, String>> infoIds = new ArrayList<Entry<String, String>>(params.entrySet());
	    	 Collections.sort(infoIds,new Comparator<Entry<String, String>>() {
	    		 @Override
	    		public int compare(Entry<String, String> o1, Entry<String, String> o2) {
	    			// TODO Auto-generated method stub
	    			return o1.getKey().compareTo(o2.getKey());
	    		}
			});
	    	 StringBuilder buf = new StringBuilder(); 
	    	 for (Entry<String, String> item : infoIds){
	    		 String key=item.getKey();
	    		 String value=item.getValue();
	    		 buf.append(key).append(value);
	    	 }
	    	 buf.append(params.get("userid"));
	    	 return buf.toString();

}
}
