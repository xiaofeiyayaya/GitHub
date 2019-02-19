/*package com.cn.jxf.service.student.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.jxf.domain.student.Student;
import com.cn.jxf.mapper.student.StudentMapper;
import com.cn.jxf.service.student.StudentCacheService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
@Service("studentCacheService")
public class StudentCacheServiceImpl implements StudentCacheService{
	
	@Resource
	private StudentMapper studentMapper;
	@Resource
	private Cache studentCache;
	
	@Override
	public Student findById(Integer id) {
		Element element = studentCache.get(id);
		if (element == null) {
			studentList();
			element = studentCache.get(id);
		}

		if (element == null) {
			return null;
		} else {
			Student user = (Student) element.getObjectValue();
			return user;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> studentList() {
		Element element2 = studentCache.get("");
		if (element2 == null) {
			List<Student> users = studentMapper.list();
			Element element = new Element("",users);
			studentCache.put(element);
		}
		Element element3 = studentCache.get("");
		List<Student> student = null;
		if(element3!=null){
			student = (List<Student>)element3.getObjectValue();
			if (student != null) {
				for (Student user : student) {
					Element userElement = new Element(user.getId(), user);
					studentCache.put(userElement);
				}
			}
		}
		return student;
	}

}
*/