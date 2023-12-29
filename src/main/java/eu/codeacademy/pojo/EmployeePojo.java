package eu.codeacademy.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "employee")
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Table(name = "employee")
@AllArgsConstructor
public class EmployeePojo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name", length = 30, nullable = false)
	private String firstName;

	@Column(name = "last_name", length = 50, nullable = false)
	private String lastName;

	@Column(precision = 20, scale = 2, nullable = false)
	private BigDecimal salary;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tool_id")
	private ToolPojo tool;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	private List<TaskPojo> tasks;

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "employees")
	private List<CityPojo> cities = new ArrayList<>();

	public EmployeePojo(String firstName, String lastName, BigDecimal salary) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;
	}
}
