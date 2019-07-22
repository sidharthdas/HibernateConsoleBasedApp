package com.mindtree.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConfig {

	private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

}
