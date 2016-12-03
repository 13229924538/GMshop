package com.guimei.action;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.guimei.domain.User;
import com.guimei.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/*
 * �û�action
 * */
public class UserAction extends ActionSupport implements ModelDriven<User>{
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
	//��¼����
	public String login(){

		User exitUser=userService.login(user);
		if(exitUser!=null){
			//�û����ڣ����û�����Ϣ���뵽session��
			HttpSession session= ServletActionContext.getRequest().getSession();
			session.setAttribute("exitUser",exitUser);
			return "loginSucc";
		}
		else{
			//��¼ʧ��
			this.addActionError("��¼ʧ��:�û������������!!!");
			return "loginFalse";
		}
		
	} 
	//�û�ע��
	public String regist(){	
		user.setnYear(ServletActionContext.getRequest().getParameter("nYear"));
		userService.save(user);
		return "registSucc";
	}
	//����û����Ƿ�ע��
	public String findByName() throws IOException{
		User exitUser=userService.findByName(user.getUsername());
		if(exitUser!=null){
			//�û����ڣ�
			ServletActionContext.getResponse().getWriter().print("false");
			//ҳ�治��ת
			return NONE;	
		}
		else{
			//��¼�ɹ�
			ServletActionContext.getResponse().getWriter().print("true");
			return NONE;	
		}
		
		
	
	}
}
