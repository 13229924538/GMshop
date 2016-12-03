package com.guimei.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;



import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import Utils.Page;

import com.guimei.domain.Adminuser;
import com.guimei.domain.User;
import com.guimei.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class uMAnageAction extends ActionSupport implements ModelDriven<User> {
//	�����û�����
	private User user=new User();
	public User getModel() {
		return user;
	}
	//ע��service
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	private int rows;//ÿҳ��¼��
	private int page;//��ǰҳ��
	private String order;//��ȡҪ����ķ�ʽ
	private String sort;//��ȡҪ�����Ԫ��
	
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

//���ܴ�������Ҫɾ����id����
	private String ids;	
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}

	//��ѯ����
	public String findAll() throws IOException{
		ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");//��Ӧ����
		Page pageBean= userService.findAll(user,page,rows,order,sort);
	
		int total=pageBean.getTotalNums();//�ܼ�¼��
		List list=pageBean.getList();//ÿ����¼
		 JsonConfig config = new JsonConfig();
			//��ȥemps����,��ȥ��һ���˵����������ԣ������˵��Ķ���categoryseconds��
			  config.setExcludes(new String[]{"orderses"});
			String rows=JSONArray.fromObject(list,config).toString();//תΪjson		
		JSONObject jo=new JSONObject();
		//easyui��Ҫ�Ӻ�̨ȡ�������ܸ��������Ҽ�ֵ������total
		jo.put("total", total);
		//easyui��Ҫ�Ӻ�̨ȡ�����ݣ����Ҽ�ֵ������rows
		jo.put("rows", rows);
		ServletActionContext.getResponse().getWriter().print(jo);	
		return NONE;
	}
	//�����û�
	public String add() throws IOException{
	
			//�Ȳ����Ƿ������ͬ��username(��¼��������ͬ)
			User u=userService.findByName(user.getUsername());
			if(u!=null){
				//���ݿ��Ѿ����ڴε�¼��
				ServletActionContext.getResponse().getWriter().print("error");
				return NONE;
			}
		userService.add(user);
		ServletActionContext.getResponse().getWriter().print(1);
		return NONE;
	}

	//�޸��û�
	public String edit() throws IOException{		
		userService.edit(user);
		ServletActionContext.getResponse().getWriter().print(1);
		return NONE;
	
	}
	//ɾ��һЩ�û�
	public String delSome() throws IOException{
		int num=userService.delete(ids);
		if(num!=-1){
			ServletActionContext.getResponse().getWriter().print(num);
			return NONE;
		}
		ServletActionContext.getResponse().getWriter().print("err");
		return NONE;
	}
	
}
