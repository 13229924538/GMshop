package com.guimei.service;

import java.util.List;

import Utils.Page;

import com.guimei.domain.Category;

public interface CategoryService {
	// ��ѯ����һ�����༯��
	List<Category> findAll();
	//ajax�������е�һ���˵�������
	List findAllName();
	//�������ڵ�һ���˵�
	Category findById(Integer cid);
	//�������ֲ��Ҷ�Ӧ��һ���˵�
	Category findByName(String cname);
	//��̨�����ѯ����
		Page ManFindAll(Category category, int page, int rows, String order,
				String sort);
		//����һ���˵�
		void add(Category category);
		//�޸�һ���˵�
		void edit(Category category);
		//ɾ��һ���˵�
		int delete(String ids);
}
