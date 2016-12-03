package com.guimei.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Utils.Page;

import com.guimei.domain.Cartitem;
import com.guimei.domain.Orderitem;
import com.guimei.domain.Orders;
import com.guimei.domain.User;
import com.guimei.service.OrdersService;
import com.guimei.service.OrderitemService;
import com.guimei.service.OrdersService;

public class OrderAction extends BaseAction{
	
	private OrdersService OrdersService;
	private OrderitemService orderitemService;
	
	
	public void setOrderitemService(OrderitemService orderitemService) {
		this.orderitemService = orderitemService;
	}

	public void setOrdersService(OrdersService OrdersService) {
		this.OrdersService = OrdersService;
	}

	@SuppressWarnings("unused")
	public String show() throws Exception {
		//��ȡ���ﳵ�еĹ������
		List<Cartitem> list=(List<Cartitem>) request.getSession().getAttribute("carListProduct");
		for(Cartitem cartitem:list)
		{
			String count=request.getParameter(cartitem.getProduct().getPid().toString());
			cartitem.setCount(new Integer(count));
		}
		//��Ŷ�����ļ���
		List<Orderitem> orderitemlist=new ArrayList<Orderitem>();
		
		Orders orders=new Orders();
		User user=(User) request.getSession().getAttribute("exitUser");
		orders.setAddr(user.getAddr());
		orders.setName(user.getName());
		
		orders.setOrdertime(new Date());
		orders.setPhone(user.getPhone());
		orders.setState(1);
		orders.setUser(user);
		Set<Orderitem> set=new HashSet<Orderitem>();
		/*for(Cartitem cartitem:list)
		{
			Orderitem orderitem=new Orderitem();
			orderitem.setCount(cartitem.getCount());
			orderitem.setProduct(cartitem.getProduct());
			orderitem.setSubtotal(cartitem.getProduct().getPrice()*cartitem.getCount());
			set.add(orderitem);
			//orderitemlist.add(orderitem);
		}*/
		
		
		
		
		Double totalmoney=0.0;
		for(Cartitem cartitem:list)
		{
			Orderitem orderitem=new Orderitem();
			orderitem.setCount(cartitem.getCount());
			orderitem.setProduct(cartitem.getProduct());
			orderitem.setSubtotal(cartitem.getProduct().getPrice()*cartitem.getCount());
			orderitem.setOrders(orders);
			Double money=cartitem.getProduct().getPrice()*cartitem.getCount();
			orderitem.setSubtotal(money);
			orderitemlist.add(orderitem);
			totalmoney=totalmoney+money;
			orderitemService.save(orderitem);
			//System.out.println("��show�����д�ӡ�������ID��"+orderitem.getItemid());
			set.add(orderitem);
			
		}
		orders.setOrderitems(set);
		OrdersService.save(orders);
		Orders updOrders=OrdersService.findById(orders.getOid());
		updOrders.setTotal(totalmoney);
		//���¶�������
		OrdersService.update(updOrders);	
		Orders updOrders1=OrdersService.findById(orders.getOid());
		request.setAttribute("orders", updOrders1);	
		return "show";
	}
	
	public String findAll() throws Exception {
		// TODO Auto-generated method stub
		String oid=request.getParameter("oid");
		//System.out.println("findAll()��ѯһ�����������ж����"+oid);
		Orders order=OrdersService.findById(new Integer(oid));
		//System.out.println("��ӡ����������:"+order.getName());
		//������Ʒ�ж������ܼ�λ
		Double total=0.0;
		for(Orderitem item:(Set<Orderitem>)order.getOrderitems())
		{
			total=total+item.getProduct().getPrice();
		}
		order.setTotal(total);
		OrdersService.update(order);
		request.setAttribute("orders",order);
		return "findAll";
	}
	
	public String pay() throws Exception {
		//System.out.println("�����˸���ķ���");
		//��ȡҪѡ�й�����Ʒ��ID  ��ȡ��Щ��Ʒ�Ķ���ID
		//String[] itemids=request.getParameterValues("obj");
		
		//��ȡ������ID
		String oid=request.getParameter("oid1");
		System.out.println("��ȡ������ID��"+oid);
		Orders order=OrdersService.findById(new Integer(oid));
		order.setState(2);
		OrdersService.update(order);
		//����Щû��֧������û��ɾ�����Ķ���������û��ٴε�¼ʱ ����Щ��Ʒ���빺�ﳵ��
		return "pay";
	}
	//��ѯ���еĶ���
	public String selectAll() throws Exception {
		// TODO Auto-generated method stub
		/*List<Orders> orderList=OrdersService.findAll();
		request.setAttribute("orderList",orderList);*/
		/*for(Orders order:orderList)
		{
			for(Orderitem item:(Set<Orderitem>)order.getOrderitems())
			{
				System.out.println("��ȡ��ƷͼƬ��·����"+item.getProduct().getImage());
			}
		}*/
		int currPage=1;
		String currPage1=request.getParameter("page");
		
		if(currPage1!=null)
		{
			currPage=new Integer(currPage1);
		}
		Integer count=new Integer(OrdersService.uniqueResult().toString());
		//System.out.println("��ӡ�ܵĶ�������"+count);
		request.setAttribute("count",count);
		List<Orders> list=OrdersService.findPage(5,(currPage-1)*5,null);
		Page page=new Page();
		page.setCurrentPage(currPage);
		page.setLimit(5);
		page.setList(list);
		page.setTotalNums(count);
		int pages=0;
		if(count%5==0)
		{
			pages=count/5;
		}
		else
		{
			pages=count/5+1;
		}
		page.setTotalPages(pages);
		request.setAttribute("page",page);
		return "selectAll";
	}
	
	
	public String delete() throws Exception {
		String oid=request.getParameter("oid");
		System.out.println("��ȡɾ��������ID:"+oid);
		Orders order=OrdersService.findById(new Integer(oid));
		OrdersService.delete(order);
		return "delete";
	}

}
