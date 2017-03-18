package net.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {

    // todo /edit_profile
    /*
    @RequestMapping(value = {"/edit_ruk"}, method = RequestMethod.GET)
    public String editRuk(Map<String, Object> model) {
        UserDto currentUser = userService.getCurrentUserDto();
        model.put("ruk", currentUser);
        return "edit_ruk";
    }


    // todo /edit_profile
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
    */

    // TODO /company GET запрос



    @RequestMapping(value = {"/employee/{login}"}, method = RequestMethod.GET)
    public String employee(@PathVariable("login") String login, Map<String, Object> mdel) {
        ///// TODO: 18.03.17 получить пользователя по логину, положить его в модель
        return "employee";
    }


    @RequestMapping(value = {"/employee/{id}"}, method = RequestMethod.POST)
    public String employee(@PathVariable("id") int id,
                           @RequestParam("name") String name,
                           @RequestParam("login") String login,
                           @RequestParam("email") String email,
                           @RequestParam("pass") String pass,
                           @RequestParam("pass_old") String passOld,
                           @RequestParam("roles") List<String> roles,
                           Map<String, Object> model) {
        ////// TODO: 18.03.17 создать сервис, который сможет сохранить эти данные
        ////// TODO: 18.03.17 подумать куда редиректить
        return "reg_company";
    }
}
