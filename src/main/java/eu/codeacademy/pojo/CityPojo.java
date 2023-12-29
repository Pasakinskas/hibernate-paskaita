package eu.codeacademy.pojo;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "city")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CityPojo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
    name = "user_cities",
    joinColumns = { @JoinColumn(referencedColumnName = "id") },
    inverseJoinColumns = { @JoinColumn( referencedColumnName = "id") }
  )
  private List<EmployeePojo> employees = new ArrayList<>();
}
