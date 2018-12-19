package bell.controllerTest;

import bell.entity.Employee;
import bell.entity.Office;
import bell.entity.Organization;
import bell.repository.OrganizationRepository;
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
import static org.junit.Assert.assertSame;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrganizationControllerTest {
    private static String urlList = "api/organization/list";
    private static String urlSave = "api/organization/save";
    private static String urlUpdate = "api/organization/update";
    private static String urlDelete = "api/organization/delete/{id}";
    private static String urlId = "api/organization/{id}";


    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void saveTestEnt() throws Exception {
        Organization organization = createOrganization("Name");
        ResponseEntity<Organization> response = restTemplate.postForEntity(urlSave, organization, Organization.class);
        organization = response.getBody();
        assertThat(organization.getId(), is(1));
        assertThat(organization.getName(), is("Name"));
        assertThat(organization.getAddress(), is("Some Address"));
        assertThat(organization.getPhone(), is("220206"));
        assertThat(organization.getOffices(), is(new ArrayList<>()));
    }

    @Test
    public void listTestEnt() throws Exception {
        Organization organization = createOrganization("Name");
        Organization organization1 = createOrganization("SecondName");
        ResponseEntity<List<Organization>> response = restTemplate.exchange(urlList, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Organization>>() {

                });

        List<Organization> employeeList = response.getBody();
        assertThat(employeeList, hasSize(2));
        assertNotEquals(employeeList.get(1), is(employeeList.get(2)));
        assertThat(employeeList.get(1).getId(), is(1));
        assertThat(employeeList.get(1).getName(), is("Name"));
        assertThat(employeeList.get(2).getId(), is(2));
        assertThat(employeeList.get(2).getName(), is("Second"));
    }

    @Test
    public void deleteTestEnt() throws Exception {
        Organization organization = createOrganization("Name");
        restTemplate.delete(urlDelete, organization.getId());

        assertThat(organization.getId(), is(1));
    }

    @Test
    public void updateTestEnt() throws Exception {
        Organization organization1 = createOrganization("Name");
        organization1.setName("Name1");
        organization1.setOffices(new ArrayList<>());
        organization1.setActive(true);
        organization1.setPhone("212341");
        organization1.setAddress("New Some Address");
        organization1.setInn(222113);
        organization1.setKpp(321);

        ResponseEntity<Organization> response = restTemplate.postForEntity(urlSave, organization1, Organization.class);
        organization1 = response.getBody();

        assertThat(organization1.getId(), is(1));
//        assertNotEquals("Name",organization1.getName());
        assertThat(organization1.getName(), is("Name1"));


    }

    @Test
    public void getByIdTestEnt() throws Exception {
        Organization organization = createOrganization("Name");
        restTemplate.getForObject(urlId, Employee.class, organization.getId());

        assertThat(organization.getId(), is(1));
    }

    private Organization createOrganization(String name) {
        Organization organization = new Organization();
        organization.setName(name);
        organization.setActive(true);
        organization.setPhone("220216");
        organization.setAddress("Some Address");
        organization.setKpp(123);
        organization.setInn(1122333);
        organization.setOffices(new ArrayList<>());
        return organization;
    }

}
