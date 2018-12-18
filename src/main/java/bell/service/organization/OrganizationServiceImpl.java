package bell.service.organization;

import bell.entity.Organization;
import bell.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public List<Organization> list() {
        return organizationRepository.findAll();
    }

    @Override
    public Organization getById(int id) {
        return organizationRepository.getOne(id);
    }

    @Override
    public Organization update(int id, String name, String fullName, int inn, int kpp, String address, int phone, boolean isActive) {
        Organization organization = new Organization();
        if (getById(organization.getId()) != null) {
            organization.setId(id);
            organization.setName(name);
            organization.setFullName(fullName);
            organization.setInn(inn);
            organization.setKpp(kpp);
            organization.setAddress(address);
            organization.setPhone(phone);
            organization.setActive(isActive);
        }
        organizationRepository.save(organization);
        return organization;
    }


    @Override
    public void delete(int id) {
        if(getById(id)!=null){
            organizationRepository.delete(getById(id));
        }
    }

    @Override
    public void save(Organization organization) {
        organizationRepository.save(organization);
    }
}
