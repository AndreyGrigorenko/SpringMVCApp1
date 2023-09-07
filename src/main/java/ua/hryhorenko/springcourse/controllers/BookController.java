package ua.hryhorenko.springcourse.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.hryhorenko.springcourse.dao.PersonDao;
import ua.hryhorenko.springcourse.models.Person;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
  private final BookDao bookDAO;
  private final PersonDao personDao;

  @Autowired
  public BookController(PersonDao personDAO, BookDao bookDao) {
    this.personDAO = personDAO;
    this.bookDao = bookDao;
  }

  @GetMapping()
  public String index(Model model) {
    model.addAttribute("books", bookDAO.index());

    return "books/index";
  }

  @GetMapping("/{id}")
  public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
    model.addAttribute("book", bookDAO.show(id));

    Optional<Person> bookOwner = bookDAO.getBookOwner(id);

    if (bookOwner.isPresent()) {
      model.addAttribute("owner", bookOwner.get());
    } else {
      model.addAttribute("people", personDao.index());
    }

    return "books/show";
  }

  @GetMapping("/new")
  public String newBook(@ModelAttribute("book") Book book) {
    return "books/new";
  }

  @PostMapping()
  public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "books/new";
    }

    bookDAO.save(book);

    return "redirect:/books";
  }

  @GetMapping("/{id}/edit")
  public String edit(@PathVariable("id") int id, Model model) {
    model.addAttribute("book", bookDAO.show(id));

    return "books/edit";
  }

  @PatchMapping("/{id}")
  public String update(@ModelAttribute("book") @Valid Book book,
                       BindingResult bindingResult,
                       @PathVariable("id") int id) {
    if (bindingResult.hasErrors()) {
      return "books/edit";
    }

    bookDAO.update(id, book);

    return "redirect:/books";
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable("id") int id) {
    bookDAO.delete(id);

    return "redirect:/books";
  }

  @PatchMapping("/{id}/release")
  public String release(@PathVariable("id") int id) {
    bookDAO.release(id);

    return "redirect:/books" + id;
  }

  @PatchMapping("/{id}/assign")
  public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person selectedPerson) {
    bookDAO.assign(id, selectedPerson);

    return "redirect:/books" + id;
  }
}
