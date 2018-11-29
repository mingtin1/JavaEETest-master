package org.sang.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * 配置OAuth2验证服务器
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter
        implements EnvironmentAware {

    private Logger logger = LoggerFactory.getLogger(AuthorizationServerConfiguration.class);

    private static final String ENV_OAUTH = "authentication.oauth.";
    private static final String PROP_CLIENTID = "clientid";
    private static final String PROP_SECRET = "secret";
    private static final String PROP_TOKEN_VALIDITY_SECONDS = "tokenValidityInSeconds";

    private RelaxedPropertyResolver propertyResolver;


    @Bean
    public TokenStore tokenStore() {
        //使用内存的tokenStore
        return new InMemoryTokenStore();
    }

    @Autowired
    @Qualifier("authenticationManagerBean")//认证方式
    private AuthenticationManager authenticationManager;


    /**
     * 之后注入到认证转换器配置中调用
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore())
                .authenticationManager(authenticationManager);
    }

    /**
     * //1.配置客户端认证方式以及客户端连接参数设置：
     * //该配置为SSO客户端配置所需的client-id: acme;client-secret: acmesecret
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory() // 使用in-memory存储
                .withClient(propertyResolver.getProperty(PROP_CLIENTID))//client_id用来标识客户的Id
                .scopes("read", "write") //允许授权范围
                .authorities("ROLE_ADMIN", "ROLE_USER")//客户端可以使用的权限
                .authorizedGrantTypes("password", "refresh_token")//允许授权类型
                .secret(propertyResolver.getProperty(PROP_SECRET))//secret客户端安全码
                .accessTokenValiditySeconds(propertyResolver.getProperty(PROP_TOKEN_VALIDITY_SECONDS, Integer.class, 1800));
    }

    @Override
    public void setEnvironment(Environment environment) {
        //获取到前缀是"authentication.oauth." 的属性列表值.
        this.propertyResolver = new RelaxedPropertyResolver(environment, ENV_OAUTH);
    }
}
