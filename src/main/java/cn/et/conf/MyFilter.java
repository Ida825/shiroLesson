package cn.et.conf;

import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.et.shiro.dao.UserMapper;
import cn.et.shiro.entity.Menu;

@Component  
public class MyFilter extends AuthorizationFilter {  
   
      
    @Autowired  
    private ShiroFilterFactoryBean sffb;  
    /**  
     * 匹配指定过滤器规则的url  
     * @param regex  
     * @param url  
     * @return  
     */  
    public static boolean matchUrl(String regex,String url){  
        regex=regex.replaceAll("/+", "/");  
        if(regex.equals(url)){  
            return true;  
        }  
        regex=regex.replaceAll("\\.", "\\\\.");  
        // /login.html  /l*.html  
        regex=regex.replaceAll("\\*", ".*");  
        // /**/login.html  /a/b/login.html  
        if(regex.indexOf("/.*.*/")>=0){  
            regex=regex.replaceAll("/\\.\\*\\.\\*/", "((/.*/)+|/)");  
        }  
        System.out.println(regex+"----"+url);  
        return Pattern.matches(regex, url);  
    }  
    
    @Autowired
    UserMapper mapper;
    
    /**  
     * 测试
     * @param args  
     */  
    public static void main(String[] args) {  
        System.out.println(matchUrl("/**/s*.html","/t/g/login.html"));  
    }  
   
    
    
    /**  
     *  isAccessAllowed用于判断当前url的请求是否能验证通过  如果验证失败 调用父类的onAccessDenied决定跳转到登录失败页还是授权失败页面  
     */  
    @Override  
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)  
            throws Exception { 
    	
        HttpServletRequest req=(HttpServletRequest)request;

        String contextPath = req.getContextPath();
   
        String url=req.getRequestURI(); 
        url=url.split(contextPath)[1];
        
        
       // List<Menu> queryMenu = mapper.queryMenuByUrl(url);
 
        List<Menu> queryMenu = mapper.queryMenu();
        

        if(queryMenu.size()==0){
        	return false;
        }
        //循环菜单获取权限
        String urlAuth = null;
        for (Menu menu : queryMenu) {
			if(matchUrl(menu.getMenuUrl(),url)){
				urlAuth = menu.getMenuFilter();
			}
		}
      
        if(urlAuth==null){  
            return false;  
        }  
       
        //配置的过滤器是anon 直接放过  
        if(urlAuth.startsWith("anon")){  
            return true;  
        }  
        
        //配置的是authc 判断当前用户是否认证通过  
        Subject subject = getSubject(request, response);  
        if(urlAuth.startsWith("authc")){  
            return subject.isAuthenticated();  
        }  
        //授权认证 也需要判断是否登录 没有登录返回 登录继续下面的验证 
        boolean ifAuthc=subject.isAuthenticated();  
        if(!ifAuthc)  
            return ifAuthc;  
        //如果是定义的roles过滤器  获取所有的roles 一般是roles[a,b]  
        if(urlAuth.startsWith("roles")){  
            String[] rolesArray=urlAuth.split("roles\\[")[1].split("\\]")[0].split(",");  
            if (rolesArray == null || rolesArray.length == 0) {  
                return true;  
            }  
            Set<String> roles = CollectionUtils.asSet(rolesArray);  
            return subject.hasAllRoles(roles);  
        }  
        
        if(urlAuth.startsWith("perms")){  
            String[] perms=urlAuth.split("perms\\[")[1].split("\\]")[0].split(",");  
            boolean isPermitted = true;  
            if (perms != null && perms.length > 0) {  
                if (perms.length == 1) {  
                    if (!subject.isPermitted(perms[0])) {  
                        isPermitted = false;  
                    }  
                } else {  
                    if (!subject.isPermittedAll(perms)) {  
                        isPermitted = false;  
                    }  
                }  
            }  
  
            return isPermitted;  
        }  
        return false;  
    }  
  
}  
