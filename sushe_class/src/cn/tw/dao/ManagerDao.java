package cn.tw.dao;

import cn.tw.domain.Manager;
import cn.tw.pagination.Page;

public interface ManagerDao extends BaseDao<Manager> {
	public String findResultSize(Page page);
}
