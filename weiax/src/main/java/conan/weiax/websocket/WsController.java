package conan.weiax.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import conan.weiax.config.vo.ReturnInfo;
import conan.weiax.exception.GlobalException;
import conan.weiax.util.OkHttp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class WsController {
    private static Logger log = LoggerFactory.getLogger(WsController.class);
    private final String FLAG_CANCELUSER = "FLAG_CANCELUSER";
    private final String FLAG_CONTENT = "FLAG_CONTENT";
    private final String FLAG_FRESHUSER = "FLAG_FRESHUSER";
    private final String WAX_WECHAT_ONLINE = "WAX_WECHAT_ONLINE";
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public ReturnInfo<JSONObject> say(@RequestBody JSONObject json) {
        System.out.println("==================json"+json);
        ReturnInfo<JSONObject> info = new ReturnInfo<>();
        String name = json.getString("name");
        String flag = json.getString("flag");
        if(FLAG_CANCELUSER.equals(flag)){
            this.cancelUser(name);
        }else if(FLAG_FRESHUSER.equals(flag)){
            List onlineList = this.freshUser();
            json.put("onlineList",onlineList);
        }
        info.setCode(ReturnInfo.OK);
        info.setData(json);
        return info;
    }
    @RequestMapping(value = "/wechat/initChater", method = RequestMethod.GET)
    @ResponseBody
    public ReturnInfo<JSONObject> initChater() {
        ReturnInfo<JSONObject> info = new ReturnInfo<>();
        try {
            String myself = OkHttp.getInstance().doGet("http://127.0.0.1/wax_measure/tourist");
            boolean flag = stringRedisTemplate.opsForHash().putIfAbsent(WAX_WECHAT_ONLINE,myself,"1");
            if(!flag){
                throw new GlobalException("网络异常，请稍后重试");
            }

            JSONObject returnJSON = new JSONObject();
            returnJSON.put("myself",myself);
            info.setData(returnJSON);
            info.setCode(ReturnInfo.OK);
        } catch (IOException e) {
            info.setCode(ReturnInfo.ERROR);
            info.setMessage(e.getMessage());
        }
        return info;
    }
    private void cancelUser(String name){
        stringRedisTemplate.opsForHash().delete(WAX_WECHAT_ONLINE,name);
    }
    private List freshUser(){
        Map map = stringRedisTemplate.opsForHash().entries(WAX_WECHAT_ONLINE);
        JSONObject json = JSON.parseObject(JSON.toJSONString(map));
        List returnList = new ArrayList();
        returnList.addAll(json.keySet());
        Collections.sort(returnList);
        return returnList;
    }
}
