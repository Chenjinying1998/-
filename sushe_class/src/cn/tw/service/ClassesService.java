package cn.tw.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.tw.domain.Classes;
import cn.tw.pagination.Page;

public interface ClassesService {

	public List<Classes> list(Map paraMap);

	public List<Classes> findPage(Page page); // 分页查询

	public List<Classes> find(Map paraMap); // 带条件查询，条件可以为null，既没有条件；返回list对象集合

	public Classes get(Serializable id); // 只查询一个，常用于修�?

	public void insert(Classes factory); // 插入，用实体作为参数

	public void update(Classes factory); // 修改，用实体作为参数

	public void deleteById(Serializable id); // 按id删除，删除一条；支持整数型和字符串类型ID

	public void delete(Serializable[] ids); // 批量删除；支持整数型和字符串类型ID

	public String findResultSize(Page page); // 分页查询总记录数
}
