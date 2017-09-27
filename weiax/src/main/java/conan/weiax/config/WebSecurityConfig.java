package conan.weiax.config;

import conan.weiax.security.service.MyAuthenticationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by itw_hongww
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	private static Logger log = LoggerFactory
			.getLogger(WebSecurityConfig.class);
	@Autowired
	private MyAuthenticationProvider provider;// 自定义验证

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/weiax/login.html")
				.permitAll().antMatchers("/menu/**","/weiax/**","/weiaxC/**").hasAnyRole("ADMIN")
				.anyRequest()
				// .antMatchers("/user/**").hasAnyRole("USER,ADMIN").anyRequest()
				.authenticated().and().formLogin().loginPage("/weiax/login.html")
				// .defaultSuccessUrl("/hello")//登录成功后默认跳转到"/hello"
				.loginProcessingUrl("/weiaxC/login")
				//.successHandler(autherticationSuccesshandler())
				.defaultSuccessUrl("/weiaxC/loginsuccess")
				.failureUrl("/weiaxC/loginerror")
				.permitAll().and().logout().logoutUrl("/weiaxC/logout")
				.logoutSuccessUrl("/weiaxC/logoutsuccess")//退出登录后的默认url是"/home"
				.permitAll().and();
	}

	@Override
	public void configure(WebSecurity web) throws Exception { 
		web.ignoring().antMatchers("/weiax/src/js/**",
				"/weiax/src/css/**","/weiax/js/**",
				"/weiax/src/images/**", "/weiax/font-awesome/css/**",
				"/weiax/font-awesome/fonts/**","/weiax/font-awesome/less/**",
				"/weiax/font-awesome/scss/**",
				"/weiax/fonts/**",
				"/weiax/img/**");
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.authenticationProvider(provider);
	}
}