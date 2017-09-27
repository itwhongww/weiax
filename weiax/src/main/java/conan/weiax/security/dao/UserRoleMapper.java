package conan.weiax.security.dao;

import conan.weiax.security.dto.UserRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserRoleMapper {
	@Select("SELECT userRoleId,userId,roleId,description FROM userRole WHERE userId = #{userId}")
	UserRole findByUserId(@Param("userId") Long userId);
	
	@Insert("INSERT INTO userRole(userRoleId,userId,roleId,description) VALUES(#{userRoleId},#{userId},#{roleId},#{description})")
	int insert(UserRole userRole);
	
	@Update("UPDATE userRole SET userId=#{userId},roleId=#{roleId},description=#{description} WHERE userRoleId=#{userRoleId}")
	void update(UserRole userRole);
	
	@Delete("DELETE FROM userRole WHERE userRoleId=#{userRoleId}")
	void delete(Long id);
	
	@Results({
		@Result(property = "userRoleId", column = "userRoleId"),
		@Result(property = "userId", column = "userId"),
		@Result(property = "roleId", column = "roleId"),
		@Result(property = "description", column = "description")
	})
	@Select("SELECT userRoleId,userId,roleId,description FROM userRole")
	List<UserRole> findAll();
}
