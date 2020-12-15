package cn.tw.dao;

import cn.tw.domain.Apartment;
import cn.tw.pagination.Page;

public interface ApartmentDao extends BaseDao<Apartment>{
	public String findResultSize(Page page);
}
