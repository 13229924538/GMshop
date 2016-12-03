package com.guimei.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;

import Utils.Page;

import com.guimei.domain.Orders;
import com.guimei.domain.User;
import com.guimei.service.OrdersService;
import com.guimei.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class dinManageAction extends ActionSupport implements ModelDriven<Orders> {
	
	private Orders orders=new Orders();
	public Orders getModel() {	
		return  orders;
	}
	private OrdersService ordersService;
	public void setOrdersService(OrdersService ordersService) {
		this.ordersService = ordersService;
	}
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	private int rows;//ÿҳ��¼��
	private int page;//��ǰҳ��
	private String order;//��ȡҪ����ķ�ʽ
	private String sort;//��ȡҪ�����Ԫ��
	private Integer uid;//�û�id
	

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

		//�������ж���
		public String findAll() throws IOException{
			ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");//��Ӧ����
			
			Page pageBean= ordersService.findAll(page,rows,order,sort,orders);
			
			int total=pageBean.getTotalNums();//�ܼ�¼��
			List<Orders> list=pageBean.getList();//ÿ����¼
			List jsonList=new ArrayList();
			for (int i = 0; i < list.size(); i++) {
				jsonList.add("{'oid':"+list.get(i).getOid()+",'total':'"+list.get(i).getTotal()+"','state':'"+list.get(i).getState()
						+"','name':'"+list.get(i).getName()+"','phone':'"+list.get(i).getPhone()+"','addr':'"+list.get(i).getAddr()
						+"','uid':'"+list.get(i).getUser().getUid()+"','ordertime':'"+list.get(i).getOrdertime()+"'}");

			}
		/*	JsonConfig config = new JsonConfig();
			//��ȥemps����,��ȥ�뼶��������
				  config.setExcludes(new String[]{"user","orderitems"});
			String rows=JSONArray.fromObject(list,config).toString();//תΪjson		*/
			String rows=JSONArray.fromObject(jsonList).toString();//תΪjson	
			JSONObject jo=new JSONObject();
				//easyui��Ҫ�Ӻ�̨ȡ�������ܸ��������Ҽ�ֵ������total
				jo.put("total", total);
				//easyui��Ҫ�Ӻ�̨ȡ�����ݣ����Ҽ�ֵ������rows
				jo.put("rows", rows);
				ServletActionContext.getResponse().getWriter().print(jo);	
				return NONE;
			}
		//�༭����
		public String update() throws IOException{

			
			//�Ȳ����ٱ���
			User u=userService.findById(uid);
			orders.setUser(u);
			ordersService.update(orders);
			ServletActionContext.getResponse().getWriter().print("1");	
			return NONE;
		}
		//����
		public String sendGoods() throws IOException{
			//�Ȳ���
			orders=ordersService.findById(orders.getOid());
			//����״̬3(����)
			orders.setState(3);
			//����
			ordersService.update(orders);
			ServletActionContext.getResponse().getWriter().print("ok");	
			return NONE;
		}
}
