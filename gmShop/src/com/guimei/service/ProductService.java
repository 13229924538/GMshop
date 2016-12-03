package com.guimei.service;

import java.util.List;

import Utils.Page;

import com.guimei.domain.Product;

public interface ProductService {
	// ��ѯ������Ʒ:
	List<Product> findHot();
	//����������Ʒ
	Page findAll(int page, int rows, String order, String sort,
			Product product, Integer cid, Integer csid);
	//ͨ��pid������Ʒ
	Product findById(Integer pid);
	//������Ʒ
	void save(Product product);
	//ɾ��һЩ��Ʒ
	int delete(String ids);
	//�༭��Ʒ
	void update(Product product);
	
	//---------------------ying-------------------
	//ͨ��Csid������Ʒ
	Page findByCsid(Integer page,int rows,Integer csid);
	//ͨ��cid������Ʒ
	Page findByCid(Integer page,int rows,Integer cid);

}
