package ywcai.ls.oauth.service;



import java.util.Collection;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import ywcai.ls.oauth.bean.SimpleUser;
import ywcai.ls.oauth.entity.Roles;
import ywcai.ls.oauth.entity.UserBase;
import ywcai.ls.oauth.repository.UserBaseRepository;


@Service
@Slf4j
public class UserBaseService {
	@Autowired
	UserBaseRepository userBaseRepository;
	public UserBase getUserBase(String username) {
		// TODO Auto-generated method stub
		UserBase userBase=userBaseRepository.findTop1ByUsername(username);
		log.warn("find the username = {} ",userBase);
		return userBase;
	}

	public UserDetails getUserDetails(UserBase userBase) {
		// TODO Auto-generated method stub
		Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		for(Roles role : userBase.getRolelist())
		{
			authorities.add(new SimpleGrantedAuthority(role.getRolename()));//获取oauth资源权限
		}
		SimpleUser simpleUser=new SimpleUser(userBase.getUsername(),userBase.getPassword(),authorities);
		return simpleUser;
	}
	public UserDetails getDefaultUserDetails() {
		// TODO Auto-generated method stub
		log.warn("use the default account login  username = ywcai ");
		Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("admin"));//获取oauth资源权限
		SimpleUser simpleUser=new SimpleUser("ywcai","Ywcai1985118",authorities);
		return simpleUser;
	}
}
