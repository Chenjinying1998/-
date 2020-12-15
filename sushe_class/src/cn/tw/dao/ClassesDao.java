package cn.tw.dao;

import cn.tw.domain.Classes;
import cn.tw.pagination.Page;

public interface ClassesDao extends BaseDao<Classes>{
	public String findResultSize(Page page);
}
