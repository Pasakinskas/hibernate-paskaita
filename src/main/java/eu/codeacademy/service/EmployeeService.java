package eu.codeacademy.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import eu.codeacademy.dao.EmployeeDao;
import eu.codeacademy.exception.DataNotFoundException;
import eu.codeacademy.pojo.CityPojo;
import eu.codeacademy.pojo.EmployeePojo;
import eu.codeacademy.pojo.TaskPojo;
import eu.codeacademy.pojo.ToolPojo;
import lombok.RequiredArgsConstructor;
import org.hibernate.Transaction;

@RequiredArgsConstructor
public class EmployeeService {

	private final EmployeeDao employeeDao;

	public List<EmployeePojo> getAll() {
		return employeeDao.getAll();
	}

	public EmployeePojo getById(long id) {
		return employeeDao
				.getOneById(id)
				.orElseThrow(DataNotFoundException::new);
	}

	public void save(String fullName, BigDecimal salary) {
		Transaction transaction = employeeDao.getSession().beginTransaction();
		try {
			ToolPojo tool = ToolPojo
				.builder()
				.name("plaktukas")
				.status("sugedes")
				.build();

			EmployeePojo employee = EmployeePojo.builder()
				.firstName(fullName.split(" ")[0])
				.lastName(fullName.split(" ")[1])
				.salary(salary)
				.tool(tool)
				.build();

			List<TaskPojo> tasks = new ArrayList<>();

			tasks.add(TaskPojo.builder().taskName("surinkti ikea baldus").employee(employee).build());
			tasks.add(TaskPojo.builder().taskName("priverzti kondiske prie sienos").employee(employee).build());



			List<CityPojo> cities = new ArrayList<>();

			CityPojo city1 = CityPojo.builder().name("Kaunas").employees(List.of(employee)).build();
			CityPojo city2 = CityPojo.builder().name("Vilnius").employees(List.of(employee)).build();

			employee.setTasks(tasks);

			cities.add(city1);
			cities.add(city2);

			employee.setTasks(tasks);
			employee.setCities(cities);
			employeeDao.persist(employee);
			transaction.commit();

		} catch (Exception e) {
			System.out.println(e);
			transaction.rollback();
		}
	}

	public void update(EmployeePojo employeePojo) {
		Transaction transaction = employeeDao.getSession().beginTransaction();
		try {
			employeeDao.persist(employeePojo);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}
	}

	public void deleteById(long id) {
		Transaction transaction = employeeDao.getSession().beginTransaction();
		try {
			employeeDao.deleteById(id);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}
	}
}
