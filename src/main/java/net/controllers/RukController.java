package net.controllers;

import net.dto.UserDto;
import net.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class RukController {

    private final UserService userService;

    @Autowired
    public RukController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = {"/reg_ruk"}, method = RequestMethod.GET)
    public String regRuk(Map<String, Object> mdel) {
        return "reg_ruk";
    }

    @RequestMapping(value = {"/programm"}, method = RequestMethod.GET)
    public String programm(Map<String, Object> mdel) {
        return "programm";
    }

    @RequestMapping(value = {"/registrate_account"}, method = RequestMethod.GET)
    public String registrate_account(Map<String, Object> mdel) {
        return "registrate_account";
    }

    @RequestMapping(value = {"/reg_company"}, method = RequestMethod.GET)
    public String company(Map<String, Object> mdel) {
        return "reg_company";
    }

    @RequestMapping(value = {"/reg_ruk"}, method = RequestMethod.POST)
    public String addRuk(Map<String, Object> mdel,
                         @RequestParam(value = "name", required = true) String name,
                         @RequestParam(value = "login", required = true) String login,
                         @RequestParam(value = "password", required = true) String pass,
                         @RequestParam(value = "email", required = true) String email) {
        userService.addRuc(name, login, pass, email);
        return "redirect:update_ruk";
    }


    @RequestMapping(value = {"/edit_ruk"}, method = RequestMethod.GET)
    public String editRuk(Map<String, Object> model) {
        UserDto currentUser = userService.getCurrentUserDto();
        model.put("ruk", currentUser);
        return "edit_ruk";
    }

    @RequestMapping(value = {"/edit_ruk"}, method = RequestMethod.POST)
    public String editRuk(Map<String, Object> model,
                          @RequestParam(value = "name", required = true) String name,
                          @RequestParam(value = "login", required = true) String login,
                          @RequestParam(value = "password", required = true) String pass,
                          @RequestParam(value = "old_password", required = true) String oldPass,
                          @RequestParam(value = "email", required = true) String email) {
        boolean saved = userService.editRuc(name, login, pass, email, oldPass);
        if (saved) {
            model.put("saved", "Обновлено");
            model.put("ruk", userService.getUser(login));
        }
        return "redirect:edit_ruk";
    }

}
