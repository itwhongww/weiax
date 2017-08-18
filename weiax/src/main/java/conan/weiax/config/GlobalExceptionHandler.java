package conan.weiax.config;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import conan.weiax.config.vo.ErrorInfo;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ErrorInfo<String> defaultErrorHandler(HttpServletRequest req, Exception e){
		ErrorInfo<String> r = new ErrorInfo<String>(); 
		r.setMessage(e.getMessage());
		r.setCode(ErrorInfo.ERROR);
		r.setData("Some Data");
		r.setUrl(req.getRequestURL().toString());
		log.info("====================exception:",e);
		return r;
	}
}
