package py.edu.fpune.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import py.edu.fpune.rest.ExceptionResource;
import py.edu.fpune.rest.FuncionarioController;
import py.edu.fpune.security.filter.MyFilter;
import py.edu.fpune.security.filter.TimeAccessFilter;
import py.edu.fpune.service.UsuarioService;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)//siguiendo lo sugerido por MIW
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private BCryptPasswordEncoder bCrypt;
	
	@Autowired
	private UsuarioService userDetailService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService).passwordEncoder(bCrypt);
		//usuario Estatico
/*		auth.inMemoryAuthentication()
			.withUser("user").password("123456").roles("USER").and()
			.withUser("manager").password("123456").roles("MANAGER").and()
			.withUser("admin").password("12345").roles("ADMIN","MANAGER","USER");
*/
	}

	//obligamos a que toda peticion se autentique
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//primera forma básica
		/*http.authorizeRequests().anyRequest()
			.authenticated()
			.and()
			.httpBasic();*/
		http.csrf().disable().httpBasic()
			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().addFilter(jwtAuthorizationFilter());
	}
	@Bean
	public JwtAuthorizationFilter jwtAuthorizationFilter() throws Exception {
		return new JwtAuthorizationFilter(this.authenticationManager());
	}
	
	@Bean
	public FilterRegistrationBean<MyFilter> myFilter(){
		FilterRegistrationBean<MyFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new MyFilter());
		filterRegistrationBean.addUrlPatterns(FuncionarioController.FUNCTIONARY+FuncionarioController.ALL);
		return filterRegistrationBean;
	}
	
	@Bean
	public FilterRegistrationBean<TimeAccessFilter> timeAccesFilter(){
		FilterRegistrationBean<TimeAccessFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new TimeAccessFilter());
		filterRegistrationBean.addUrlPatterns(ExceptionResource.EXCEPTIONS +ExceptionResource.OUT_OF_TIME);
		filterRegistrationBean.setOrder(Ordered.LOWEST_PRECEDENCE -1);
		return filterRegistrationBean;
	}
	
	//La instacia de este bean la manejara Spring
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
}
