package net.controllers;

import net.domain.users.Role;
import net.dto.UserDto;
import net.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    private final UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/auth_all")
    public String auth_all(Map<String, Object> model) {
        return "auth_all";
    }

    @RequestMapping("/")
    public String root(Map<String, Object> model) {
        return "redirect:http://platform.lab4up.ru/";
    }

    @RequestMapping("/auth_ruk")
    public String auth_ruk(Map<String, Object> model) {
        return "auth_ruk";
    }

    @RequestMapping("/employees_list")
    public String employees_list(Map<String, Object> model) {
        return "employees_list";
    }

    @RequestMapping("/groups_list")
    public String group_list(Map<String, Object> model) {
        return "groups_list";
    }

    @RequestMapping("/programms_list")
    public String programms_list(Map<String, Object> model) {
        return "programms_list";
    }

    @RequestMapping("/settings_account")
    public String settings_account(Map<String, Object> model) {
        return "settings_account";
    }


    @RequestMapping("/login")
    public String login(Map<String, Object> model) {
        return "auth_all";
    }


    @RequestMapping(value = {"/addCollaborator"}, method = RequestMethod.POST)
    public String addCollaborator(@RequestParam("name") String name,
                                  @RequestParam("login") String login,
                                  @RequestParam("email") String email,
                                  @RequestParam("password") String pass,
                                  @RequestParam(value = "roles", required = false) List<String> rolesName,
                                  Map<String, Object> model) {
        boolean saved = userService.addCollaborator(login, name, pass, rolesName, email);
        if (!saved) {
            model.put("error", "Пользователь с таким логином уже существует.");
        } else {
            model.put("completed", "Успешно");
        }
//        List<UserDto> users = userService.getAllUsers(null, null);
//        model.put("users", users);
//        model.put("sort", "asc");
        return "adminka";
    }

    @RequestMapping(value = {"/adminka", "/adminka/{column}/{sort}"}, method = RequestMethod.GET)
    public String getNewsByDate(@PathVariable(value = "column", required = false) String column,
                                @PathVariable(value = "sort", required = false) String sort,
                                Map<String, Object> model) {
        List<UserDto> users = userService.getAllUsers();
        List<Role> roles = userService.getAllRoles();
        model.put("roles", roles);
        model.put("users", users);
        model.put("sort", "asc".equals(sort) ? "desc" : "asc");
        return "old/adminka";
    }

   /* @RequestMapping(value = {"/tokensignin"}, method = RequestMethod.POST)
    public String tokensignin(@RequestBody String id_token) {

        System.out.println(id_token);
        return id_token;
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Collections.singletonList("335218463881-7gd33rfh03tbhkn5uel8v1nh2ie1i1j5"))
                // Or, if multiple clients access the backend:
                //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
                .build();

// (Receive idTokenString by HTTPS POST)

        GoogleIdToken idToken = verifier.verify(idTokenString);
        if (idToken != null) {
            Payload payload = idToken.getPayload();

            // Print user identifier
            String userId = payload.getSubject();
            System.out.println("User ID: " + userId);

            // Get profile information from payload
            String email = payload.getEmail();
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");
            String locale = (String) payload.get("locale");
            String familyName = (String) payload.get("family_name");
            String givenName = (String) payload.get("given_name");

            // Use or store profile information
            // ...

        } else {
            System.out.println("Invalid ID token.");
        }
    }*/

    /**
     * временно лежат тут
     */
    @RequestMapping(value = {"/programm/{id}"}, method = RequestMethod.GET)
    public String programm(@PathVariable(name = "id") int id, Map<String, Object> model) {
        //// TODO: 18.03.17 положить в модель программу (достать из базы по id)
        return "programm";
    }

    @RequestMapping(value = {"/programm_sort/{id}"}, method = RequestMethod.POST)
    public String programm_sort(@PathVariable(name = "id") int id,
                                ////// TODO: 18.03.17 тут нужно принять массив из id урока и его номера всписке
                                Map<String, Object> model) {
        //// TODO: 18.03.17 по id достать из базы программу, переупорядочить  ее в соответсвии с массивом. (в объекте предмета есть поле order его надо привести в соответсвие с массивом)
        return "programm";
    }


}
