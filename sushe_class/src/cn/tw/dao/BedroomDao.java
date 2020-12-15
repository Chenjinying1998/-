package cn.tw.dao;

import cn.tw.domain.Bedroom;
import cn.tw.pagination.Page;

public interface BedroomDao extends BaseDao<Bedroom>{
	public String findResultSize(Page page);
}
