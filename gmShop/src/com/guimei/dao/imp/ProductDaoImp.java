package com.guimei.dao.imp;

import java.util.List;

import org.springframework.orm.hibernate4.BaseDaoImpl;

import Utils.Page;

import com.guimei.dao.ProductDao;
import com.guimei.domain.Product;

public class ProductDaoImp extends BaseDaoImpl<Product> implements ProductDao {
	//����������Ʒ
	public Page findAllByPage(int page, int rows, String order, String sort,
			Product product, Integer cid, Integer csid) {
		StringBuffer hql = new StringBuffer("from Product p where 1=1");
	
		//һ���˵���cid
		if(cid!=null&&!cid.equals("")){
			hql.append(" and p.categorysecond.category.cid="+cid);
		}	
		//2���˵���csid
		if(csid!=null&&!csid.equals("")){
	
			hql.append(" and p.categorysecond.csid="+csid);
		}
		//��Ʒ����
		if(product.getPname()!=null&&!product.getPname().equals("")){
			hql.append(" and pname like '%"+product.getPname()+"%'");
		}
		//����������
		if(sort!=null){
			hql.append(" ORDER BY "+sort+" "+order);
		}
		Page page2= this.getPageReady(page, hql.toString(),rows);

		return page2;
	}

	
	/**
	 * ying
	 */
	//���ݶ����˵�������Ʒ����ҳ��ʾ
	@Override
	public Page findByCsidByPage(int page, int rows, Integer csid) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("from Product p where 1=1");
		if(csid!=null&&!csid.equals("")){
			hql.append(" and p.categorysecond.csid="+csid);
		}
		Page p= this.getPageReady(page, hql.toString(),rows);
		return p;
	}

	//����һ���˵�������Ʒ����ҳ��ʾ
	@Override
	public Page findByCidByPage(int page, int rows, Integer cid) {
		// TODO Auto-generated method stub
		Page p=new Page();
		String hql="from Product where categorysecond.csid in (select csid from Categorysecond where category.cid="+cid+")";
		p=this.getPageReady(page, hql, rows);
		return p;
	}
	
	

}
