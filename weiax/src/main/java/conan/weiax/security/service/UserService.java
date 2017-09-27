package conan.weiax.security.service;


import conan.weiax.config.annotation.Read;
import conan.weiax.config.annotation.Write;
import conan.weiax.security.dao.RoleMapper;
import conan.weiax.security.dao.UserMapper;
import conan.weiax.security.dao.UserRoleMapper;
import conan.weiax.security.dto.User;
import conan.weiax.security.utils.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
	private static Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
	@Write
	public void saveUser(String name ,String password){
		log.debug("================saveUser");
		password = MD5Util.encode(password);
		userMapper.insert(new User(1L,name,password,null));
	}
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	@Read
	public List<User> findUsers(){
		List<User> users = userMapper.findAll();
		return users;
	}
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	@Read
	public User findByUsername(String username){
		
		User user = userMapper.findByName(username);
		return user;
	}
}
