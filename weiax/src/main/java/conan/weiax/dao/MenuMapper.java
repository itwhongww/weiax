package conan.weiax.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.alibaba.fastjson.JSONObject;

public interface MenuMapper {
	@Select("SELECT menuId,menuName,content,imgPath FROM menu WHERE menuId = #{menuId}")
	JSONObject findMenuById(@Param("menuId") int menuId);
	@Select("SELECT menuId,menuName FROM menu")
	List<JSONObject> findMenus();

	@Insert("INSERT INTO menu(menuName,content,imgPath) VALUES(#{menuName},#{content},#{imgPath})")
	int insertMenu(JSONObject json);

	@Update("UPDATE menu SET menuName=#{menuName},content=#{content},imgPath=#{imgPath} WHERE menuId=#{menuId}")
	void updateMenu(JSONObject json);

	@Delete("DELETE FROM menu WHERE menuId=#{menuId}")
	void deleteMenuById(@Param("menuId") int menuId);
}
