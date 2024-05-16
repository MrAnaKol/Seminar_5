package lv.venta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lv.venta.service.MyUserDetailsManagerServiceImpl;

@Configuration
@EnableWebSecurity
public class MySpringSecurityConfig {
	
	
	@Bean
	public MyUserDetailsManagerServiceImpl getService() {
		return new MyUserDetailsManagerServiceImpl();
	}
	
	@Bean
	public DaoAuthenticationProvider createAuthProvider() {
		PasswordEncoder encoder =
				PasswordEncoderFactories.createDelegatingPasswordEncoder();
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(encoder);
		provider.setUserDetailsService(getService());
		return provider;
	}
	
	
	
	@Bean
	public SecurityFilterChain configureEndpoints(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(auth->auth
				.requestMatchers("/hello").permitAll()
				.requestMatchers("/hello/msg").permitAll()
				.requestMatchers("/error").permitAll()
				.requestMatchers("/product/all").hasAnyAuthority("USER", "ADMIN")
				.requestMatchers("/product/one**").hasAnyAuthority("USER", "ADMIN")
				.requestMatchers("/product/all/**").hasAnyAuthority("USER", "ADMIN")
				.requestMatchers("/product/insert").hasAuthority("ADMIN")
				.requestMatchers("/product/update**").hasAuthority("ADMIN")
				.requestMatchers("/product/delete/**").hasAuthority("ADMIN")
				.requestMatchers("/product/filter/**").hasAnyAuthority("USER", "ADMIN")
				.requestMatchers("/h2-console/**").hasAuthority("ADMIN")
				);//.exceptionHandling(exp -> exp.accessDeniedPage("/access-denied"));
		//http.exceptionHandling(exp -> exp.accessDeniedPage("/access-denied")); // nosaka uz kuru url adresi japarlec
		http.csrf(csrf->csrf.disable());
		http.headers(frame->frame.frameOptions(option->option.disable()));
		
		http.formLogin(form -> form.permitAll());
		
		
		return http.build();
		
	}
	
	

}