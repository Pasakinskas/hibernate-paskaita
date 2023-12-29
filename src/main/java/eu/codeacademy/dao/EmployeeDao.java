package eu.codeacademy.dao;

import java.util.List;
import java.util.Optional;

import eu.codeacademy.pojo.EmployeePojo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;

@RequiredArgsConstructor
public class EmployeeDao {

	@Getter
	private final Session session;

	// R - Read
	public List<EmployeePojo> getAll() {
		return session.createQuery("FROM EmployeePojo", EmployeePojo.class).list();
	}

	public Optional<EmployeePojo> getOneById(Long id) {
		return Optional.ofNullable(session.get(EmployeePojo.class, id));
	}

	// C - Create or U - Update
	public void persist(EmployeePojo employeePojo) {
		session.merge(employeePojo);
	}


	// D - Delete
	public void delete(EmployeePojo employeePojo) {
		session.remove(employeePojo);
	}

	public void deleteById(Long id) {
		getOneById(id).ifPresent(session::remove);
	}

}
