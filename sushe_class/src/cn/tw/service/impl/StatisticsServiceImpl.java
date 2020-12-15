package cn.tw.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.tw.dao.StatisticsDao;
import cn.tw.service.StatisticsService;

@Repository
public class StatisticsServiceImpl implements StatisticsService {

	@Resource
	StatisticsDao statisticsDao;

	@Override
	public void updateStuBrByCla(Map map) {
		statisticsDao.updateStuBrByCla(map);
	}

}
