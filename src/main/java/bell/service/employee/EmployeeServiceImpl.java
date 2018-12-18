package bell.service.employee;

import bell.entity.*;
import bell.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public List<Employee> list() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getById(int id) {
        return employeeRepository.getOne(id);
    }


    @Override
    public void delete(int id) {
        if (getById(id) != null) {
            employeeRepository.delete(getById(id));
        }
    }

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }


    @Override
    public Employee update(EmployeeData data) {
        Employee employee = new Employee();
        if (getById(employee.getId()) != null) {
            employee.setEmployeeData(data);
        }
        employeeRepository.save(employee);
        return employee;
    }

}