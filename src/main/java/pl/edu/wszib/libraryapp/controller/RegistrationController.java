package pl.edu.wszib.libraryapp.controller;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.libraryapp.model.User;
import pl.edu.wszib.libraryapp.services.IUserService;
import pl.edu.wszib.libraryapp.session.SessionObject;

@Controller
public class RegistrationController {

    @Autowired
    IUserService userService;
    @Resource
    SessionObject sessionObj;

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String login(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("isLogged",
                this.sessionObj.isLogged());
        return "register";
    }
    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String login(@ModelAttribute User user, Model model){
        model.addAttribute("isLogged",
                this.sessionObj.isLogged());
        if(this.userService.userExist(user.getLogin())){
            model.addAttribute("exist", true);
            return "register";
        }
        else{
            model.addAttribute("exist",false);
            userService.persist(user);
            return "redirect:/login";
        }

    }
}
