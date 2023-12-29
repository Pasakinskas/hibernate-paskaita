package eu.codeacademy.pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "task")
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TaskPojo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String taskName;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "employee_id")
  EmployeePojo employee;
}
