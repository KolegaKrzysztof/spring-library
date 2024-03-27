package pl.edu.wszib.libraryapp.controller;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.libraryapp.model.User;
import pl.edu.wszib.libraryapp.services.IAuthenticationService;
import pl.edu.wszib.libraryapp.session.SessionObject;

@Controller
public class AuthenticationController {

    @Autowired
    IAuthenticationService authenticationService;
    @Resource
    SessionObject sessionObject;

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("isLogged",
                this.sessionObject.isLogged());
        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute User user, Model model) {
        model.addAttribute("isLogged",
                sessionObject.isLogged());

        this.authenticationService.login(user.getLogin(), user.getPassword());

        if (this.sessionObject.isLogged()) {
            model.addAttribute("logged", true);
            return "redirect:/main";
        } else {
            model.addAttribute("logged", false);
            return "login";
        }
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout() {
        this.authenticationService.logout();
        return "redirect:/main";
    }
}