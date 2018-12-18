package bell.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.util.List;


@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String address;

    @NotNull
    @Pattern(regexp = "-?\\d+(\\.\\d+)?")
    private String phone;

    @NotNull
    private boolean active;

    @NotNull
    @OneToMany(mappedBy = "officeId", cascade = CascadeType.ALL)
    private List<Employee> employeeList;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "org_id")
    private Organization organization;
}
