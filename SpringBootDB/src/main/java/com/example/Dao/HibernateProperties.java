package com.example.Dao;

import java.util.Map;

import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Configuration;
@Configuration
public class HibernateProperties implements HibernatePropertiesCustomizer {

	@Override
	public void customize(Map<String, Object> hibernateProperties) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * @Override public void customize(Map<String, Object> hibernateProperties) {
	 * hibernateProperties.put("spring.jpa.show-sql", "false");
	 * HibernatePropertiesCustomizer.super.customize(hibernateProperties); }
	 */
}
