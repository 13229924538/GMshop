package com.guimei.dao.imp;

import org.springframework.orm.hibernate4.BaseDaoImpl;

import Utils.Page;

import com.guimei.dao.CategoryDao;
import com.guimei.domain.Category;

public class CategoryDaoImp extends BaseDaoImpl<Category> implements CategoryDao{
	//��̨�����ѯ����
		public Page ManFindAll(Category category, int page, int rows, String order,
				String sort) {
			StringBuffer hql = new StringBuffer("from Category where 1=1");
			//�û�����
			if(category.getCname()!=null&&!category.getCname().equals("")){
				hql.append(" and cname like '%"+category.getCname()+"%'");
			}
			
			//����������
			if(sort!=null){
				hql.append(" ORDER BY "+sort+" "+order);
			}
			Page page2= this.getPageReady(page, hql.toString(),rows);
			return page2;
		}
}
