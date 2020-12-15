package cn.tw.dao.impl;

import org.springframework.stereotype.Repository;

import cn.tw.dao.VisitorDao;
import cn.tw.domain.Visitor;
import cn.tw.pagination.Page;

@Repository
public class VisitorDaoImpl extends BaseDaoImpl<Visitor> implements VisitorDao {

	public VisitorDaoImpl() {
		super.setNs("cn.tw.mapper.VisitorMapper");
	}

	@Override
	public String findResultSize(Page page) {
		return super.getSqlSession().selectOne(super.getNs() + ".findResultSize",page);
	}

	
}
