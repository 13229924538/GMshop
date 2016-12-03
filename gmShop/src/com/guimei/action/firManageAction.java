package com.guimei.action;

import java.io.IOException;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;

import Utils.Page;

import com.guimei.domain.Category;

import com.guimei.service.CategoryService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/*
 * һ���˵���̨����
 * */
public class firManageAction extends ActionSupport implements ModelDriven<Category> {
	//ģ������
	private Category category=new Category();
	public Category getModel() {
		return category;
	}
	//ע��service
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
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
	//ajax�������е�һ���˵�������
	public String findAllName() throws IOException{
		//��Ӧ���� ���json����������
		ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
		List<Category> list=categoryService.findAllName();
		  JsonConfig config = new JsonConfig();
		//��ȥemps����,��ȥ��һ���˵����������ԣ������˵��Ķ���categoryseconds��
		  config.setExcludes(new String[]{"categoryseconds"});
		String json=JSONArray.fromObject(list,config).toString();//תΪjson		
		ServletActionContext.getResponse().getWriter().print(json);	 
	
	
		return NONE;
	}
	//��ѯ����
	public String findAll() throws IOException{
		ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");//��Ӧ����
		Page pageBean= categoryService.ManFindAll(category,page,rows,order,sort);
	
		int total=pageBean.getTotalNums();//�ܼ�¼��
		List list=pageBean.getList();//ÿ����¼
		JsonConfig config = new JsonConfig();
		//��ȥemps����,��ȥ��һ���˵����������ԣ������˵��Ķ���categoryseconds��
		  config.setExcludes(new String[]{"categoryseconds"});
	String rows=JSONArray.fromObject(list,config).toString();//תΪjson		
		JSONObject jo=new JSONObject();
		//easyui��Ҫ�Ӻ�̨ȡ�������ܸ��������Ҽ�ֵ������total
		jo.put("total", total);
		//easyui��Ҫ�Ӻ�̨ȡ�����ݣ����Ҽ�ֵ������rows
		jo.put("rows", rows);
		ServletActionContext.getResponse().getWriter().print(jo);	
		return NONE;
	}
	//����һ���˵�
		public String add() throws IOException{
			//�Ȳ����Ƿ������ͬ��һ���˵�
			Category c=categoryService.findByName(category.getCname());
			if(c!=null){
				//���ݿ��Ѿ�����һ���˵�
				ServletActionContext.getResponse().getWriter().print("error");
				return NONE;
			}
			categoryService.add(category);
			ServletActionContext.getResponse().getWriter().print(1);
			return NONE;
		}
		//�޸�һ���˵�
		public String edit() throws IOException{
			//�Ȳ����Ƿ������ͬ��һ���˵�
			Category c=categoryService.findByName(category.getCname());
			if(c!=null){
				//���ݿ��Ѿ�����һ���˵�
				ServletActionContext.getResponse().getWriter().print("error");
				return NONE;
			}
			categoryService.edit(category);
			ServletActionContext.getResponse().getWriter().print(1);
			return NONE;
		}
		//ɾ��һЩһ���˵�
		public String delSome() throws IOException{
			int num=categoryService.delete(ids);
			ServletActionContext.getResponse().getWriter().print(num);
			return NONE;
		}
}
