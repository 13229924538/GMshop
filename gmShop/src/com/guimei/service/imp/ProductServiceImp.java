package com.guimei.service.imp;

import java.util.List;

import Utils.Page;

import com.guimei.dao.ProductDao;
import com.guimei.domain.Product;
import com.guimei.service.ProductService;

public class ProductServiceImp implements ProductService {
//ע��dao
	private ProductDao productDao;
	
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	// ��ѯ������Ʒ:
	public List<Product> findHot() {

		String hql="from Product where isHot=1 order by pdate desc";
		List list=productDao.findPage(hql,9,0,null);
		System.out.println(list.size()+"����Ϊ:######################3");
		return list;
		
	}

	//����������Ʒ
	public Page findAll(int page, int rows, String order, String sort,
			Product product, Integer cid, Integer csid) {
		
		return productDao.findAllByPage(page,rows,order,sort,product,cid,csid);
	}

	//ͨ��pid������Ʒ
	public Product findById(Integer pid) {
		
		return productDao.findById(pid);
	}

	//������Ʒ
	public void save(Product product) {
		productDao.save(product);
	}

	//ɾ��һЩ��Ʒ
	public int delete(String ids) {
		int length=ids.split(",").length;
		String[] strIds = ids.split(",");
		String hql = "delete from Product where pid in (";
		for(int i=0;i<strIds.length;i++){
			hql += strIds[i]+",";
		}
		hql = hql.substring(0,hql.length()-1);
		hql += ")";
		System.out.println(hql);
		productDao.executeByHql(hql, null);
		return length;
		
	}

	//�༭��Ʒ
	public void update(Product product) {
		productDao.saveOrUpdate(product);
	}

	
	//----------------------ying------------------
	//ͨ��Csid������Ʒ
	@Override
	public Page findByCsid(Integer page,int rows,Integer csid) {
		// TODO Auto-generated method stub
		System.out.println("��Ʒ�Ķ����˵����:"+csid);
		return productDao.findByCsidByPage(page, rows, csid);
		
	}

	@Override
	public Page findByCid(Integer page, int rows, Integer cid) {
		// TODO Auto-generated method stub
		System.out.println("in ProductServiceImp method findByCid()");
		return productDao.findByCidByPage(page, rows, cid);
	}

}
