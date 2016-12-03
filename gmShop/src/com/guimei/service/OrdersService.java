package com.guimei.service;

import java.io.Serializable;
import java.util.List;

import Utils.Page;

import com.guimei.domain.Orders;

public interface OrdersService {
	//�������ж���
	Page findAll(int page, int rows, String order, String sort, Orders orders);
	//�༭����
	void update(Orders orders);
	//����id����
	Orders findById(Integer oid);
	public void save(Orders orders);
	public Orders findById(Serializable id);

	public List<Orders> findAll();
	public Object uniqueResult();
	public List findPage(final int maxResult,final int firstResult,final Object... paras);
	public void delete(Orders instance);
}
