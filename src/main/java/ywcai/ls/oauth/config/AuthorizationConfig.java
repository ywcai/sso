package ywcai.ls.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private TokenStore tokenStore;
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("client")
		//		.secret("111111")
		//		.resourceIds("oauth")
		.authorizedGrantTypes("authorization_code", "refresh_token")//设置验证方式
		//		.redirectUris("http://localhost/callback")//后期回调地址可动态填写
		.scopes("all")
//		.resourceIds("productinf")
		.autoApprove(true);//默认登录后可直接授权
	}

//		@Override
//		public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
//			//允许表单认证
//			oauthServer
//			.realm("oauth")
//			.tokenKeyAccess("permitAll()")
//			.checkTokenAccess("permitAll()");
//		}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore);
	}

	@Bean
	public TokenStore tokenStore()
	{
		return new InMemoryTokenStore();
	}
}
