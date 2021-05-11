package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableAutoConfiguration(exclude = {H2ConsoleAutoConfiguration.class})
public class SpringBootDbApplication {

	public static void main(String[] args) {
		/* ConfigurableApplicationContext x = */ SpringApplication.run(SpringBootDbApplication.class, args);
		
		/*
		 * String[] allBeanNames = x.getBeanDefinitionNames();
		 * 
		 * for(String beanName : allBeanNames) { System.out.println(beanName); }
		 */
		
	}

}
