package com.guimei.service;

import java.util.List;

import Utils.Page;

import com.guimei.domain.Category;
import com.guimei.domain.Categorysecond;

public interface CategorysecondService {

	Page findAll(Categorysecond categorysecond, int page, int rows,
			String order, String sort, Integer cid);
	//���
	void save(Categorysecond categorysecond);
	//�����������Ƿ��������Ķ����˵�
	Categorysecond findByName(String csname);
	//���¶����˵�
	void update(Categorysecond categorysecond);
	int delete(String ids);
	//ͨ��ajax�������ж����˵�������
	List<Categorysecond> findAllName(Integer cid);
	//�����������Ƿ��������Ķ����˵�
		Categorysecond findCate(Categorysecond categorysecond, String cname);
}
