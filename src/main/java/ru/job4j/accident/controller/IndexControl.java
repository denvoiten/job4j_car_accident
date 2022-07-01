package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.persistence.jdbc.AccidentJdbcTemplate;
import ru.job4j.accident.service.AccidentRuleService;
import ru.job4j.accident.service.AccidentTypeService;

import java.util.Set;
import java.util.TreeSet;

@Controller
public class IndexControl {
    private static final String REDIRECT = "redirect:/";
    private static final String TYPES = "types";
    private static final String RULES = "rules";
    private final AccidentJdbcTemplate accidentJdbcTemplate;
    private final AccidentTypeService accidentTypeService;

    private final AccidentRuleService accidentRuleService;

    public IndexControl(AccidentJdbcTemplate accidentJdbcTemplate, AccidentTypeService accidentTypeService, AccidentRuleService accidentRuleService) {
        this.accidentJdbcTemplate = accidentJdbcTemplate;
        this.accidentTypeService = accidentTypeService;
        this.accidentRuleService = accidentRuleService;
    }

    @GetMapping("/")
    public String empty() {
        return "redirect:/accident";
    }

    @GetMapping("/accident")
    public String index(Model model) {
        model.addAttribute("accidents", accidentJdbcTemplate.getAll());
        model.addAttribute(TYPES, accidentTypeService.getTypes());
        model.addAttribute(RULES, accidentRuleService.getRules());
        return "index";
    }

    @GetMapping("/addAccident")
    public String add(Model model) {
        model.addAttribute(TYPES, accidentTypeService.getTypes());
        model.addAttribute(RULES, accidentRuleService.getRules());
        return "addAccident";
    }

    @PostMapping("createAccident")
    public String create(@ModelAttribute Accident accident,
                         @RequestParam("typeID") int id,
                         @RequestParam("ruleID") Set<Integer> ruleIds) {
        accident.setType(accidentTypeService.findById(id));
        Set<Rule> rules = new TreeSet<>();
        ruleIds.forEach(i -> rules.add(accidentRuleService.findById(i)));
        accident.setRules(rules);
        accidentJdbcTemplate.add(accident);
        return REDIRECT;
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", accidentJdbcTemplate.findById(id));
        model.addAttribute(TYPES, accidentTypeService.getTypes());
        model.addAttribute(RULES, accidentRuleService.getRules());
        return "update";
    }

    @PostMapping("/updateAccident")
    public String updateAccident(@ModelAttribute Accident accident,
                                 @RequestParam("typeID") int id,
                                 @RequestParam("ruleID") Set<Integer> ruleIds) {
        accident.setType(accidentTypeService.findById(id));
        Set<Rule> rules = new TreeSet<>();
        ruleIds.forEach(i -> rules.add(accidentRuleService.findById(i)));
        accident.setRules(rules);
        accidentJdbcTemplate.update(accident);
        return REDIRECT;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        accidentJdbcTemplate.delete(id);
        return REDIRECT;
    }
}
