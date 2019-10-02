package com.spring;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@SpringBootApplication
public class CashregisterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CashregisterApplication.class, args);
	}
    
    @Bean	//Spring Boot будет искать файлы сообщений, содержащие ключи и значения интернационализации в папке src / main / resources
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/i18n/contentBundle");
        //messageSource.setDefaultEncoding("UTF-8");
        //application.properties: spring.messages.basename = messages, com.foo.wherever.i18n
        return messageSource;
    }
}
