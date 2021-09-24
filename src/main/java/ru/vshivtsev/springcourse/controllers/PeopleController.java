package ru.vshivtsev.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vshivtsev.springcourse.DAO.PersonDAO;
import ru.vshivtsev.springcourse.model.Person;

import javax.validation.Valid;


@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String Index(Model model){
        //Получим всех людей из DAO и передадим на обображение в представление
        model.addAttribute("people", personDAO.index());
        return "/people/index";
    }

    @GetMapping("/{id}")
    public String Show(@PathVariable("id") int id, Model model){
        //Получим одного человека по его id из DAO и передадим на отображение в представление
        model.addAttribute("person", personDAO.show(id));
        return "/people/show";
    }

    @GetMapping("/new")
    public String NewPerson(Model model){
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping()
    public String Create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult){

        if(bindingResult.hasErrors())
            return "people/new";

        personDAO.Save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/editPerson/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {

        if (bindingResult.hasErrors())
            return "people/edit";
        personDAO.Update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/deletePerson/{id}")
    public String delete(@PathVariable("id") int id){
        personDAO.Delete(id);
        return "redirect:/people";
    }
}
