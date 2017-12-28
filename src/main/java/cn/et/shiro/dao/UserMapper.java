/**
 * 
 */
package cn.et.shiro.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Select;

import cn.et.shiro.entity.Menu;
import cn.et.shiro.entity.UserInfo;

/**
 * @author Administrator
 *
 */
public interface UserMapper {
	/**
	 * 根据用户名获取用户信息
	 * @param userName 用户名
	 * @return
	 */
	@Select("select user_name as userName,pass_word as password from user_info where user_name=#{0}")
	public UserInfo queryUser(String userName);
	
	
	/**
	 * 根据用户名查询角色
	 * @param userName 用户名
	 * @return
	 */
	@Select("SELECT r.role_name FROM user_info u "
			+"INNER JOIN user_role_relation rr ON u.user_id=rr.user_id "
			+"INNER JOIN role r ON rr.role_id=r.role_id "
			+"WHERE user_name='suny'")
	public Set<String> queryRoleByName(String userName);
	
	/**
	 * 根据用户名查询权限
	 * @param userName 用户名
	 * @return
	 */
	@Select("SELECT p.perm_tag FROM user_info u "
			+"INNER JOIN user_role_relation rr ON u.user_id=rr.user_id "
			+"INNER JOIN role r ON rr.role_id=r.role_id "
			+"INNER JOIN role_perm_relation rpr ON r.role_id=rpr.role_id "
			+"INNER JOIN perms p ON rpr.perm_id=p.perm_id "
			+"WHERE user_name='suny'")
	public Set<String> queryPermsByName(String userName);
	
	/**
	 * 查询角色和权限信息
	 * @return
	 */
	@Select("select menu_id as menuId,menu_name as menuName,menu_url as menuUrl,menu_filter as menuFilter,is_menu as isMenu  from menu")
	public List<Menu> queryMenu();
	
	/**
	 * 根据URL查询权限
	 * @param url
	 * @return
	 */
	@Select("select menu_id as menuId,menu_name as menuName,menu_url as menuUrl,menu_filter as menuFilter,is_menu as isMenu  from menu where menu_url=#{0}")
	public List<Menu> queryMenuByUrl(String url);

	/**
	 * 根据用户名查询菜单 
	 * @param userName
	 * @return
	 */
	@Select("SELECT menu_name as menuName,menu_url as menuUrl,menu_filter as menuFilter,is_menu as isMenu FROM menu m "+
	" INNER JOIN user_menu_relation umr ON m.menu_id=umr.menu_id "+
	" INNER JOIN user_info u ON umr.user_id=u.user_id "+
	" WHERE user_name=#{0}")
	public List<Menu> queryMenuByUser(String userName);
}
