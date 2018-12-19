package bell.controllerTest;

import bell.entity.*;
import bell.repository.EmployeeRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {
    private static String urlList = "api/employee/list";
    private static String urlSave = "api/employee/save";
    private static String urlUpdate = "api/employee/update";
    private static String urlDelete = "api/employee/delete/{id}";
    private static String urlId = "api/employee/{id}";


    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void saveTestEnt() throws Exception {
        Employee employee = createEmpl("Name");
        ResponseEntity<Employee> response = restTemplate.postForEntity(urlSave, employee, Employee.class);
        employee = response.getBody();
        assertThat(employee.getId(), is(1));
        assertThat(employee.getEmployeeData().getFirstName(), is("Name"));

    }

    @Test
    public void listTestEnt() throws Exception {
        Employee employee = createEmpl("Name");
        Employee employee2 = createEmpl("SecondName");
        ResponseEntity<List<Employee>> response = restTemplate.exchange(urlList, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Employee>>() {
                });

        List<Employee> employeeList = response.getBody();
        assertThat(employeeList, hasSize(2));
        assertThat(employeeList.get(1).getId(), is(1));
        assertThat(employeeList.get(1).getEmployeeData().getFirstName(), is("Name"));
        assertThat(employeeList.get(2).getId(), is(2));
        assertThat(employeeList.get(2).getEmployeeData().getFirstName(), is("Second"));
    }

    @Test
    public void deleteTestEnt() throws Exception {
        Employee employee = createEmpl("Name");
        restTemplate.delete(urlDelete, employee.getId());

        assertThat(employee.getId(), is(1));
    }

    @Test
    public void updateTestEnt() throws Exception {
        Employee employee = createEmpl("Name");
        EmployeeData data = new EmployeeData();
        {
            data.setFirstName("name1");
            data.setSecondName("secname2");
            data.setMiddleName("midname");
            data.setPhone("+79201920182");
            data.setPosition("TM");
        }
        employee.setEmployeeData(data);

        employee.setIdentified(true);
        employee.setOfficeId(new Office());
        employee.setCountriesId(new Countries());
        employee.setDocumentId(new Document());

        ResponseEntity<Employee> response = restTemplate.postForEntity(urlSave, employee, Employee.class);

        employee = response.getBody();
        assertThat(employee.getId(), is(1));
        assertThat(data.getFirstName(), is("name1"));
    }

    @Test
    public void getByIdTestEnt() throws Exception {
        Employee employee = createEmpl("Name");
        restTemplate.getForEntity(urlId, Employee.class, employee.getId());

        assertThat(employee.getId(), is(1));
        assertThat(employee.getEmployeeData().getFirstName(), is("Name"));
    }

    private Employee createEmpl(String name) {
        Employee employee = new Employee();
        EmployeeData data = new EmployeeData();
        {
            data.setFirstName(name);
            data.setSecondName("secname");
            data.setMiddleName("name");
            data.setPhone("+79873550560");
            data.setPosition("PM");
        }

        employee.setEmployeeData(data);

        employee.setIdentified(true);
        employee.setOfficeId(new Office());
        employee.setCountriesId(new Countries());
        employee.setDocumentId(new Document());
        return employee;
    }

}
