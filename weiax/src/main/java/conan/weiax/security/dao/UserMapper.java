package conan.weiax.security.dao;

import conan.weiax.security.dto.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
	@Select("SELECT userId,userName,password,description FROM USER WHERE username = #{username}")
	User findByName(@Param("username") String username);

	@Insert("INSERT INTO USER(userName,password,description) VALUES(#{username}, #{password},#{description})")
	int insert(User user);

	@Update("UPDATE user SET userName=#{userName},password=#{password},description=#{description} WHERE userId=#{userId}")
	void updateById(User user);

	@Update("UPDATE user SET userName=#{userName},password=#{password},description=#{description} WHERE userName=#{userName}")
	void updateByUserName(User user);

	@Delete("DELETE FROM user WHERE userId=#{userId}")
	void deleteById(Long id);

	@Delete("DELETE FROM user WHERE userName=#{userName}")
	void deleteByUserName(String userName);

	@Results({ @Result(property = "userId", column = "userId"),
			@Result(property = "userName", column = "userName"),
			@Result(property = "password", column = "password"),
			@Result(property = "description", column = "description") })
	@Select("SELECT userId,userName,password,description FROM user")
	List<User> findAll();
}
