package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexControl {
    @GetMapping("/accident")
    public String index(Model model) {
        model.addAttribute("stringList", List.of("First", "Second", "Third", "Fourth", "Fifth"));
        return "index";
    }

    @GetMapping("/")
    public String empty() {
        return "redirect:/accident";
    }
}
