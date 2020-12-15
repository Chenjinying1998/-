package cn.tw.dao.impl;

import org.springframework.stereotype.Repository;

import cn.tw.dao.ApartmentDao;
import cn.tw.domain.Apartment;
import cn.tw.pagination.Page;

@Repository
public class ApartmentDaoImpl extends BaseDaoImpl<Apartment> implements ApartmentDao {

	public ApartmentDaoImpl() {
		super.setNs("cn.tw.mapper.ApartmentMapper");
	}

	@Override
	public String findResultSize(Page page) {
		return super.getSqlSession().selectOne(super.getNs() + ".findResultSize",page);
	}

}
