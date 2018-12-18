package bell.controller;

import bell.entity.Role;
import bell.entity.User;
import bell.repository.UserRepository;
import bell.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;

@RestController("/api/registration")
public class RegistrationController {

//    @Autowired
//    private EmployeeRepository employeeRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String registration() {
        return "registration";
    }

    @PostMapping
    public String addUser(@Valid User user, BindingResult bindingResult, Model model) {

            User userFromDb = userRepository.findByUsername(user.getUsername());
        if (bindingResult.hasErrors()) {
            return "error";
        }
        if (userFromDb != null) {
            model.addAttribute("message", "Employee exists!");
            return "registration";
        }
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);

        return "redirect:/api/login";
    }

}
