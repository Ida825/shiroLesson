package cn.et;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class TestShiro {


	public static void main(String[] args) {
		//从配置文件中读取用户的权限信息
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:my.ini");
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		
		//获取登录用户信息
		Subject currentUser =  SecurityUtils.getSubject();
		//当前用户的会话
		Session session = currentUser.getSession();
		/**
		 * 用户包括两部分：
		 *   principals（本人）：表示用户的标识信息 比如用户名 用户地址等
		 *   credentials（凭证）：表示用户用于登录的凭证 比如密码 证书等
		 */
		//判断是否登录
		if(!currentUser.isAuthenticated()){
			//令牌 用户名和密码 其实就是credentials  
			UsernamePasswordToken token = new UsernamePasswordToken("suny","123456");
			token.setRememberMe(true);
			
			try {
				currentUser.login(token);
			    System.out.println("登录成功");
			    System.out.println(currentUser.isAuthenticated());
			} catch ( UnknownAccountException uae ) {
				System.out.println("账号错误");
			} catch ( IncorrectCredentialsException ice ) {
			    System.out.println("密码不匹配");
			} catch ( LockedAccountException lae ) {
				System.out.println("账号被锁定");
			} catch ( AuthenticationException ae ) {
			    System.out.println("位置异常");
			}
		}
	}

}
