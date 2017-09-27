package conan.weiax.love_kitchen.web;

import com.alibaba.fastjson.JSONObject;
import conan.weiax.config.vo.ReturnInfo;
import conan.weiax.love_kitchen.service.LoveKitchenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class LoveKitchenController {
    private static Logger log = LoggerFactory.getLogger(LoveKitchenController.class);
    @Autowired
    private LoveKitchenService loveKitchenService;
    @RequestMapping(value = "/love_kitchenC/menulist", method = RequestMethod.POST)
    @ResponseBody
    public ReturnInfo<List> menulist(@RequestBody JSONObject json) {
        ReturnInfo<List> info = new ReturnInfo<List>();
        List<JSONObject> list = loveKitchenService.showAllMenu(json);
        info.setData(list);
        info.setCode(ReturnInfo.OK);
        return info;
    }
    @RequestMapping(value = "/love_kitchenC/menudetail", method = RequestMethod.POST)
    @ResponseBody
    public ReturnInfo<JSONObject> menudetail(@RequestBody JSONObject json) {
        ReturnInfo<JSONObject> info = new ReturnInfo<JSONObject>();
        JSONObject returnJson = loveKitchenService.showMenuDetail(json);
        info.setData(returnJson);
        info.setCode(ReturnInfo.OK);
        return info;
    }
    @RequestMapping(value = "/love_kitchenC/menuclient", method = RequestMethod.POST)
    @ResponseBody
    public ReturnInfo<List> menuclient(@RequestBody JSONObject json) {
        ReturnInfo<List> info = new ReturnInfo<List>();
        List<JSONObject> list = loveKitchenService.showMenuClient(json);
        info.setData(list);
        info.setCode(ReturnInfo.OK);
        return info;
    }
}
