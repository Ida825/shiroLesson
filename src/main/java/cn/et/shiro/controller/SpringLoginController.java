package cn.et.shiro.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.et.shiro.dao.UserMapper;
import cn.et.shiro.entity.Menu;

@Controller
public class SpringLoginController {
	@Autowired
	UserMapper mapper;
	
	@RequestMapping("/loginShiro")
	public String login(String userName,String password,Model model){
		//��ȡ�û�
		Subject currentUser = SecurityUtils.getSubject();
		
		//���� �û��������� ��ʵ����credentials  
		UsernamePasswordToken token = new UsernamePasswordToken(userName,password);
		try {
			//��ʼ��¼����
			currentUser.login(token);
			
			//��ѯ�˵�
			List<Menu> menuList = mapper.queryMenuByUser(userName);
			//�赽model�У�request�У�
			model.addAttribute("menuList", menuList);
			
		    //��ת���ɹ�ҳ��
			return "/layout.jsp";
			
		} catch ( UnknownAccountException uae ) {
			System.out.println("�û�������");
		} catch ( IncorrectCredentialsException ice ) {
		    System.out.println("��������");
		} catch ( LockedAccountException lae ) {
			System.out.println("�˺�������");
		} catch ( AuthenticationException ae ) {
		    System.out.println("��¼ʧ��");
		}
		
		return "/error.jsp";
		
	}
	
}
