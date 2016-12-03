package com.guimei.action;

import java.util.List;

import com.guimei.domain.Cartitem;
import com.guimei.domain.Product;
import com.guimei.service.ProductService;

public class ProductAction extends BaseAction{
	//ע��productService
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	public String findByPid() throws Exception {
		// TODO Auto-generated method stub
		String pid=request.getParameter("pid");
		Product pro=productService.findById(new Integer(pid));
		System.out.println("��ת�����ﳵ��ҳ��");
		System.out.println(pro.getPname());
		List<Cartitem> carListProduct=(List<Cartitem>) request.getSession().getAttribute("carListProduct");
		if(carListProduct==null)
		{
			return "gologin";
		}
		else
		{
			int flag=0;
			for(Cartitem pro1:carListProduct)
			{
				if(pro1.getProduct().getPid().equals(pro.getPid()))
				{
					flag++;
					
				}
			}
			if(flag==0)
			{
				Cartitem cartitem=new Cartitem();
				cartitem.setProduct(pro);
				cartitem.setCount(1);
				carListProduct.add(cartitem);
			}
			
		}
		request.getSession().setAttribute("carListProduct",carListProduct);
		return "findByPid";
	}
	//ɾ�����ﳵ�е�ĳ��������Ʒ
	
	public String del() throws Exception {
		String pid=request.getParameter("pid");
		//System.out.println("Ҫɾ����Ʒ��pid�ǣ�"+pid);
		List<Cartitem> list=(List<Cartitem>)request.getSession().getAttribute("carListProduct");
		for(Cartitem cartitem:list)
		{
			if(cartitem.getProduct().getPid().equals(new Integer(pid)))
			{
				list.remove(cartitem);
				break;
			}
		}
		request.getSession().setAttribute("carListProduct", list);
		return "del";
	}
}
