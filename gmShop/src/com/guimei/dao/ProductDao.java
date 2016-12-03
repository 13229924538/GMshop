package com.guimei.dao;

import org.springframework.orm.hibernate4.BaseDao;

import Utils.Page;

import com.guimei.domain.Product;

public interface ProductDao extends BaseDao<Product> {
	//����������Ʒ
	Page findAllByPage(int page, int rows, String order, String sort,
			Product product, Integer cid, Integer csid);
	
	//-------------------ying------------------------
	//���ݶ����˵�������Ʒ����ҳ��ʾ
	Page findByCsidByPage(int page, int rows,Integer csid);
	//����һ���˵�������Ʒ����ҳ��ʾ
	Page findByCidByPage(int page, int rows,Integer cid);
}
