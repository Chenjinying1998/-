package cn.tw.dao.impl;

import org.springframework.stereotype.Repository;

import cn.tw.dao.ManagerDao;
import cn.tw.domain.Manager;
import cn.tw.pagination.Page;

@Repository
public class ManagerDaoImpl extends BaseDaoImpl<Manager> implements ManagerDao {

	public ManagerDaoImpl() {
		super.setNs("cn.tw.mapper.ManagerMapper");
	}

	@Override
	public String findResultSize(Page page) {
		return super.getSqlSession().selectOne(super.getNs() + ".findResultSize",page);
	}

	
}
