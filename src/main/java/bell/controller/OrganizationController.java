package bell.controller;

import bell.controller.exception.ExceptionController;
import bell.entity.Organization;
import bell.service.organization.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController("/api/organization")
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;

    @PostMapping("/list")
    public List<Organization> list(BindingResult result) {
        if (result.hasErrors()) {
            throw new ExceptionController();
        }
        return organizationService.list();

    }

    @GetMapping("/{id}")
    public Organization getById(@PathVariable("id") int id) {
        if (id == 0) {
            throw new ExceptionController();
        }
        return organizationService.getById(id);
    }

    @PostMapping("/save")
    public void save(@Valid @RequestBody Organization organization,
                     BindingResult result) {
        if (result.hasErrors()) {
            throw new ExceptionController();
        }
        organizationService.save(organization);
    }

    @PostMapping("/update")
    public Organization update(@RequestParam int id,
                               @RequestParam String name,
                               @RequestParam String fullName,
                               @RequestParam int inn,
                               @RequestParam int kpp,
                               @RequestParam String address,
                               @RequestParam int phone,
                               @RequestParam boolean isActive, BindingResult result) {
        if (result.hasErrors()) {
            throw new ExceptionController();
        }
        Organization organization = organizationService.update(id, name, fullName, inn, kpp, address, phone, isActive);
        organizationService.save(organization);
        return organization;
    }

    @PostMapping("/delete")
    public void delete(@RequestParam int id) {
        if (organizationService.getById(id) == null) {
            throw new ExceptionController();
        }
        organizationService.delete(id);
    }

}
