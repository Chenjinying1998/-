package cn.tw.dao.impl;

import org.springframework.stereotype.Repository;

import cn.tw.dao.AnnouncementDao;
import cn.tw.dao.StudentDao;
import cn.tw.domain.Announcement;
import cn.tw.domain.Student;
import cn.tw.pagination.Page;

@Repository
public class AnnouncementDaoImpl extends BaseDaoImpl<Announcement> implements AnnouncementDao {

	public AnnouncementDaoImpl() {
		super.setNs("cn.tw.mapper.AnnouncementMapper");
	}

	@Override
	public String findResultSize(Page page) {
		return super.getSqlSession().selectOne(super.getNs() + ".findResultSize",page);
	}

}
