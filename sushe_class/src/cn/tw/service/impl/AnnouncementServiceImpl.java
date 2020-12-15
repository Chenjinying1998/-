package cn.tw.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import cn.tw.dao.AnnouncementDao;
import cn.tw.domain.Announcement;
import cn.tw.pagination.Page;
import cn.tw.service.AnnouncementService;

@Service
@Repository
public class AnnouncementServiceImpl implements AnnouncementService {

	@Autowired
	AnnouncementDao announcementDao;

	@Override
	public List<Announcement> list(Map paraMap) {
		return announcementDao.find(paraMap);
	}

	@Override
	public List<Announcement> findPage(Page page) {
		return announcementDao.findPage(page);
	}

	@Override
	public List<Announcement> find(Map paraMap) {
		return announcementDao.find(paraMap);
	}

	@Override
	public Announcement get(Serializable id) {
		return announcementDao.get(id);
	}

	@Override
	public void insert(Announcement announcement) {
		announcementDao.insert(announcement);
	}

	@Override
	public void update(Announcement announcement) {
		announcementDao.update(announcement);
	}

	@Override
	public void deleteById(Serializable id) {
		announcementDao.deleteById(id);
	}

	@Override
	public void delete(Serializable[] ids) {
		announcementDao.delete(ids);
	}

	@Override
	public String findResultSize(Page page) {
		return announcementDao.findResultSize(page);
	}

}
