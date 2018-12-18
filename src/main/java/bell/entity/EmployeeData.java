package bell.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@Embeddable
public class EmployeeData {
    @NotNull
    private String firstName;

    @NotNull
    @Size(min = 10, max = 255)
    private String secondName;

    @NotNull
    @Size(min = 10, max = 255)
    private String middleName;

    @NotNull
    @Size(min = 10, max = 255)
    private String position;

    @NotNull
    @Size(min = 11, max = 11)
    @Pattern(regexp = "-?\\d+(\\.\\d+)?")
    private String phone;


}
