package couponSystem.phase4.spring.boot.phase4springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import couponSystem.phase4.spring.boot.phase4springboot.filter.LoginFilter;

@SpringBootApplication
public class Phase4springBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(Phase4springBootApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<LoginFilter> loginFilter() {
		FilterRegistrationBean<LoginFilter> registrationBean = new FilterRegistrationBean<>();

		registrationBean.setFilter(new LoginFilter());
		registrationBean.addUrlPatterns("/AdminController/*");
		registrationBean.addUrlPatterns("/CustomerController/*");
		registrationBean.addUrlPatterns("/CompanyController/*");

		return registrationBean;
	}
}
