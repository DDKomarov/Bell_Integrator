package bell.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@SuppressWarnings("JavaDoc")
@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doc_id")
    private Document documentId;

    @OneToOne(cascade =CascadeType.ALL)
    @JoinColumn(name = "citizenship_id")
    private Countries countriesId;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Office.class)
    @JoinColumn(name = "office_id")
    private Office officeId;

    @NotNull
    private boolean identified;

    private EmployeeData employeeData;


//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return roles;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }

}
