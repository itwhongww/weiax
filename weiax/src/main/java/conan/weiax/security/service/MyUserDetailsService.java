package conan.weiax.security.service;

import com.alibaba.druid.util.StringUtils;
import conan.weiax.security.dto.Role;
import conan.weiax.security.dto.SecurityUser;
import conan.weiax.security.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class MyUserDetailsService implements UserDetailsService {
	private static Logger log = LoggerFactory.getLogger(MyUserDetailsService.class);

	@Autowired
	private UserService loginService;

	@Autowired
	private RoleService roleService;

	@Override
	public UserDetails loadUserByUsername(String username) {
		if (StringUtils.isEmpty(username)) {
			throw new UsernameNotFoundException("用户名为空");
		}
		User user = loginService.findByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("用户名不存在");
		}
		SecurityUser securityUser = new SecurityUser(user);
		Role role = roleService.findRoleByUserId(user.getUserId());
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        securityUser.setAuthorities(authorities);
		return securityUser;
	}
}
