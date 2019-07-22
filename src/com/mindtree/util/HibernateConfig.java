package com.mindtree.util;

import org.hibernate.Session;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/*https://sourceforge.net/projects/hibernate/files/hibernate3/3.6.4.Final/

	Use above url to download the hibernate jars & download mysql-connector jar*/

public class HibernateConfig {

	private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

}
