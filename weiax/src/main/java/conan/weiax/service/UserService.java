package conan.weiax.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import conan.weiax.config.annotation.Read;
import conan.weiax.config.annotation.Write;
import conan.weiax.dao.UserMapper;
import conan.weiax.dto.User;

public class UserService {
	@Autowired
	private UserMapper userMapper;
	
	@Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
	@Write
	public void saveUser(String name ,String password){
	}
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	@Read
	public List<User> findUsers(){
		userMapper.findByName("hongww");
		return null;
	}
}
