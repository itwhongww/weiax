package conan.weiax.security.service;

import conan.weiax.config.annotation.Read;
import conan.weiax.config.annotation.Write;
import conan.weiax.security.dao.RoleMapper;
import conan.weiax.security.dto.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleService {
	@Autowired
	private RoleMapper roleMapper;
	
	@Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
	@Write
	public void saveRole(String name){
		//userMapper.insert(new User(1L,name,10));
	}
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	@Read
	public List<Role> getRoles(Long id){
		List<Role> roles = roleMapper.findAll();
		return roles;
	}
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	@Read
	public Role findRoleByUserId(Long userId){
		Role role = roleMapper.findRoleByUserId(userId);
		return role;
	}
	public boolean authorized(Long id, Object targetDomainObject, Object permission) {
		// TODO Auto-generated method stub
		return false;
	}
}
