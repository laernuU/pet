package ru.nikita.library.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import ru.nikita.library.dao.BookDAO;
import ru.nikita.library.models.Book;

@Controller
@RequestMapping("/books")
public class BooksController {

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("lbooks", BookDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", BookDAO.findBook(id));
        return "/books/showBook";
    }

    @GetMapping("/new")
    public String add(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String newBook(@ModelAttribute("book") @Valid Book book,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/new";
        }
        BookDAO.addNewBook(book);
        return "redirect:books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", BookDAO.findBook(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
            @PathVariable("id") int id) {
        BookDAO.updateBook(id, book);
        if (bindingResult.hasErrors()) {
            return "books/new";
        }
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        BookDAO.deleteBook(id);
        return "redirect:/books";
    }
}