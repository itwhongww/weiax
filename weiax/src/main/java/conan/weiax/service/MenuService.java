package conan.weiax.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import conan.weiax.config.annotation.Read;
import conan.weiax.config.annotation.Write;
import conan.weiax.dao.MenuMapper;
@Controller
public class MenuService {
	@Autowired
	private MenuMapper menuMapper;
	
	@Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
	@Write
	public void saveMenu(JSONObject json){
		menuMapper.insertMenu(json);
	}
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	@Read
	public JSONObject findMenuById(int menuId){
		JSONObject json = menuMapper.findMenuById(menuId);
		
		//处理imgPath
		JSONArray array = new JSONArray();
		String imgPath = json.getString("imgPath");
		String[] paths = imgPath.split(",");
		for(String path:paths){
			array.add(path);
		}
		json.put("imgPath", array);
		return menuMapper.findMenuById(menuId);
	}
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	@Read
	public List<JSONObject> findMenus(){
		return menuMapper.findMenus();
	}
	@Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
	@Write
	public void updateMenu(JSONObject json){
		menuMapper.updateMenu(json);
	}
}
