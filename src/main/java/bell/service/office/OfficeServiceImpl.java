package bell.service.office;

import bell.entity.Office;
import bell.entity.Organization;
import bell.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OfficeServiceImpl implements OfficeService {
    @Autowired
    private OfficeRepository officeRepository;

    @Override
    public List<Office> list(Organization organizationId, String name, String phone, boolean active) {
        return officeRepository.findAllByOrganizationIdAndNameAndPhoneAndActive(organizationId,name,phone,active);
    }

    @Override
    public Office getById(int id) {
        return officeRepository.getOne(id);
    }

    @Override
    public Office update(int id, String name, String address,String phone, Boolean isActive) {
        Office office = new Office();
        if (getById(office.getId()) != null) {
            office.setId(id);
            office.setName(name);
            office.setAddress(address);
            office.setPhone(phone);
            office.setActive(isActive);
        }
        officeRepository.save(office);
        return office;
    }

    @Override
    public void delete(int id) {
        if (getById(id) != null) {
            officeRepository.delete(getById(id));
        }
    }

    @Override
    public void save(Office office) {

    }
}
