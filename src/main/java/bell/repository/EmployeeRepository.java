package bell.repository;

import bell.entity.Employee;
import bell.entity.EmployeeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для класса {@link Employee}
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}