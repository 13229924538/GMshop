package com.guimei.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.guimei.domain.Adminuser;
import com.guimei.domain.User;
import com.guimei.service.AdminService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class adminManageAction extends ActionSupport implements ModelDriven<Adminuser> {
	//ע��ģ������
	private Adminuser adminuser=new Adminuser();
	public Adminuser getModel() {
		return adminuser;
	}
	//ע��service
	private AdminService adminService;
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	
	//��¼
	public String login(){
		Adminuser adExit=adminService.login(adminuser);
		if(adExit!=null){
			//�û����ڣ����û�����Ϣ���뵽session��
			HttpSession session= ServletActionContext.getRequest().getSession();
			session.setAttribute("adExit",adExit);
			session.removeAttribute("info");
			return "loginSucc";
		}
		else{
			//��¼ʧ��
			this.addActionError("��¼ʧ��:�û������������!!!");
			return "loginFalse";
		}
	}
	//�˳�
	public String quit(){
		
		HttpSession session= ServletActionContext.getRequest().getSession();
		session.removeAttribute("adExit");
		this.addActionMessage("�˳��ɹ�������������µ�¼");
		return "quit";
	}
}
