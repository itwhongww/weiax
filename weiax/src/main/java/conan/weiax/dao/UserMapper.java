package conan.weiax.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import conan.weiax.dto.User;

public interface UserMapper {
	@Select("SELECT userId,userName FROM USER WHERE userName = #{userName}")
	User findByName(@Param("userName") String userName);

	@Insert("INSERT INTO USER(userName) VALUES(#{username})")
	int insert(User user);

	@Update("UPDATE user SET userName=#{userName} WHERE userId=#{userId}")
	void updateById(User user);

	@Delete("DELETE FROM user WHERE userId=#{userId}")
	void deleteById(int id);
}
