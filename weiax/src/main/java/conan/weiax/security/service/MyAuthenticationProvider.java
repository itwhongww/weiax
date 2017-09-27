package conan.weiax.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
	private static Logger log = LoggerFactory
			.getLogger(MyAuthenticationProvider.class);
    @Autowired
    private MyUserDetailsService userService;

    /**
     * 自定义验证方式
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.debug("=============MyAuthenticationProvider");
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        log.debug("=============username:"+username);
        log.debug("=============password:"+password);
        UserDetails user =  userService.loadUserByUsername(username);

        if(user == null){
            throw new BadCredentialsException("Username not found.");
        }

        //加密过程在这里体现
        //password = MD5Util.encode(password);
        if (!password.equals(user.getPassword())) {
            throw new BadCredentialsException("Wrong password.");
        }
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }

}
