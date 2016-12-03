package com.guimei.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import Utils.Page;

import com.guimei.domain.Product;
import com.guimei.service.ProductService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class proManageAction extends BaseAction implements
		ModelDriven<Product> {
private Product product=new Product();
	//ģ������
	public Product getModel() {	
		return product;
	}
	//ע��service
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}	
	private int rows;//ÿҳ��¼��
	private int page;//��ǰҳ��
	private String order;//��ȡҪ����ķ�ʽ
	private String sort;//��ȡҪ�����Ԫ��
	private Integer cid;//��ȡ������һ���˵���cid
	private Integer csid;//��ȡ������һ���˵���cid
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
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	//���ܴ�������Ҫɾ����id����
	private String ids;	
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}	

	// �ļ��ϴ���Ҫ����������:
	private File upload;
	private String uploadFileName;
	private String uploadContentType;

	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}	
	
	//����������Ʒ
		public String findAll() throws IOException{
			ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");//��Ӧ����
			Page pageBean= productService.findAll(page,rows,order,sort,product,cid,csid);
		
			int total=pageBean.getTotalNums();//�ܼ�¼��
			List list=pageBean.getList();//ÿ����¼
		
			JsonConfig config = new JsonConfig();
			//��ȥemps����,��ȥ��һ���˵����������ԣ������˵��Ķ���categoryseconds��
			  config.setExcludes(new String[]{"categorysecond","orderitems"});
		String rows=JSONArray.fromObject(list,config).toString();//תΪjson		
			JSONObject jo=new JSONObject();
			//easyui��Ҫ�Ӻ�̨ȡ�������ܸ��������Ҽ�ֵ������total
			jo.put("total", total);
			//easyui��Ҫ�Ӻ�̨ȡ�����ݣ����Ҽ�ֵ������rows
			jo.put("rows", rows);
			ServletActionContext.getResponse().getWriter().print(jo);	
			return NONE;
		}
		//������Ʒ
	public String add() throws IOException{
		
		if(upload != null){
	
			// ����ƷͼƬ�ϴ�����������.
			// ����ϴ�ͼƬ�ķ�������·��.
			String path = ServletActionContext.getServletContext().getRealPath(
					"/products");
			// �����ļ����Ͷ���:
			File diskFile = new File(path + "//" + uploadFileName);
			// �ļ��ϴ�:
			FileUtils.copyFile(upload, diskFile);
	
			product.setImage("products/" + uploadFileName);
		}

		productService.save(product);
		ServletActionContext.getResponse().getWriter().print("ok");	
		return NONE;
	}
	//ͨ��pid������Ʒ
	public String findById(){
		Product proList=productService.findById(product.getPid());
	
		// ���浽ֵջ��:
		ActionContext.getContext().getValueStack().set("proList", proList);
		return "findSucc";
	}
	//ɾ��һЩ��Ʒ
	public String delSome() throws IOException{
		int num=productService.delete(ids);
		ServletActionContext.getResponse().getWriter().print(num);
		return NONE;
	}
	//�༭��Ʒ
	public String edit() throws IOException{
		System.out.println(product.getImage()+"ͼƬ��ַ��******************");
		// �ϴ�:
		if(upload != null){
			String delPath = ServletActionContext.getServletContext().getRealPath(
				"/" + product.getImage());
			File file = new File(delPath);
			file.delete();
			// ����ϴ�ͼƬ�ķ�������·��.
			String path = ServletActionContext.getServletContext().getRealPath(
					"/products");
			// �����ļ����Ͷ���:
			File diskFile = new File(path + "//" + uploadFileName);
			// �ļ��ϴ�:
			FileUtils.copyFile(upload, diskFile);
				product.setImage("products/" + uploadFileName);
				}
	
		productService.update(product);	
		ServletActionContext.getResponse().getWriter().print("ok");
		return NONE;
	}
	/**
	 * ying 
	 */
	//���ݶ����˵���ѯ��Ʒ
	public String findByCsid(){
		System.out.println("in proManageAction method findByCsid()");
		this.rows=6;
		Page csidpro=productService.findByCsid(page,rows,csid);
		System.out.println("���ȣ�"+csidpro.getList().size());
		
		request.setAttribute("propage",csidpro);
		//���û�ѡ��Ķ����˵�����request�У�����jspҳ����ж�
		request.setAttribute("option","twomenu");
		return "success";
	}
	
	//����һ���˵�������Ʒ
	public String findByCid(){
		System.out.println("in proManageAction method findByCid()");
		this.rows=6;
		System.out.println("cid:"+cid);
		Page cidpro=productService.findByCid(page,rows,cid);
		System.out.println("���ȣ�"+cidpro.getList().size());
		System.out.println("cidΪ:"+cid);
		request.setAttribute("propage",cidpro);
		//���û�ѡ���һ���˵�����request�У�����jspҳ����ж�
		request.setAttribute("option","onemenu");
		return "success";
	}
}
