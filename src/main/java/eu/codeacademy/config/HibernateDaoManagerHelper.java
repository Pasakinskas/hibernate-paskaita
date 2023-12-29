package eu.codeacademy.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateDaoManagerHelper {

	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			sessionFactory = HibernateConfig.initConfiguration().buildSessionFactory();
		}

		return sessionFactory;
	}
}
