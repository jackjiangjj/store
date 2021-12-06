package com.jw.store.auth.security;


import com.jw.store.auth.userDetailsServiceImpl.userDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Arrays;

@Configuration
//配置Oauth2的核心配置类，spring Oauth2在需要时会调用这个配置类中的方法
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private userDetailsServiceImpl userDetailsService;

    //配置授权服务器端点信息
    // 主要是当用户访问到/oauth/token时我们的操作
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer
                                  endpoints) throws Exception {
        //配置SpringSecurity中获得的认证管理器
        endpoints.authenticationManager(authenticationManager)
                //配置获得用户详情的方法
                .userDetailsService(userDetailsService)
                //为了安全,限制登录提交的方式只能是Post
                .allowedTokenEndpointRequestMethods(HttpMethod.POST)
                //配置令牌生成器
                .tokenServices(tokenService());
    }
    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;

    //配置令牌生成
    @Bean
    public AuthorizationServerTokenServices tokenService(){
        DefaultTokenServices services=new DefaultTokenServices();
        //支持令牌刷新策略
        services.setSupportRefreshToken(true);
        //设置令牌生成策略
        services.setTokenStore(tokenStore);
        //设置令牌增强对象(Jwt令牌固定配置)
        TokenEnhancerChain chain=new TokenEnhancerChain();
        //这个对象可以添加很多令牌转换器
        // 我们现在将Jwt令牌转换器添加到这个对象中
        chain.setTokenEnhancers(
                Arrays.asList(accessTokenConverter));
        //设置chain到当前生成令牌的配置对象
        services.setTokenEnhancer(chain);
        //设置令牌有效期(3600秒,一小时有效)
        services.setAccessTokenValiditySeconds(3600);
        //设置令牌刷新最大时间
        services.setRefreshTokenValiditySeconds(3600);
        //配置客户端详情
        services.setClientDetailsService(clientDetailsService);
        //千万别忘了返回
        return services;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;
    //配置客户端详情(规定客户端权限)
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                //定义客户端id
                .withClient("knows")
                //客户端秘钥
                .secret(passwordEncoder.encode(
                        "779"))
                //授予客户端权限:all只是个名字而已,就像ROLE_STUDENT
                .scopes("all")
                //当前客户端支持的Oauth2操作
                //这里面的字符串是有意义的,不能拼错
                //password表示Oauth2支持用户名密码登录
                //refresh_token表示Oauth2支持刷新令牌
                .authorizedGrantTypes("password","refresh_token");
    }
    //认证成功的安全策略设置(登录成功后允许在资源服务器中做的事情)
    @Override
    public void configure(AuthorizationServerSecurityConfigurer
                                  security) throws Exception {
        security
                //允许任何人访问/oauth/token_key端点
                .tokenKeyAccess("permitAll()")
                //允许任何人访问/oauth/check_token端点
                .checkTokenAccess("permitAll()")
                //允许客户端进行表单权限认证(如果登录成功赋予令牌)
                .allowFormAuthenticationForClients();
    }
}
