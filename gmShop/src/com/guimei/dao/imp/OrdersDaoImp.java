package com.guimei.dao.imp;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate4.BaseDaoImpl;

import Utils.Page;

import com.guimei.dao.OrdersDao;
import com.guimei.domain.Orders;

public class OrdersDaoImp extends BaseDaoImpl<Orders> implements OrdersDao {
	//�������ж���
	public Page findAllByPage(int page, int rows, String order, String sort,
			Orders orders) {
		StringBuffer hql = new StringBuffer("from Orders where 1=1");
		
		//����״̬
		if(orders.getState()!=null&&!orders.getState().equals("")){
			
			hql.append(" and state="+orders.getState());
		}	
	
		//������
		if(orders.getName()!=null&&!orders.getName().equals("")){
			hql.append(" and name like '%"+orders.getName()+"%'");
		}
		//����������
		if(sort!=null){
			hql.append(" ORDER BY "+sort+" "+order);
		}
		Page page2= this.getPageReady(page, hql.toString(),rows);

		return page2;
	}

	
}
