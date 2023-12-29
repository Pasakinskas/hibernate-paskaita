package eu.codeacademy.pojo;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "tool")
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Table(name = "tool")
@AllArgsConstructor
public class ToolPojo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(length = 50, nullable = false)
  private String name;

  @Column(length = 50, nullable = false)
  private String status;

  @OneToOne(cascade = CascadeType.ALL, mappedBy = "tool")
  @ToString.Exclude
  private EmployeePojo employee;

  public ToolPojo(String name, String status) {
    this.name = name;
    this.status = status;
  }
}
