package com.guimei.dao;

import org.springframework.orm.hibernate4.BaseDao;

import Utils.Page;

import com.guimei.domain.Category;

public interface CategoryDao extends BaseDao<Category> {
	//��̨�����ѯ����
		Page ManFindAll(Category category, int page, int rows, String order,
				String sort);
}
