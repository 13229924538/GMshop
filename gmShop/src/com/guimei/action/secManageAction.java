package com.guimei.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;

import Utils.Page;

import com.guimei.domain.Category;
import com.guimei.domain.Categorysecond;
import com.guimei.service.CategoryService;
import com.guimei.service.CategorysecondService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/*
 * ��̨�����˵�
 * */
public class secManageAction extends ActionSupport implements ModelDriven<Categorysecond> {
	//����ģ������
	private Categorysecond categorysecond=new Categorysecond(); 
	public Categorysecond getModel() {
		return categorysecond;
	}
	//ע��һ��service
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	//ע��2��service
	private CategorysecondService categorysecondService;
	public void setCategorysecondService(CategorysecondService categorysecondService) {
		this.categorysecondService = categorysecondService;
	}
	private int rows;//ÿҳ��¼��
	private int page;//��ǰҳ��
	private String order;//��ȡҪ����ķ�ʽ
	private String sort;//��ȡҪ�����Ԫ��
	private Integer cid;//��ȡ������һ���˵���cid
	private String cname;//��ȡһ���˵�����
	
	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
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
		Page pageBean= categorysecondService.findAll(categorysecond,page,rows,order,sort,cid);
	
		int total=pageBean.getTotalNums();//�ܼ�¼��
		List<Categorysecond> list=pageBean.getList();//ÿ����¼
		List jsonList=new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			jsonList.add("{'csid':"+list.get(i).getCsid()+",'csname':'"+list.get(i).getCsname()+"','cid':'"+list.get(i).getCategory().getCname()+"'}");

		}

	 /*JsonConfig config = new JsonConfig();
			//��ȥemps����,��ȥ��һ���˵����������ԣ������˵��Ķ���categoryseconds��
			  config.setExcludes(new String[]{"category","products"});
		String rows=JSONArray.fromObject(list,config).toString();//תΪjson		
*/		
		String rows=JSONArray.fromObject(jsonList).toString();//תΪjson	
		JSONObject jo=new JSONObject();
		//easyui��Ҫ�Ӻ�̨ȡ�������ܸ��������Ҽ�ֵ������total
		jo.put("total", total);
		//easyui��Ҫ�Ӻ�̨ȡ�����ݣ����Ҽ�ֵ������rows
		jo.put("rows", rows);
		ServletActionContext.getResponse().getWriter().print(jo);	
		return NONE;
	}
	//���
	public String add() throws IOException{
		
		//�����������Ƿ��������Ķ����˵�
		Categorysecond c=categorysecondService.findByName(categorysecond.getCsname());
		if(c!=null){
			//����
			ServletActionContext.getResponse().getWriter().print("error");	
			return NONE;
		}
	
		//�������ڵ�һ���˵�
		Category category=categoryService.findById(cid);

		categorysecond.setCategory(category);
		//��ӵ����ݿ�
		categorysecondService.save(categorysecond);
		ServletActionContext.getResponse().getWriter().print("OK");	
		return NONE;
	}
	//���¶����˵�
	public String update() throws IOException{
		//�����������Ƿ��������Ķ����˵�
		Categorysecond c=categorysecondService.findCate(categorysecond,cname);
		if(c!=null){
			//����
			ServletActionContext.getResponse().getWriter().print("error");	
			return NONE;
		}
		System.out.println(cname+"******************************");
		//�Ȳ�ѯһ���˵�
		Category category=categoryService.findByName(cname);
		//����һ���˵��������˵���
		categorysecond.setCategory(category);
		categorysecondService.update(categorysecond);
		ServletActionContext.getResponse().getWriter().print("1");	
		return NONE;
	}
	//ɾ��һЩ�����˵�
	public String delSome() throws IOException{
		int num=categorysecondService.delete(ids);
		ServletActionContext.getResponse().getWriter().print(num);
		return NONE;
	}
	//ͨ��ajax�������ж����˵�������,����һ���˵�cid
	public String findAllName() throws IOException{
		//��Ӧ���� ���json����������
		ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");

		List<Categorysecond> list=categorysecondService.findAllName(cid);

		  JsonConfig config = new JsonConfig();
		//��ȥemps����,��ȥ��һ���˵����������ԣ������˵��Ķ���categoryseconds��
		  config.setExcludes(new String[]{"category","products"});
		String json=JSONArray.fromObject(list,config).toString();//תΪjson		
		ServletActionContext.getResponse().getWriter().print(json);	 
		return NONE;
	}
}
