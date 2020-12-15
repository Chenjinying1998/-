package cn.tw.dao.impl;

import org.springframework.stereotype.Repository;

import cn.tw.dao.ClassesDao;
import cn.tw.domain.Classes;
import cn.tw.pagination.Page;

@Repository
public class ClassesDaoImpl extends BaseDaoImpl<Classes> implements ClassesDao {

	public ClassesDaoImpl() {
		super.setNs("cn.tw.mapper.ClassesMapper");
	}

	@Override
	public String findResultSize(Page page) {
		return super.getSqlSession().selectOne(super.getNs() + ".findResultSize",page);
	}

}
