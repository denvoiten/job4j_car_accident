package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

@Controller
public class IndexControl {
    private final AccidentService accidentService;

    public IndexControl(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/")
    public String empty() {
        return "redirect:/accident";
    }

    @GetMapping("/accident")
    public String index(Model model) {
        model.addAttribute("accidents", accidentService.getAll());
        return "index";
    }

    @GetMapping("/addAccident")
    public String add(Model model) {
        model.addAttribute("accident", new Accident());
        return "addAccident";
    }

    @PostMapping("createAccident")
    public String create(@ModelAttribute Accident accident) {
        accidentService.add(accident);
        return "redirect:/";
    }
}
