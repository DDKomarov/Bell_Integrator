package bell.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "code")
@NoArgsConstructor
public class Countries {
    @Id
    private int code;

    @NotNull
    private String name;

    @NotNull
    @OneToOne(mappedBy = "citizenshipId" , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Employee employee;

}
