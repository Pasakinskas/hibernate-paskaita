package eu.codeacademy;

import java.math.BigDecimal;
import java.util.List;

import eu.codeacademy.config.HibernateConfig;
import eu.codeacademy.config.HibernateDaoManagerHelper;
import eu.codeacademy.dao.EmployeeDao;
import eu.codeacademy.pojo.EmployeePojo;
import eu.codeacademy.pojo.ToolPojo;
import eu.codeacademy.service.EmployeeService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

public class App {

	public static void main(String[] args) {
		saveWithRelationshipExample();
	}

	public static void differentMethodExample() {
		var tool = new ToolPojo("Verzliaraktis", "naujutelis");
		var employee = new EmployeePojo("Marius", "Pasakinskas", BigDecimal.valueOf(400));
		employee.setTool(tool);

	}

	private static void saveWithRelationshipExample() {
		EmployeeService employeeService = new EmployeeService(new EmployeeDao(getSession()));
		employeeService.save("Ponas Suktukas is Kauno", BigDecimal.valueOf(1500));
	}

	private static void crudExample() {
		EmployeeService employeeService = new EmployeeService(new EmployeeDao(getSession()));
		System.out.println("--- Ger all employees");
		employeeService.getAll().forEach(System.out::println);

		EmployeePojo employeePojo = employeeService.getById(1L);
		System.out.printf("Employee by id %d is: %s", 1L, employeePojo);

		employeeService.save("Dalia Grybauskaite", BigDecimal.valueOf(6000));

		employeePojo = employeeService.getById(3L);
		employeePojo.setLastName("Grybayskaitė");
		employeeService.update(employeePojo);

		employeeService.deleteById(1L);
	}

	private static void hqlQueryExample() {
		try (Session session = getSession()) {
			Query<EmployeePojo> query = session.createQuery("FROM EmployeePojo", EmployeePojo.class);
			List<EmployeePojo> employees = query.list();

			employees.forEach(System.out::println);
		}
	}

	private static void nativeQueryExample() {
		try (Session session = getSession()) {
			NativeQuery<EmployeePojo> query = session.createNativeQuery("SELECT * FROM employee", EmployeePojo.class);
			List<EmployeePojo> employees = query.list();

			employees.forEach(System.out::println);
		}
	}

	private static Session getSession() {
		return HibernateDaoManagerHelper.getSessionFactory().openSession();
	}
}
