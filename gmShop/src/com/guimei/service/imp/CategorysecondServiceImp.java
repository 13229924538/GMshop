package com.guimei.service.imp;

import java.util.List;

import Utils.Page;

import com.guimei.dao.CategorysecondDao;
import com.guimei.domain.Category;
import com.guimei.domain.Categorysecond;
import com.guimei.service.CategorysecondService;

public class CategorysecondServiceImp implements CategorysecondService {
//ע��dao
	private CategorysecondDao categorysecondDao;

	public void setCategorysecondDao(CategorysecondDao categorysecondDao) {
		this.categorysecondDao = categorysecondDao;
	}

	//��ѯ����
	public Page findAll(Categorysecond categorysecond, int page, int rows,
			String order, String sort,Integer cid) {
		
		return categorysecondDao.findByPage(categorysecond,page,rows,order,sort,cid);
	}

	//���
	public void save(Categorysecond categorysecond) {
		 categorysecondDao.save(categorysecond);
	}

	@Override
	public Categorysecond findByName(String csname) {
		List<Categorysecond> list=categorysecondDao.findByProperty("csname", csname);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	//���¶����˵�
	public void update(Categorysecond categorysecond) {
	
		categorysecondDao.update(categorysecond);
		
	}
	//ɾ��һЩ�����˵�
	public int delete(String ids) {
		int length=ids.split(",").length;
		String[] strIds = ids.split(",");
		String hql = "delete from Categorysecond where csid in (";
		for(int i=0;i<strIds.length;i++){
			hql += strIds[i]+",";
		}
		hql = hql.substring(0,hql.length()-1);
		hql += ")";
		System.out.println(hql);
		categorysecondDao.executeByHql(hql, null);
		return length;
	}

	//ͨ��ajax�������ж����˵�������
	public List<Categorysecond> findAllName(Integer cid) {
		if(cid!=null&&!cid.equals("")){
			String hql="from Categorysecond c where c.category.cid=? ";
			return categorysecondDao.findByHql(hql, cid);
		}
		return categorysecondDao.findAll();
	}
	//�����������Ƿ��������Ķ����˵�
	public Categorysecond findCate(Categorysecond categorysecond,String cname) {
	
		String hql="from Categorysecond c where c.category.cname=? and c.csname=?";
		List<Categorysecond> list=categorysecondDao.findByHql(hql,cname,categorysecond.getCsname());
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
}
