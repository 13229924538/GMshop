package com.guimei.action;

import java.util.List;

import com.guimei.domain.Category;
import com.guimei.domain.Product;
import com.guimei.service.CategoryService;
import com.guimei.service.ProductService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport{
	// ע��һ�������Service:
	private CategoryService categoryService;
	//ע����ƷService
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/**
	 * ִ�еķ�����ҳ�ķ���:
	 */
	public String execute() throws Exception {
		// ��ѯ����һ�����༯��
		List<Category> cList = categoryService.findAll();		
		// ��һ��������뵽Session�ķ�Χ:
		ActionContext.getContext().getSession().put("cList", cList);
		// ��ѯ������Ʒ:
		List<Product> hList = productService.findHot();
		// ���浽ֵջ��:
		ActionContext.getContext().getValueStack().set("hList", hList);
		return "index";
	}
}
