package com.cn.jxf.test.dom4j;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.cn.jxf.domain.trade.TradeInfo;
import com.cn.jxf.domain.trade.TradeRelation;
import com.cn.jxf.domain.trade.TraderInfo;

public class Dom4jDemo {

	public static void main(String[] args) {
		//readXml1();
		//readXml2();
		readXml3();
	}
	
	/**
	 * 交易成员基本信息 
	 */
	public static void readXml1() {
		List<TradeInfo> tradeInfoList = new ArrayList<>();
		SAXReader reader = new SAXReader();
		Document doc = null;
		try {
			doc = reader.read(new File(
					"C:\\Users\\Administrator\\Desktop\\项目资料\\CSTP\\cstp本币\\20181123\\盘前\\本币交易成员信息_银行间\\交易成员基本信息_银行间_20181123 - 副本.xml"));
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Element root = doc.getRootElement();
		Iterator<Element> it = root.elementIterator();
		
		while (it.hasNext()) {
			TradeInfo tradeInfo = new TradeInfo();
			Element e = it.next();// 获取子元素
			// 获取属性值： 通过元素对象获取元素属性值
			String PartyInfoType = e.attributeValue("PartyInfoType"); //PartyInfoType  3 交易成员基本信息 
			if(!"3".equals(PartyInfoType)){
				continue;
			}
			
			// 创建迭代器对象判断该父元素是否还有子元素，有的话，就获取子元素的名称和文本内容
			Iterator<Element> it2 = e.elementIterator();
			while (it2.hasNext()) {
				Element e2 = (Element) it2.next();
				// 递归调用自身方法判断该子元素是否还存在子元素，以此类推并获取信息
				//String NoPartyList = e2.attributeValue("NoPartyList");
				String ID = e2.attributeValue("ID"); // ID 机构代码 
				tradeInfo.setWhPartyId(ID);

				// 创建迭代器对象判断该父元素是否还有子元素，有的话，就获取子元素的名称和文本内容
				Iterator<Element> it3 = e2.elementIterator();
				while (it3.hasNext()) {
					Element e3 = (Element) it3.next();
					//Typ  124 交易成员全称   125 中文简称   5 英文全称  102 英文简称  2 交易成员ID
					String Typ = e3.attributeValue("Typ");
					String ID2 = e3.attributeValue("ID"); // 获取属性id的值
					if("124".equals(Typ)){
						tradeInfo.setMemberLongname(ID2);
					}
					if("125".equals(Typ)){
						tradeInfo.setMemberName(ID2);
					}
					if("5".equals(Typ)){
						tradeInfo.setMemberLongnameEn(ID2);
					}
					if("102".equals(Typ)){
						tradeInfo.setMemberNameEn(ID2);
					}
					if("2".equals(Typ)){
						tradeInfo.setMemberId(ID2);
					}
				}
			}
			tradeInfoList.add(tradeInfo);
		}
		System.out.println(tradeInfoList);
		
	}
	
	

	/**
	 * 交易成员通讯录_银行间 
	 */
	public static void readXml2() {
		List<TraderInfo> traderInfoList = new ArrayList<>();
		SAXReader reader = new SAXReader();
		Document doc = null;
		try {
			doc = reader.read(new File(
					"C:\\Users\\Administrator\\Desktop\\项目资料\\CSTP\\cstp本币\\20181123\\盘前\\本币交易成员信息_银行间\\交易成员通讯录_银行间_20181123 - 副本.xml"));
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Element root = doc.getRootElement();
		
		Iterator<Element> it = root.elementIterator();
		while (it.hasNext()) {
			TraderInfo traderInfo = new TraderInfo();
			Element e = it.next();// 获取子元素
			// 获取属性值： 通过元素对象获取元素属性值
			String PartyInfoType = e.attributeValue("PartyInfoType"); //PartyInfoType  1 交易成员通讯录_银行间
			if(!"1".equals(PartyInfoType)){
				continue;
			}

			// 创建迭代器对象判断该父元素是否还有子元素，有的话，就获取子元素的名称和文本内容
			Iterator<Element> it2 = e.elementIterator();
			while (it2.hasNext()) {
				Element e2 = (Element) it2.next();
				// 递归调用自身方法判断该子元素是否还存在子元素，以此类推并获取信息  
				String NoPartyList = e2.attributeValue("NoPartyList");
				// 获取属性id的值    
				String ID = e2.attributeValue("ID"); // ID 交易成员内部ID
				traderInfo.setMemberId(ID);

				// 创建迭代器对象判断该父元素是否还有子元素，有的话，就获取子元素的名称和文本内容
				Iterator<Element> it3 = e2.elementIterator();
				while (it3.hasNext()) {
					Element e3 = (Element) it3.next();
					//交易成员通讯录_银行间    Typ  124 交易成员   2 交易员内部ID  126 交易员姓名  
					String Typ = e3.attributeValue("Typ"); 
					String ID2 = e3.attributeValue("ID"); // 获取属性id的值
					if("124".equals(Typ)){
						traderInfo.setMemberLongname(ID2);
					}
					if("2".equals(Typ)){
						traderInfo.setTraderId(ID2);
					}
					if("126".equals(Typ)){
						traderInfo.setTraderName(ID2);
					}
					//交易成员通讯录_银行间   ContactInfoIDType  6 联系电话  8 传真  10 EMail 11 MSN  0 通讯地址  5 邮政编码
					String ContactInfoIDType = e3.attributeValue("ContactInfoIDType");
					String ContactInfoID = e3.attributeValue("ContactInfoID");
					if("6".equals(ContactInfoIDType)){
						traderInfo.setTel(ContactInfoID);
					}
					if("8".equals(ContactInfoIDType)){
						traderInfo.setFax(ContactInfoID);
					}
					if("10".equals(ContactInfoIDType)){
						traderInfo.setEmail(ContactInfoID);
					}
					if("11".equals(ContactInfoIDType)){
						traderInfo.setMsn(ContactInfoID);
					}
					if("0".equals(ContactInfoIDType)){
						traderInfo.setContactAddress(ContactInfoID);
					}
					if("5".equals(ContactInfoIDType)){
						traderInfo.setPostcode(ContactInfoID);
					}
				}
			}
			traderInfoList.add(traderInfo);
		}
		System.out.println(traderInfoList);
	}
	
	/**
	 * 机构主从关系信息
	 */
	public static void readXml3() {
		List<TradeRelation> tradeRelationList = new ArrayList<>();
		SAXReader reader = new SAXReader();
		Document doc = null;
		try {
			doc = reader.read(new File(
					"C:\\Users\\Administrator\\Desktop\\项目资料\\CSTP\\cstp本币\\20181123\\盘前\\机构主从关系信息\\机构主从关系信息_20181123 - 副本.xml"));
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Element root = doc.getRootElement();
		Iterator<Element> it = root.elementIterator();
		while (it.hasNext()) {
			TradeRelation tradeRelation = new TradeRelation();
			Element e = it.next();// 获取子元素
			// 获取属性值： 通过元素对象获取元素属性值
			String PartyInfoType = e.attributeValue("PartyInfoType"); //PartyInfoType  K 机构主从关系信息
			if(!"K".equals(PartyInfoType)){
				continue;
			}

			// 创建迭代器对象判断该父元素是否还有子元素，有的话，就获取子元素的名称和文本内容
			Iterator<Element> it2 = e.elementIterator();
			while (it2.hasNext()) {
				Element e2 = (Element) it2.next();
				// 递归调用自身方法判断该子元素是否还存在子元素，以此类推并获取信息
				String NoPartyList = e2.attributeValue("NoPartyList");
				String ID = e2.attributeValue("ID"); // ID  R=1  主机构6位码 , R=146  从机构6位码
				String R = e2.attributeValue("R"); // R  1 主机构   146 从机构
				if("1".equals(R)){
					tradeRelation.setMemberId(ID);
				}
				if("146".equals(R)){
					tradeRelation.setSubMemberId(ID);
				}

				// 创建迭代器对象判断该父元素是否还有子元素，有的话，就获取子元素的名称和文本内容
				Iterator<Element> it3 = e2.elementIterator();
				while (it3.hasNext()) {
					Element e3 = (Element) it3.next();
					// R=1 Typ  124 主机构中文全称  244 主关系类型名称 , R=146 Typ 124 从机构中文全称  245 从关系类型名称
					String Typ = e3.attributeValue("Typ");
					String ID2 = e3.attributeValue("ID"); // 获取属性id的值
					if("1".equals(R)){
						if("124".equals(Typ)){
							tradeRelation.setMemberLongname(ID2);
						}
						if("244".equals(Typ)){
							tradeRelation.setRelaType(ID2);
						}
					}
					if("146".equals(R)){
						if("124".equals(Typ)){
							tradeRelation.setSubMemberLongname(ID2);
						}
						if("245".equals(Typ)){
							tradeRelation.setSubRelaType(ID2);
						}
					}
				}
			}
			tradeRelationList.add(tradeRelation);
		}
		System.out.println(tradeRelationList);
	}

}
