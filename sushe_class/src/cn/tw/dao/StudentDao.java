package cn.tw.dao;

import cn.tw.domain.Student;
import cn.tw.pagination.Page;

public interface StudentDao extends BaseDao<Student> {
	public String findResultSize(Page page);
}
