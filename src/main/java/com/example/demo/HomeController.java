package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    ToDoRepository toDoRepository;

    @RequestMapping("/")
    public String listToDos(Model model){
        model.addAttribute("todos", toDoRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String toDoForm(Model model){
        model.addAttribute("todo", new ToDo());
        return "todoform";
    }

    @PostMapping("/process")
    public String processForm(@Valid ToDo todo, BindingResult result){
        if (result.hasErrors()){
            return "todoform";
        }
        toDoRepository.save(todo);
        return "redirect:/";
    }
}
