package cn.tw.dao;

import cn.tw.domain.Visitor;
import cn.tw.pagination.Page;

public interface VisitorDao extends BaseDao<Visitor> {
	public String findResultSize(Page page);
}
