package cn.tw.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.tw.pagination.Page;

public interface BaseDao<T> {
	public List<T> findPage(Page page); // 鍒嗛〉鏌ヨ

	public List<T> find(Map paraMap); // 甯︽潯浠舵煡璇紝鏉′欢鍙互涓簄ull锛屾棦娌℃湁鏉′欢锛涜繑鍥瀕ist瀵硅薄闆嗗悎

	public T get(Serializable id); // 鍙煡璇竴涓紝甯哥敤浜庝慨鏀�

	public void insert(T entity); // 鎻掑叆锛岀敤瀹炰綋浣滀负鍙傛暟

	public void update(T entity); // 淇敼锛岀敤瀹炰綋浣滀负鍙傛暟

	public void deleteById(Serializable id); // 鎸塱d鍒犻櫎锛屽垹闄や竴鏉★紱鏀寔鏁存暟鍨嬪拰瀛楃涓茬被鍨婭D

	public void delete(Serializable[] ids); // 鎵归噺鍒犻櫎锛涙敮鎸佹暣鏁板瀷鍜屽瓧绗︿覆绫诲瀷ID
}
