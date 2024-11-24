
package miniproject.springboot.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import miniproject.springboot.service.AdminService;
import miniproject.springboot.model.Admin;
@Configuration
public class SecurityConfig {
	@SuppressWarnings("deprecation")
	@Bean
		public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
			http
				.authorizeHttpRequests(authorizeRequests -> authorizeRequests
					.requestMatchers("/signup", "/login").permitAll()
					.anyRequest().authenticated()
				)
				.formLogin(formLogin -> formLogin
					.loginPage("/login")
					.defaultSuccessUrl("/", true)
					.permitAll()
				)
				.logout(logout -> logout
					.logoutUrl("/logout")
					.logoutSuccessUrl("/login?logout")
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID")
					.permitAll()
				)
				.csrf(csrf -> csrf.disable());
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public UserDetailsService userDetailsService(AdminService adminService) {
			return email -> {
				Admin admin = adminService.findByEmail(email);
				if (admin != null) {
					return org.springframework.security.core.userdetails.User
					.withUsername(admin.getEmail())
					.password(admin.getPassword())
					.authorities("ROLE_USER")
					.build();
				} else {
				throw new UsernameNotFoundException("User not found");
				}
			};
	}
}
