package conan.weiax.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
	
	@RequestMapping("/user")
	@ResponseBody
	public String user() {
		return "user";
	}
	@RequestMapping("/")
	public String index() {		
		return "/index.html";
	}
}
