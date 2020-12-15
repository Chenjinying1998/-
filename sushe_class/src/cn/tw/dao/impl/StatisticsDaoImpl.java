package cn.tw.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.tw.dao.StatisticsDao;
import cn.tw.domain.Statistics;

@Repository
public class StatisticsDaoImpl extends BaseDaoImpl<Statistics> implements StatisticsDao {

	public StatisticsDaoImpl() {
		super.setNs("cn.tw.mapper.StatisMapper");
	}

	@Override
	public void updateStuBrByCla(Map map) {
		super.getSqlSession().update(super.getNs() + ".updateStuBrByCla");		
	}


}
