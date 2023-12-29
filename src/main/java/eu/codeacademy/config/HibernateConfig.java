package eu.codeacademy.config;

import eu.codeacademy.pojo.CityPojo;
import eu.codeacademy.pojo.EmployeePojo;
import eu.codeacademy.pojo.TaskPojo;
import eu.codeacademy.pojo.ToolPojo;
import org.hibernate.cfg.Configuration;

public class HibernateConfig {

	public static Configuration initConfiguration() {
		Configuration configuration = new Configuration();
		configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
		configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/playground");
		configuration.setProperty("hibernate.connection.username", "root");
		configuration.setProperty("hibernate.connection.password", "root_password");
		configuration.setProperty("hibernate.connection.autocommit", "true");
		configuration.setProperty("hibernate.show_sql", "true");
		configuration.setProperty("hibernate.hbm2ddl.auto", "update");

		configuration.addAnnotatedClass(EmployeePojo.class);
		configuration.addAnnotatedClass(ToolPojo.class);
		configuration.addAnnotatedClass(TaskPojo.class);
		configuration.addAnnotatedClass(CityPojo.class);

		return configuration;
	}
}
