package bell.service.employee;

import bell.entity.EmployeeData;
import bell.entity.Employee;
import bell.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Cервис
 * Интефейс для работы с классом {@link Employee}
 * */
@SuppressWarnings("JavaDoc")
@Service
public interface EmployeeService {

    /**
     * Список всех работников
     * */
    List<Employee> list();

    /**
     * Один работник
     * @param id
     * @return объект класса {@link Employee}
     * */
    Employee getById(int id);

    /**
     * Удаление работника
     * @param id
     * */
    void delete(int id);

    /**
     * Добавление работника
     * @param employee
     * */
    void save(Employee employee);


    /**
     * Изменение данных работника
     * @param data {@link EmployeeData}
     * @return объект класса {@link EmployeeData}
     * */
    Employee update(EmployeeData data);
}
