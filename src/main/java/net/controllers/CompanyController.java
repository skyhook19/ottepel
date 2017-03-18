package net.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 18.03.2017.
 */
public class CompanyController {

    //// TODO: 18.03.17 переместить в контроллер для компаний
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

    //// TODO: 18.03.17 переместить в контроллер для компаний
    @RequestMapping(value = {"/reg_company"}, method = RequestMethod.GET)
    public String company(Map<String, Object> mdel) {
        return "reg_company";
    }

    @RequestMapping(value = {"/reg_company"}, method = RequestMethod.POST)
    public String company(@RequestParam("files") List<MultipartFile> files,
                          @RequestParam("name") String name,
                          @RequestParam("description") String description,
                          Map<String, Object> model) {
        ////// TODO: 18.03.17 создать сервис, который сможет сохранить эти данные
        ////// TODO: 18.03.17 подумать куда редиректить после спешного создания компании (для редиректа перед названием view написать "redirect:")
        return "reg_company";
    }

}
