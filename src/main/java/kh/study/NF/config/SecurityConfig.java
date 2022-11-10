package kh.study.NF.config;
//by 유빈
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration //컨피규레이션 객체 생성 어노테이션
@EnableWebSecurity //내부적으로 이 객체를 가져가 파일을 실행해주는 어노테이션
public class SecurityConfig {
	@Bean 
	public SecurityFilterChain filterChain(HttpSecurity security) throws Exception{
		security.csrf().disable()
						.authorizeRequests()
						.antMatchers("/**/**").permitAll()
						.anyRequest().authenticated()
					.and()
						.formLogin()
						.loginPage("/member/homeLogin")
						.defaultSuccessUrl("/board/list")
						.failureUrl("/member/loginFail")
						.loginProcessingUrl("/member/login")// 실제 로그인을 진행할 요청 정보
					.and()
						.logout()
						.invalidateHttpSession(true)
						.logoutSuccessUrl("/board/list")
					.and()
						.exceptionHandling()
						.accessDeniedPage("/member/accessDenied")
						;
		return security.build();
		
	}
	//------------------------- 스프링 암호화 -------------------------------------//
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//------css,js 요청 인증 무시 -------------------------------------------------//
	// css,js 파일 모두 열리면 인증을 받아야하기때문에 
	// /js,/css와 같은 파일 요청이 있으면 무시하도록 설정
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/js/**",  "/css/**");
	}
}


