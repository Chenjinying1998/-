package cn.tw.dao;

import cn.tw.domain.College;
import cn.tw.pagination.Page;

public interface CollegeDao extends BaseDao<College>{
	public String findResultSize(Page page);
}
