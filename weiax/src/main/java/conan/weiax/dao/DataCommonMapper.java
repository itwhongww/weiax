package conan.weiax.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface DataCommonMapper {
	@Select("SELECT ${dbResult} FROM ${tableName} WHERE ${dbWhere}")
	JSONObject findCommonData(JSONObject json);
	@Select("SELECT ${dbResult} FROM ${tableName} WHERE ${dbWhere}")
	List<JSONObject> findCommonDatas(JSONObject json);
	@Insert("INSERT INTO ${tableName} (${dbWhere}) VALUES(${dbResult})")
	int insertData(JSONObject json);
	@Update("UPDATE ${tableName} SET ${dbResult} WHERE ${dbWhere}")
	void updateData(JSONObject json);
	@Delete("DELETE FROM ${tableName} WHERE ${dbWhere}")
	void deleteData(JSONObject json);
}
