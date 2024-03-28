package pl.edu.wszib.libraryapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.libraryapp.services.IBookService;
import pl.edu.wszib.libraryapp.services.IUserService;

@Controller
public class SetInController {

    @Autowired
    IBookService bookService;
    @Autowired
    IUserService userService;

    @RequestMapping(path = "/set/book", method = RequestMethod.PUT)
    public ResponseEntity<Object> bookInit() {
        this.bookService.setInDb();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(path = "/set/user", method = RequestMethod.PUT)
    public ResponseEntity<Object> userInit() {
        this.userService.setInDb();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}