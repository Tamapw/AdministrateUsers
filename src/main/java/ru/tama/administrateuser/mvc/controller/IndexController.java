package ru.tama.administrateuser.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Index controller return UserManagement views
 *
 * @author tama
 */
@Controller
@RequestMapping("/")
public class IndexController {
    @RequestMapping(method = RequestMethod.GET)
    public String getIndexPage() {
        return "UserManagement";
    }
}
