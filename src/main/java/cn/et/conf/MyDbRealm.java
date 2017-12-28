package cn.et.conf;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.et.shiro.dao.UserMapper;
import cn.et.shiro.entity.UserInfo;

@Component
public class MyDbRealm extends AuthorizingRealm{

	@Autowired
	UserMapper mapper;
	
	
	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//令牌  获取页面的用户名和密码
		UsernamePasswordToken upt = (UsernamePasswordToken)token;
		//获取数据库中的用户信息
		UserInfo user = mapper.queryUser(token.getPrincipal().toString());
		if(user!=null && user.getPassword().equals(new String(upt.getPassword()))){		
			SimpleAccount sa = new SimpleAccount(upt.getUsername(),upt.getPassword(),"MyDbRealm");
			System.out.println("成功");
			return sa;
		}
		System.out.println("失败");
		return null;
	}
	
	
	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//获取当前登录的用户
		String userName = principals.getPrimaryPrincipal().toString();
		//获取当前用户拥有的角色
		Set<String> roleList = mapper.queryRoleByName(userName);
		//获取当前用户拥有的权限
		Set<String> permsList = mapper.queryPermsByName(userName);	
		SimpleAuthorizationInfo sa = new SimpleAuthorizationInfo();
		sa.setRoles(roleList);
		sa.setStringPermissions(permsList);		
		return sa;
	}

	
	
}





