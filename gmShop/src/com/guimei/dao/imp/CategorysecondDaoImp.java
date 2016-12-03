package com.guimei.dao.imp;

import org.springframework.orm.hibernate4.BaseDaoImpl;

import Utils.Page;

import com.guimei.dao.CategorysecondDao;
import com.guimei.domain.Categorysecond;

public class CategorysecondDaoImp extends BaseDaoImpl<Categorysecond> implements CategorysecondDao{
	//��ѯ����
	public Page findByPage(Categorysecond categorysecond, int page, int rows,
			String order, String sort,Integer cid) {

		StringBuffer hql = new StringBuffer("from Categorysecond c where 1=1");
		//�����˵�����
		if(categorysecond.getCsname()!=null&&!categorysecond.getCsname().equals("")){
			hql.append(" and c.csname like '%"+categorysecond.getCsname()+"%'");
		}
		//һ���˵���cid
		if(cid!=null&&!cid.equals("")){
			hql.append(" and c.category.cid="+cid);
		}
		
		//����������
		if(sort!=null){
			hql.append(" ORDER BY "+sort+" "+order);
		}
		Page page2= this.getPageReady(page, hql.toString(),rows);
		return page2;
	}

}
