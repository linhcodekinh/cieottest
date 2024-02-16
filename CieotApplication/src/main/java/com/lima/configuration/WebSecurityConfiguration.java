package com.lima.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.lima.jwt.JwtAuthenticationEntryPoint;
import com.lima.jwt.JwtFilter;
import com.lima.service.impl.AccountDetailServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private AccountDetailServiceImpl accountService;
	@Autowired
	private JwtFilter jwtFilter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(accountService);
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Don't need CSRF for this example
//		http.csrf().disable()
		http.cors().and().csrf().disable()
				// dont authenticate this particular request
				.authorizeRequests().antMatchers("/api/public/**").permitAll()
				// .authorizeRequests().antMatchers("/api/public/signup",
				// "/api/public/login").permitAll()
				// all other requests need to be authenticated
				.antMatchers("/api/user/**").hasAnyRole("USER", "ADMIN").antMatchers("api/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated().and()
				// make sure we use stateless session; session won't be used to
				// store user's state.
				.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// Add a filter to handle CORS
		http.addFilterBefore(new CustomCorsFilter(), ChannelProcessingFilter.class);
		// Add a filter to validate the tokens with every request
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		// Enable CORS and disable CSRF
//        http.cors().and().csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/api/public/**").permitAll()
//                .antMatchers("/api/user/**").hasAnyRole("USER", "ADMIN")
//                .antMatchers("/api/admin/**").hasRole("ADMIN")
//                .anyRequest().authenticated().and()
//                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// Add a filter to validate the tokens with every request
//        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

	}
}
