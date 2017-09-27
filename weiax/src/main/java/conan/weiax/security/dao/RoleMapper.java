package conan.weiax.security.dao;

import conan.weiax.security.dto.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoleMapper {
	@Select("SELECT r.roleId,roleName,r.description FROM role r , userrole u WHERE u.userId = #{userId} and u.roleId = r.roleId")
	Role findRoleByUserId(@Param("userId") long userId);
	
	@Insert("INSERT INTO role(roleId,roleName,description) VALUES(#{roleId},#{roleName},#{description})")
	int insert(Role role);
	
	@Update("UPDATE role SET roleName=#{roleName},description=#{description} WHERE roleId=#{roleId}")
	void update(Role role);
	
	@Delete("DELETE FROM role WHERE roleId=#{roleId}")
	void delete(Long id);
	
	@Results({
		@Result(property = "roleId", column = "roleId"),
		@Result(property = "roleName", column = "roleName"),
		@Result(property = "description", column = "description")
	})
	@Select("SELECT roleId,roleName,description FROM role")
	List<Role> findAll();
}
