package ywcai.ls.oauth.filter;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ywcai.ls.oauth.entity.UserBase;
import ywcai.ls.oauth.service.UserBaseService;


@Service
@Qualifier(value="myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {

	@Autowired 
	UserBaseService userBaseService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		if(username==null)
		{
			throw new UsernameNotFoundException("username is not exist !");
		}
		if(username.equals(""))
		{
			throw new UsernameNotFoundException("username is not exist !");
		}
		UserBase userBase=userBaseService.getUserBase(username);
		if(userBase==null)
		{
			throw new UsernameNotFoundException("username is not exist !");
		}
		return userBaseService.getUserDetails(userBase);
	}
}
