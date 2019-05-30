package com.garage.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SingletonHiberUtil {

	public static Session session;

	private SingletonHiberUtil() {

	}

	public static Session getSession() {
		SessionFactory sFactory = getSessionFactory();
		if (session == null) {
			session = sFactory.openSession();
		} else {
			session = sFactory.getCurrentSession();
		}
		return session;
	}
 
	private static SessionFactory getSessionFactory() {
		SessionFactory sessionFactory = null;
		StandardServiceRegistry registry = null;
		if (sessionFactory == null) {

			try {
  
				
				// Create registry
				registry = new StandardServiceRegistryBuilder().configure().build();

				// Create MetadataSources
				MetadataSources sources = new MetadataSources(registry);

				// Create Metadata
				Metadata metadata = sources.getMetadataBuilder().build();

				// Create SessionFactory
				sessionFactory = metadata.getSessionFactoryBuilder().build();

			} catch (Exception e) {

				e.printStackTrace();

				if (registry != null) {

					StandardServiceRegistryBuilder.destroy(registry);

				}
			}
		}
		return sessionFactory;
	}
}
