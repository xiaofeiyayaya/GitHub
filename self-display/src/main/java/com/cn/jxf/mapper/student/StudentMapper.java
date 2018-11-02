package com.cn.jxf.mapper.student;

import java.util.List;

import com.cn.jxf.domain.student.Student;

public interface StudentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
    
    Student findByName(String name);
    
    List<Student> list();
}