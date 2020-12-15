package cn.tw.dao.impl;

import org.springframework.stereotype.Repository;

import cn.tw.dao.CollegeDao;
import cn.tw.domain.College;
import cn.tw.pagination.Page;

@Repository
public class CollegeDaoImpl extends BaseDaoImpl<College> implements CollegeDao {

	public CollegeDaoImpl() {
		super.setNs("cn.tw.mapper.CollegeMapper");
	}

	@Override
	public String findResultSize(Page page) {
		return super.getSqlSession().selectOne(super.getNs() + ".findResultSize",page);
	}

}
