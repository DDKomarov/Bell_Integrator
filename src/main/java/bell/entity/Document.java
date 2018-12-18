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
public class Document {

    @Id
    private int id;

    private int code;

    @NotNull
    private String name;

    @NotNull
    @OneToOne(mappedBy = "documentId" , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Employee employee;
}
