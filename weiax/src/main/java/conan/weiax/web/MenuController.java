package conan.weiax.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import conan.weiax.config.vo.ReturnInfo;
import conan.weiax.service.MenuService;

@Controller
public class MenuController {
	private static Logger log = LoggerFactory.getLogger(MenuController.class);

	@Autowired
	private MenuService menuService;
	@RequestMapping(value = "/menu/querymenus", method = RequestMethod.GET)
	@ResponseBody
	public ReturnInfo<JSONArray> querymenus() {
		ReturnInfo<JSONArray> info = new ReturnInfo<JSONArray>();
		List<JSONObject> list = menuService.findMenus();
		String jsonStr = JSONObject.toJSONString(list);
		JSONArray array = JSONObject.parseArray(jsonStr);
		info.setData(array);
		info.setCode(ReturnInfo.OK);
		info.setMessage("查询成功");
		return info;
	}
	@RequestMapping(value = "/menu/querymenubyid", method = RequestMethod.POST)
	@ResponseBody
	public ReturnInfo<JSONObject> querymenubyid(@RequestBody JSONObject jsonObject) {
		log.debug("===============enter into querymenubyid"+jsonObject);
		ReturnInfo<JSONObject> info = new ReturnInfo<JSONObject>();
		int id = jsonObject.getIntValue("id");
		JSONObject json = menuService.findMenuById(id);
		info.setData(json);
		info.setCode(ReturnInfo.OK);
		return info;
	}
}
