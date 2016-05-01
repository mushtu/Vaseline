package ir.amv.os.vaseline.security.authentication.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Configuration
@EnableResourceServer
@PropertySource("classpath:openam.properties")
public class OAuth2ResourceConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	private Environment environment;

	private TokenExtractor tokenExtractor = new BearerTokenExtractor();

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.addFilterAfter(new OncePerRequestFilter() {
			@Override
			protected void doFilterInternal(HttpServletRequest request,
											HttpServletResponse response, FilterChain filterChain)
					throws ServletException, IOException {
				// We don't want to allow access to a resource with no token so clear
				// the security context in case it is actually an OAuth2Authentication
				if (tokenExtractor.extract(request) == null) {
					SecurityContextHolder.clearContext();
				}
				filterChain.doFilter(request, response);
			}
		}, AbstractPreAuthenticatedProcessingFilter.class);
		http.csrf().disable();
		http.authorizeRequests().anyRequest().authenticated();
	}


	
	@Bean
	public AccessTokenConverter accessTokenConverter() {
		return new DefaultAccessTokenConverter();
	}

	@Bean
	public OpenAmTokenService openAmTokenService()
	{
		OpenAmTokenService openAmTokenService = new OpenAmTokenService(environment.getProperty("openam.uri"));
		openAmTokenService.setTokenConverter(accessTokenConverter());
		return openAmTokenService;
	}
//
//	@Bean
//	public RemoteTokenServices remoteTokenServices(final @Value("${auth.server.url}") String checkTokenUrl,
//			final @Value("${auth.server.clientId}") String clientId,
//			final @Value("${auth.server.clientsecret}") String clientSecret) {
//		final RemoteTokenServices remoteTokenServices = new OpenAmTokenService();
//		remoteTokenServices.setCheckTokenEndpointUrl(checkTokenUrl);
//		remoteTokenServices.setClientId(clientId);
//		remoteTokenServices.setClientSecret(clientSecret);
//		remoteTokenServices.setAccessTokenConverter(accessTokenConverter());
//		return remoteTokenServices;
//	}
}