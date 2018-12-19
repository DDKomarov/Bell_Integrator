package bell.controllerTest;

import bell.entity.Employee;
import bell.entity.EmployeeData;
import bell.entity.Office;
import bell.entity.Organization;
import bell.repository.EmployeeRepository;
import bell.repository.OfficeRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OfficeControllerTest {
    private static String urlList = "api/office/list";
    private static String urlSave = "api/office/save";
    private static String urlUpdate = "api/office/update";
    private static String urlDelete = "api/office/delete/{id}";
    private static String urlId = "api/office/{id}";


    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void saveTestEnt() throws Exception {
        Office office = createOffice("Name");
        ResponseEntity<Office> response = restTemplate.postForEntity(urlSave, office, Office.class);
        office = response.getBody();

        assertThat(office.getId(), is(1));
        assertThat(office.getName(), is("Name"));
        assertThat(office.getAddress(), is("Some Address"));
        assertThat(office.getPhone(), is("220206"));
        assertThat(office.getOrganization(), is(new Organization()));
        assertThat(office.getEmployeeList(), is(new ArrayList<>()));
    }

    @Test
    public void listTestEnt() throws Exception {
        Office office = createOffice("Name");
        Office office1 = createOffice("SecondName");
        ResponseEntity<List<Office>> response = restTemplate.exchange(urlList, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Office>>() {

                });

        List<Office> employeeList = response.getBody();
        assertThat(employeeList, hasSize(2));
        assertNotEquals(employeeList.get(1), is(employeeList.get(2)));
        assertThat(employeeList.get(1).getId(), is(1));
        assertThat(employeeList.get(1).getName(), is("Name"));
        assertThat(employeeList.get(2).getId(), is(2));
        assertThat(employeeList.get(2).getName(), is("Second"));
    }

    @Test
    public void deleteTestEnt() throws Exception {
        Office office = createOffice("Name");
        restTemplate.delete(urlDelete, office.getId());

        assertThat(office.getId(), is(1));
    }

    @Test
    public void updateTestEnt() throws Exception {
        Office office1 = createOffice("Name");
        office1.setName("Name1");
        office1.setOrganization(new Organization());
        office1.setEmployeeList(new ArrayList<>());
        office1.setActive(true);
        office1.setPhone("212341");
        office1.setAddress("New Some Address");
        ResponseEntity<Office> response = restTemplate.postForEntity(urlSave, office1, Office.class);
        office1 = response.getBody();


        assertThat(office1.getId(), is(1));
        assertThat(office1.getName(), is("Name1"));
    }

    @Test
    public void getByIdTestEnt() throws Exception {
        Office office = createOffice("Name");
        restTemplate.getForEntity(urlId, Office.class, office.getId());

        assertThat(office.getId(), is(1));
    }

    private Office createOffice(String name) {
        Office office = new Office();
        office.setName(name);
        office.setActive(true);
        office.setPhone("220216");
        office.setAddress("Some Address");
        office.setOrganization(new Organization());
        office.setEmployeeList(new ArrayList<>());
        return office;
    }

}
