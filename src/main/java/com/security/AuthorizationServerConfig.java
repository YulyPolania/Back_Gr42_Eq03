package com.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.service.UsuarioService;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private AdditionalnfoToken additionalnfoToken;

	// configuracón ruta de acceso publica para solicitud de autenticación
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()")
				.checkTokenAccess("isAuthenticated()");
	}

	// configuración clientes se deben registrar uno por uno
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
				.withClient("webapp")
				.secret(bCryptPasswordEncoder.encode("123"))
				.scopes("read", "write")
				.authorizedGrantTypes("password", "refresh_token")// password sirve para usuario y contraseña - refresh token
																													// sirve para acceso renovado sin usar sesión - authetication
																													// code sirve como una llave -
				.accessTokenValiditySeconds(1 * 60)// duración tiempo de expiraiòn en segundos del primer token
				.refreshTokenValiditySeconds(2 * 60); // duración tiempo de expiraiòn token de refresco en segundos del primer
																							// token
	}

	// autenticaciòn y validaciòn de token
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain tokenEnhacerChain = new TokenEnhancerChain();
		tokenEnhacerChain.setTokenEnhancers(Arrays.asList(additionalnfoToken, accessTokenConverter()));
		endpoints.authenticationManager(authenticationManager)
				.tokenStore(tokenStore())
				.accessTokenConverter(accessTokenConverter())// añadir informaciòn al token (no info confidencial se puede decodificar
				.userDetailsService((UserDetailsService) usuarioService)
				.tokenEnhancer(tokenEnhacerChain);
	}

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	// còdifica y decódifica
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter jwtAccesTokenConverter = new JwtAccessTokenConverter();
		jwtAccesTokenConverter.setSigningKey(JwtConfig.RSA_PRIVADA);
		jwtAccesTokenConverter.setVerifierKey(JwtConfig.RSA_PUBLICA);
		return jwtAccesTokenConverter;
	}

}