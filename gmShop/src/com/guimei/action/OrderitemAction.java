package com.guimei.action;

import com.guimei.domain.Orderitem;
import com.guimei.service.OrderitemService;

public class OrderitemAction extends BaseAction{
	private OrderitemService orderitemService;

	public void setOrderitemService(OrderitemService orderitemService) {
		this.orderitemService = orderitemService;
	}
	
	public String del() throws Exception {
		String itemid=request.getParameter("itemid");
		String oid=request.getParameter("oid");
		//System.out.println("�õ��˶�����ID:"+oid);
		//System.out.println("���붩����ɾ���ķ���:"+itemid);
		Orderitem item=orderitemService.findById(new Integer(itemid));
		orderitemService.delete(item);
		request.setAttribute("oid",oid);
		return "del";
	}

}
