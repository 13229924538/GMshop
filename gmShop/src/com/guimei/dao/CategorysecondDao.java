package com.guimei.dao;

import org.springframework.orm.hibernate4.BaseDao;

import Utils.Page;

import com.guimei.domain.Categorysecond;


public interface CategorysecondDao extends BaseDao<Categorysecond>{
	//��ѯ����
	Page findByPage(Categorysecond categorysecond, int page, int rows,
			String order, String sort, Integer cid);

}
