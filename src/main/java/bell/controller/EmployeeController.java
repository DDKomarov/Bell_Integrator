package bell.controller;

import bell.controller.exception.ExceptionController;
import bell.entity.Employee;
import bell.entity.EmployeeData;
import bell.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController("api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;


    @GetMapping("/list")
    public List<Employee> list(BindingResult result) {
        if (result.hasErrors()) {
            throw new ExceptionController();
        }
        return employeeService.list();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable("id") int id) {
        if (id < 1) {
            throw new ExceptionController();
        }
        return employeeService.getById(id);
    }

    @PostMapping("/save")
    public void save(@Valid
                     @RequestParam
                     @RequestBody Employee employee,
                     BindingResult result) {
        if (result.hasErrors()) {
            throw new ExceptionController();
        }
        employeeService.save(employee);

    }

    @PostMapping("/update")
    public Employee update(@RequestBody EmployeeData data,
                           BindingResult result) {

        if (result.hasErrors()) {
            throw new ExceptionController();
        }
        Employee employee = employeeService.update(data);
        employeeService.save(employee);
        return employee;
    }

    @PostMapping("/delete/{id}")
    public void delete(@PathVariable("id") int id) {
        if (employeeService.getById(id) == null) {
            throw new ExceptionController();
        }
        employeeService.delete(id);
    }
}
