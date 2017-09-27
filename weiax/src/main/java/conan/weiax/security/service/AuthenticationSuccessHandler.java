package conan.weiax.security.service;

import com.alibaba.fastjson.JSONObject;
import conan.weiax.config.vo.ReturnInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationSuccessHandler extends
		SimpleUrlAuthenticationSuccessHandler {
	private static Logger log = LoggerFactory
			.getLogger(AuthenticationSuccessHandler.class);
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication auth)throws IOException,ServletException {
		String ajaxHeader = request.getHeader("X-Requested-With");
		boolean isAjax = "XMLHttpRequest".equals(ajaxHeader);
		if(isAjax){
			JSONObject info = new JSONObject();
			info.put("code", ReturnInfo.OK);
			response.getWriter().print(info);
			response.getWriter().flush();
		}else{
			super.onAuthenticationSuccess(request, response, auth);
		}
	}
}
