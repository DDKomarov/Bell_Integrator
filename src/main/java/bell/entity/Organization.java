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
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String name;

    @NotNull
    @Column(name = "full_name")
    private String fullName;

    @NotNull
    private int inn;

    @NotNull
    private int kpp;

    @NotNull
    private String address;

    @NotNull
    @Pattern(regexp = "-?\\d+(\\.\\d+)?")
    private String phone;

    @NotNull
    private boolean active;

    @NotNull
    @OneToMany(mappedBy = "organizationId", cascade = CascadeType.ALL)
    private List<Office> offices;

}
