package conan.weiax.security.web;

import conan.weiax.config.vo.ReturnInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SecurityController {
    private static Logger log = LoggerFactory.getLogger(SecurityController.class);

    @RequestMapping(value = "/weiaxC/loginsuccess", method = RequestMethod.GET)
    @ResponseBody
    public ReturnInfo loginsuccess() {
        log.debug("===============loginsuccess");
        ReturnInfo info = new ReturnInfo();
        info.setCode(ReturnInfo.OK);

        return info;
    }
    @RequestMapping(value = "/weiaxC/loginerror", method = RequestMethod.GET)
    @ResponseBody
    public ReturnInfo loginerror() {
        log.debug("===============loginerror");
        ReturnInfo info = new ReturnInfo();
        info.setCode(ReturnInfo.ERROR);
        info.setMessage("用户名或密码有误");
        return info;
    }
    @RequestMapping(value = "/weiaxC/logout", method = RequestMethod.GET)
    @ResponseBody
    public ReturnInfo logout() {
        log.debug("===============loginerror");
        ReturnInfo info = new ReturnInfo();
        info.setCode(ReturnInfo.OK);
        return info;
    }
    @RequestMapping(value = "/weiaxC/logoutsuccess", method = RequestMethod.GET)
    @ResponseBody
    public ReturnInfo logoutsuccess() {
        log.debug("===============logoutsuccess");
        ReturnInfo info = new ReturnInfo();
        info.setCode(ReturnInfo.OK);
        return info;
    }
}
