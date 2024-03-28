package pl.edu.wszib.libraryapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.libraryapp.services.IBookService;

@Controller
public class BookController {

    @Autowired
    IBookService borrowService;

    @RequestMapping(path = "/borrow/{bookId}", method = RequestMethod.GET)
    private String loanBook(@PathVariable final int bookId){
        this.borrowService.persist(borrowService.getById(bookId).get());
        return "redirect:/main";
    }
}