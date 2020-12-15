package cn.tw.dao;

import cn.tw.domain.Announcement;
import cn.tw.pagination.Page;

public interface AnnouncementDao extends BaseDao<Announcement>{
	public String findResultSize(Page page);
}
