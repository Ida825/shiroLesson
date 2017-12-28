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
		//获取用户
		Subject currentUser = SecurityUtils.getSubject();
		
		//令牌 用户名和密码 其实就是credentials  
		UsernamePasswordToken token = new UsernamePasswordToken(userName,password);
		try {
			//开始登录操作
			currentUser.login(token);
			
			//查询菜单
			List<Menu> menuList = mapper.queryMenuByUser(userName);
			//设到model中（request中）
			model.addAttribute("menuList", menuList);
			
		    //跳转到成功页面
			return "/layout.jsp";
			
		} catch ( UnknownAccountException uae ) {
			System.out.println("用户名有误");
		} catch ( IncorrectCredentialsException ice ) {
		    System.out.println("密码有误");
		} catch ( LockedAccountException lae ) {
			System.out.println("账号已锁定");
		} catch ( AuthenticationException ae ) {
		    System.out.println("登录失败");
		}
		
		return "/error.jsp";
		
	}
	
}
