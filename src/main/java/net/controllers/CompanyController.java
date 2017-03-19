package net.controllers;

import net.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;

@Controller
public class CompanyController {
    private final UserService userService;

    public CompanyController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = {"/edit_company/{id}"}, method = RequestMethod.GET)
    public String editCompany(@PathVariable("id") int id, Map<String, Object> mdel) {
        ////// TODO: 18.03.17 получить программу из сервисного слоя и положить ее в модель
        return "edit_company";
    }

    @RequestMapping(value = {"/edit_company/{id}"}, method = RequestMethod.POST)
    public String editCompany(@PathVariable("id") int id,
                              @RequestParam("files") List<MultipartFile> files,
                              @RequestParam("name") String name,
                              @RequestParam("description") String description,
                              Map<String, Object> model) {
        ////// TODO: 18.03.17 создать сервис, который сможет сохранить эти данные
        ////// TODO: 18.03.17 подумать куда редиректить после спешного создания компании (для редиректа перед названием view написать "redirect:")
        return "reg_company";
    }


    @RequestMapping(value = {"/create_account"}, method = RequestMethod.GET)
    public String regCompany(Map<String, Object> mdel) {
        return "create_account";
    }

    @RequestMapping(value = {"/create_account"}, method = RequestMethod.POST)
    public String regCompany(
                        @RequestParam(value = "name", required = true) String name,
                        @RequestParam(value = "login", required = true) String login,
                        @RequestParam(value = "password", required = true) String pass,
                        @RequestParam(value = "email", required = true) String email,
              //            @RequestParam("account_logo") Object account_logo,
                              @RequestParam(value = "account_name", required = true) String account_name,
                          @RequestParam("account_description") String description,
                          Map<String, Object> model) {
        userService.addRuc(name, login, pass, email);
        ////// TODO: 18.03.17 создать сервис, который сможет сохранить данные аккаунта

        return "redirect:login";
    }

}
