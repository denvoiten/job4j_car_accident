package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/update")
    public String update(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("accident", accidentService.findById(id));
        return "update";
    }

    @PostMapping("/updateAccident")
    public String updateAccident(@ModelAttribute Accident accident) {
        accidentService.update(accident);
        return "redirect:/";
    }
}
