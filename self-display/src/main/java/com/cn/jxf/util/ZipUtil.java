package com.cn.jxf.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

/**
 * 压缩或解压zip：
 * 由于直接使用java.util.zip工具包下的类，会出现中文乱码问题，所以使用ant.jar中的org.apache.tools.zip下的工具类
 */
public class ZipUtil {

	private static byte[] _byte = new byte[1024];

	/**
	 ** 压缩文件或路径
	 ** 
	 * @param zip
	 *            压缩的目的地址
	 ** @param srcFiles
	 *            压缩的源文件
	 */
	public static void zipFile(String zip, List<File> srcFiles) {
		try {
			if (zip.endsWith(".zip") || zip.endsWith(".ZIP")) {
				ZipOutputStream _zipOut = new ZipOutputStream(new FileOutputStream(new File(zip)));
				_zipOut.setEncoding("GBK");
				for (File _f : srcFiles) {
					handlerFile(zip, _zipOut, _f, "");
				}
				_zipOut.close();
			} else {
				System.out.println("target file[" + zip + "] is not .zip type file");
			}
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
	}

	/**
	 * 
	 * @param zip
	 *            压缩的目的地址
	 * @param zipOut
	 * @param srcFile
	 *            被压缩的文件信息
	 * @param path
	 *            在zip中的相对路径
	 * @throws IOException
	 */
	private static void handlerFile(String zip, ZipOutputStream zipOut, File srcFile, String path) throws IOException {
		System.out.println(" begin to compression file[" + srcFile.getName() + "]");
		if (!"".equals(path) && !path.endsWith(File.separator)) {
			path += File.separator;
		}
		if (!srcFile.getPath().equals(zip)) {
			if (srcFile.isDirectory()) {
				File[] _files = srcFile.listFiles();
				if (_files.length == 0) {
					zipOut.putNextEntry(new ZipEntry(path + srcFile.getName() + File.separator));
					zipOut.closeEntry();
				} else {
					for (File _f : _files) {
						handlerFile(zip, zipOut, _f, path + srcFile.getName());
					}
				}
			} else {
				InputStream _in = new FileInputStream(srcFile);
				zipOut.putNextEntry(new ZipEntry(path + srcFile.getName()));
				int len = 0;
				while ((len = _in.read(_byte)) > 0) {
					zipOut.write(_byte, 0, len);
				}
				_in.close();
				zipOut.closeEntry();
			}
		}
	}

	/**
	 * 解压缩ZIP文件，将ZIP文件里的内容解压到targetDIR目录下
	 * 
	 * @param zipPath
	 *            待解压缩的ZIP文件路径   如 D:/text/text.zip
	 * @param descDir
	 *            解压缩的目标地址，如：D:\\测试 或 /mnt/d/测试       D:/text
	 */
	public static List<File> upzip(String zipPath, String descDir) {
		return upzipFile(new File(zipPath), descDir);
	}

	/**
	 * 对.zip文件进行解压缩
	 * 
	 * @param zipFile
	 *            解压缩文件
	 * @param descDir
	 *            解压缩的目标地址，如：D:\\测试 或 /mnt/d/测试       D:/text
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static List<File> upzipFile(File zipFile, String descDir) {
		List<File> _list = new ArrayList<File>();
		try {
			ZipFile _zipFile = new ZipFile(zipFile, "GBK");
			for (Enumeration entries = _zipFile.getEntries(); entries.hasMoreElements();) {
				ZipEntry entry = (ZipEntry) entries.nextElement();
				File _file = new File(descDir + File.separator + entry.getName());
				if (entry.isDirectory()) {
					_file.mkdirs();
				} else {
					File _parent = _file.getParentFile();
					if (!_parent.exists()) {
						_parent.mkdirs();
					}
					InputStream _in = _zipFile.getInputStream(entry);
					OutputStream _out = new FileOutputStream(_file);
					int len = 0;
					while ((len = _in.read(_byte)) > 0) {
						_out.write(_byte, 0, len);
					}
					_in.close();
					_out.flush();
					_out.close();
					_list.add(_file);
				}
			}
		} catch (IOException e) {
		}
		return _list;
	}

}
