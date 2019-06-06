package com.garage.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;

public class HibernateApplicationContextListener extends ContextLoaderListener implements ServletContextListener {
	
	@Autowired
	ApplicationContext ctx;
	
	public void contextInitialized(ServletContextEvent event) {
		SingletonHiberUtil.getSession();
	}

	public void contextDestroyed(ServletContextEvent event) {
		SingletonHiberUtil.shutDown(); 
	}
}  