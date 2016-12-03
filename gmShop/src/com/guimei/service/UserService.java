package com.guimei.service;

import Utils.Page;

import com.guimei.domain.User;

public interface UserService {
	//��¼����
	User login(User user);
	//����û����Ƿ�ע��
	User findByName(String username);
	//�û�ע��
	void save(User user);
	//��ѯ����
	Page findAll(User user, int page, int rows, String order, String sort);
	//�����û�
	void add(User user);
	//�޸��û�
	void edit(User user);
	//ɾ��һЩ�û�
	int delete(String ids);
	//����id�����û�
		User findById(Integer uid);

}
