package bell.controller;

import bell.controller.exception.ExceptionController;
import bell.controller.utils.ResponseBodyController;
import bell.entity.Office;
import bell.entity.Organization;
import bell.service.office.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController("/api/office")
public class OfficeController {
    @Autowired
    private OfficeService officeService;
    @Autowired
    private ResponseBodyController controller;

    @PostMapping("/list/{organization}")
    public List<Office> list(@PathVariable("organization") Organization organization,
                             @RequestParam String name,
                             @RequestParam String phone,
                             @RequestParam boolean active) {
        return officeService.list(organization, name, phone, active);
    }

    @GetMapping("/{id}")
    public Office getById(@PathVariable("id") int id) {
        if (id == 0) {
            throw new ExceptionController();
        }
        return officeService.getById(id);
    }

    @PostMapping("/save")
    public void save(@Valid Office office, BindingResult result) {
        if (result.hasErrors()) {
            throw new ExceptionController();
        }
        officeService.save(office);
    }

    @PostMapping("/update")
    public Office update(@RequestParam int id,
                         @RequestParam String name,
                         @RequestParam String address,
                         @RequestParam String phone,
                         @RequestParam Boolean isActive,
                         BindingResult results) {
        if (results.hasErrors()) {
            throw new ExceptionController();
        }
        Office office = officeService.update(id, name, address, phone, isActive);
        officeService.save(office);
        return office;
    }

    @PostMapping("/delete")
    public void delete(int id) {
        if (officeService.getById(id) == null) {
            throw new ExceptionController();
        }
        officeService.delete(id);
    }
}
