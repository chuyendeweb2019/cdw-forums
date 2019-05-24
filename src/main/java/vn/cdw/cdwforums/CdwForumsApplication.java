package vn.cdw.cdwforums;


import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
@ComponentScan({ "vn.cdw.cdwforums.config", "vn.cdw.cdwforums.controller", "vn.cdw.cdwforums.controller"
	, "vn.cdw.cdwforums.controller.form", "vn.cdw.cdwforums.controller.validator", "vn.cdw.cdwforums.entity"
	, "vn.cdw.cdwforums.reponsitory", "vn.cdw.cdwforums.service", "vn.cdw.cdwforums.util"})

public class CdwForumsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CdwForumsApplication.class, args);
	}

	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.ENGLISH);
        return sessionLocaleResolver;
    }

    @Bean(name = "commonsMultipartResolver")
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }


   
	
}
