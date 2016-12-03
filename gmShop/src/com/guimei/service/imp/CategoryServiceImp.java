package com.guimei.service.imp;

import java.util.List;

import Utils.Page;

import com.guimei.dao.CategoryDao;
import com.guimei.domain.Category;
import com.guimei.service.CategoryService;

public class CategoryServiceImp implements CategoryService {
//ע��dao
	private CategoryDao categoryDao;
	
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	// ��ѯ����һ�����༯��
	public List<Category> findAll() {
		
		return categoryDao.findAll();
	}

	//ajax�������е�һ���˵�������
	public List findAllName() {
		
		return categoryDao.findAll();
	}

	//�������ڵ�һ���˵�
	public Category findById(Integer cid) {
		
		return categoryDao.findById(cid);
	}

	@Override
	public Category findByName(String cname) {
		List<Category> list=categoryDao.findByProperty("cname", cname);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	//��̨�����ѯ����
		public Page ManFindAll(Category category, int page, int rows, String order,
				String sort) {
			
			return categoryDao.ManFindAll(category,page,rows,order,sort);
		}

		//����һ���˵�
		public void add(Category category) {
			categoryDao.save(category);
		}

		//�޸�һ���˵�
		public void edit(Category category) {
			categoryDao.update(category);;
			
		}

		//ɾ��һ���˵�
		public int delete(String ids) {
			int length=ids.split(",").length;
			String[] strIds = ids.split(",");
			String hql = "delete from Category where cid in (";
			for(int i=0;i<strIds.length;i++){
				hql += strIds[i]+",";
			}
			hql = hql.substring(0,hql.length()-1);
			hql += ")";
			System.out.println(hql);
			categoryDao.executeByHql(hql, null);
			return length;
		}

}
