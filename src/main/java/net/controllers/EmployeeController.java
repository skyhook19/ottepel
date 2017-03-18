package net.controllers;

import org.springframework.stereotype.Controller;

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
}
