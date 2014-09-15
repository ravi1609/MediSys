package com.medisys.ravi.utility;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateSessionFactoryProvider 
{

	private static final SessionFactory sessionFactory;
	static {
	try {
		Configuration configuration=new Configuration().configure();
		StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
		serviceRegistryBuilder.applySettings(configuration.getProperties());
		ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
		sessionFactory=configuration.buildSessionFactory(serviceRegistry);
	} catch (Throwable ex) {
	System.err.println("Initial SessionFactory creation failed." + ex);
	throw new ExceptionInInitializerError(ex);
	}
	}
	public static SessionFactory getSessionFactory() {
	return sessionFactory;
	
	}

	
	
	
	
	
}
