package ywcai.ls.oauth.config;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import ywcai.ls.oauth.filter.StaticAuthenticationProvider;
import ywcai.ls.oauth.filter.MyUserDetailsService;
import ywcai.ls.oauth.filter.MyUsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {

	@Autowired
	StaticAuthenticationProvider staticAuthenticationProvider;
	@Autowired
	MyUserDetailsService myUserDetailsService;
	@Autowired
	DaoAuthenticationProvider myDaoProvider;
 

	public AuthenticationManager getProviderManage()
	{
		List<AuthenticationProvider> providers=new ArrayList<>();
		providers.add(myDaoProvider);
		providers.add(staticAuthenticationProvider);
		AuthenticationManager providerManager=new ProviderManager(providers);
		return providerManager;
	}
	@Bean(name="myDaoProvider")  
	public DaoAuthenticationProvider authenticationProvider() {  
		DaoAuthenticationProvider p2 = new DaoAuthenticationProvider();  
		p2.setUserDetailsService(myUserDetailsService); 
		//		authenticationProvider.setPasswordEncoder(passwordEncoder());  
		return p2; 
	}
	

	public MyUsernamePasswordAuthenticationFilter getMyFilter() {  
		MyUsernamePasswordAuthenticationFilter filter= new MyUsernamePasswordAuthenticationFilter();  
		filter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
		filter.setAuthenticationManager(getProviderManage());
		//		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return filter; 
	}


	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable();
		http.headers()
		.frameOptions().disable();
		http
		.authorizeRequests()
		.antMatchers("/user**","/oauth/check_token**")
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.formLogin()
		.loginProcessingUrl("login")
		.permitAll();
		http.addFilterBefore(getMyFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
