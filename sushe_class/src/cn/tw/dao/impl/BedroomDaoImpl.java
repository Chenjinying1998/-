package cn.tw.dao.impl;

import org.springframework.stereotype.Repository;

import cn.tw.dao.BedroomDao;
import cn.tw.domain.Bedroom;
import cn.tw.pagination.Page;

@Repository
public class BedroomDaoImpl extends BaseDaoImpl<Bedroom> implements BedroomDao {

	public BedroomDaoImpl() {
		super.setNs("cn.tw.mapper.BedroomMapper");
	}

	@Override
	public String findResultSize(Page page) {
		return super.getSqlSession().selectOne(super.getNs() + ".findResultSize",page);
	}

}
