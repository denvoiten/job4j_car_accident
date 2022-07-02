package ru.job4j.accident.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentServiceData;
import ru.job4j.accident.service.RuleServiceData;
import ru.job4j.accident.service.TypeServiceData;

import java.util.Set;

@RequiredArgsConstructor
@Controller
public class IndexControl {
    private static final String REDIRECT = "redirect:/";
    private static final String TYPES = "types";
    private static final String RULES = "rules";
    private final AccidentServiceData accidentServiceData;
    private final RuleServiceData ruleServiceData;
    private final TypeServiceData typeServiceData;

    @GetMapping("/")
    public String empty() {
        return "redirect:/accident";
    }

    @GetMapping("/accident")
    public String index(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("accidents", accidentServiceData.findAll());
        model.addAttribute(TYPES, typeServiceData.findAll());
        model.addAttribute(RULES, ruleServiceData.findAll());
        return "index";
    }

    @GetMapping("/addAccident")
    public String add(Model model) {
        model.addAttribute(TYPES, typeServiceData.findAll());
        model.addAttribute(RULES, ruleServiceData.findAll());
        return "addAccident";
    }

    @PostMapping("createAccident")
    public String create(@ModelAttribute Accident accident,
                         @RequestParam("typeID") int id,
                         @RequestParam("ruleID") Set<Integer> ruleIds) {
        typeServiceData.findById(id).ifPresent(accident::setType);
        accident.setRules(ruleServiceData.getRules(ruleIds));
        accidentServiceData.add(accident);
        return REDIRECT;
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", accidentServiceData.findById(id));
        model.addAttribute(TYPES, typeServiceData.findAll());
        model.addAttribute(RULES, ruleServiceData.findAll());
        return "update";
    }

    @GetMapping("/delete")
    public String delete(@ModelAttribute Accident accident) {
        accidentServiceData.delete(accident);
        return REDIRECT;
    }
}
