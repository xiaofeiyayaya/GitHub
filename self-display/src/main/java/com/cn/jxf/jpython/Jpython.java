package com.cn.jxf.jpython;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.python.core.Py;
import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyList;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

public class Jpython {

	public static void main(String[] args) throws Exception {
		// 需要在python文件 xx.py 中添加#coding:utf-8
		System.setProperty("python.console.encoding", "utf-8");
		//Properties props = new Properties();
		//props.put("python.home", "path to the Lib folder");
		//props.put("python.console.encoding", "UTF-8");
		//props.put("python.security.respectJavaAccessibility", "false");
		//props.put("python.import.site", "false");
		//Properties preprops = System.getProperties();
		//PythonInterpreter.initialize(preprops, props, new String[0]);
		// Jpython.zhixingPython();
		Jpython.diaoyongPython();
		//Jpython.diaoyongPython2();
		 //Jpython.diaoyongPython3();
		//Jpython.pyMySqlTest();
		//param("xaiofei",666,1,2,3,4,5,6,7);
	}

	// 1. 在Java类中直接执行Python语句
	public static void zhixingPython() {
		PythonInterpreter inrepreter = new PythonInterpreter();
		inrepreter.exec("classmates = ['Michael', 'Bob', 'Tracy'];");
		inrepreter.exec("print(classmates[0]);");
	}

	// 2. 在java中调用本机python脚本中的函数
	public static void diaoyongPython(Object... params) {
		PythonInterpreter inrepreter = new PythonInterpreter();
		InputStream filepy;
		try {
			filepy = new FileInputStream("C:\\Users\\Administrator\\Desktop\\my_utils.py");
			inrepreter.execfile(filepy);
			filepy.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 方法adder
		PyFunction func = (PyFunction) inrepreter.get("adder", PyFunction.class);
		
		int a = 2016;
		int b = 2;
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(a);
		paramList.add(b);
	    for(Object obj:params){
	      paramList.add(obj);
	    }
		// 传入参数
		PyObject pyObject = func.__call__(new PyList(paramList));
	    //PyObject pyObject = func.__call__(new PyInteger(a), new PyInteger(b));
		System.out.println(pyObject.toString());
		
	}

	// 3. 直接调用文件
	public static void diaoyongPython3() throws Exception {
		PythonInterpreter inrepreter = new PythonInterpreter();
		/*
		 * PySystemState sys = Py.getSystemState();
		 * sys.path.add("D:\\pyVirtual\\DjangoEnv\\Lib\\site-packages");
		 * inrepreter.exec("import pymysql"); inrepreter.exec("import enum");
		 */
		InputStream filepy;
		try {
			// filepy = new
			// FileInputStream("C:\\Users\\Administrator\\Desktop\\test.py");
			filepy = new FileInputStream("D:\\PythonWorkSpace\\djangoTest\\apps\\firstTest\\views2.py");
			inrepreter.execfile(filepy);
			filepy.close();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}

		PyFunction func = (PyFunction) inrepreter.get("list", PyFunction.class);
		PyObject pyResult = func.__call__();
		/*
		 * Iterable<PyObject> asIterable = pyResult.asIterable();
		 * Iterator<PyObject> iterator = asIterable.iterator();
		 * while(iterator.hasNext()){ PyObject obj = iterator.next();
		 * System.out.println(obj); }
		 */
		PyObject pyObject = func.__call__();
		// PyString pyString = Py.newStringUTF8(pyResult.toString());
		//System.out.println(pyString);
		
		  byte[] bt = pyResult.toString().getBytes("utf-8"); 
		  String ret = new String(bt, "utf-8"); 
		  System.out.println(ret);
		 

	}

	public static void diaoyongPython2() {
		PythonInterpreter interpreter = new PythonInterpreter();
		interpreter.execfile("C:\\Users\\Administrator\\Desktop\\t4.py");
		PyFunction pyFunction = interpreter.get("jTest",PyFunction.class);
	    PyObject pyObject = pyFunction.__call__();
	    String asString = pyObject.asString();
	    /*try {
			asString = new String(asString.getBytes("iso8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}*/
	    System.out.println(asString);
		interpreter.cleanup();
	    interpreter.close();
	}

	// java直接调用脚本用本地环境的python解释器运行py文件
	public static void pyMySqlTest() throws Exception {
		String language = "python";
		String command = "D:\\PythonWorkSpace\\djangoTest\\apps\\firstTest\\views2.py";
		String[] cmdArr = new String[] { language, command };
		Process process = Runtime.getRuntime().exec(cmdArr);
		InputStream is = process.getInputStream();
		int i = 0;
		byte[] b = new byte[1024];
		while ((i = is.read(b)) != -1) {
			System.out.println(new String(b, "GBK"));
		}

	}
	
	public static void param(Object ...params){
		
		for (Object object : params) {
			System.err.println(object);
		}
	}

}
