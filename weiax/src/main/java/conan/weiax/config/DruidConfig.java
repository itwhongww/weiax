package conan.weiax.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
public class DruidConfig {

	@Bean
	public ServletRegistrationBean druidStatViewServlet(){
		ServletRegistrationBean srb = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
		
		srb.addInitParameter("allow", "127.0.0.1");
		srb.addInitParameter("deny", "192.168.1.73");
		srb.addInitParameter("loginUsername", "admin");
		srb.addInitParameter("loginPassword", "123456");
		srb.addInitParameter("resetEnable", "false");
		return srb;
	}
	
	@Bean
	public FilterRegistrationBean druidStatFilter(){
		FilterRegistrationBean frb = new FilterRegistrationBean(new WebStatFilter());
		
		frb.addUrlPatterns("/*");
		frb.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		return frb;
	}
}
