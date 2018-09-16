package ywcai.ls.oauth.config;


import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ywcai.ls.oauth.filter.MyUserDetailsService;

@Controller
public class CoreController {
	@Autowired
	MyUserDetailsService myUserDetailsService;
	@Autowired
	private TokenStore tokenStore;
	@RequestMapping("/user")
	@ResponseBody
	public Map<String, Object> user(@RequestHeader(defaultValue ="null null") String authorization) {
		Map<String, Object> map = new HashMap<>();
		OAuth2Authentication authen=null;
		try
		{
			authen=tokenStore.readAuthentication(authorization.split(" ")[1]);
			if(authen==null)
			{
				map.put("error", "invalid token !");
				return map;
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
			map.put("error", e);
			return map;
		}
		//注意这两个key都不能随便填，都是和客户端进行数据处理时进行对应的。
		map.put("user", authen.getPrincipal());
		map.put("authorities", authen.getAuthorities());
		return map;
	}
	@RequestMapping("/hello")
	@ResponseBody
	public String hello( ) { 
		return "hello world";
	}
	@RequestMapping("/index")
	public String test( ) { 
		return "index";
	}
}
