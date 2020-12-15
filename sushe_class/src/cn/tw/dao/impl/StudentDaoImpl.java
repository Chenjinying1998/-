package cn.tw.dao.impl;

import org.springframework.stereotype.Repository;

import cn.tw.dao.StudentDao;
import cn.tw.domain.Student;
import cn.tw.pagination.Page;

@Repository
public class StudentDaoImpl extends BaseDaoImpl<Student> implements StudentDao {

	public StudentDaoImpl() {
		super.setNs("cn.tw.mapper.StudentMapper");
	}

	@Override
	public String findResultSize(Page page) {
		return super.getSqlSession().selectOne(super.getNs() + ".findResultSize",page);
	}

	
}
