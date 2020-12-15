package cn.tw.dao;

import java.util.Map;

import cn.tw.domain.Statistics;

public interface StatisticsDao extends BaseDao<Statistics> {

	public void updateStuBrByCla(Map map); 

}
