package com.example.Dao;

import javax.sql.DataSource;

import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration;
import org.springframework.boot.autoconfigure.h2.H2ConsoleProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class H2AutoConfig  extends H2ConsoleAutoConfiguration{
	
	  @Override 
	  public ServletRegistrationBean<WebServlet> h2Console(H2ConsoleProperties properties, ObjectProvider<DataSource>
	  dataSource) 
	  { 
		  properties.setPath("/naresh");
		  
	  return super.h2Console(properties, dataSource); 
	  }
	 

}
