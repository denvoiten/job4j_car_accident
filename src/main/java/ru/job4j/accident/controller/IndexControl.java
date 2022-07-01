package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentRuleService;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.AccidentTypeService;

import java.util.Set;

@Controller
public class IndexControl {
    private final AccidentService accidentService;
    private final AccidentTypeService accidentTypeService;

    private final AccidentRuleService accidentRuleService;

    public IndexControl(AccidentService accidentService, AccidentTypeService accidentTypeService, AccidentRuleService accidentRuleService) {
        this.accidentService = accidentService;
        this.accidentTypeService = accidentTypeService;
        this.accidentRuleService = accidentRuleService;
    }

    @GetMapping("/")
    public String empty() {
        return "redirect:/accident";
    }

    @GetMapping("/accident")
    public String index(Model model) {
        model.addAttribute("accidents", accidentService.getAll());
        model.addAttribute("types", accidentTypeService.getTypes());
        model.addAttribute("rules", accidentRuleService.getRules());
        return "index";
    }

    @GetMapping("/addAccident")
    public String add(Model model) {
        model.addAttribute("types", accidentTypeService.getTypes());
        model.addAttribute("rules", accidentRuleService.getRules());
        return "addAccident";
    }

    @PostMapping("createAccident")
    public String create(@ModelAttribute Accident accident,
                         @RequestParam("typeID") int id,
                         @RequestParam("ruleID") Set<Integer> ruleIds) {
        accident.setType(accidentTypeService.findById(id));
        accident.setRules(accidentRuleService.getRulesAccident(ruleIds));
        accidentService.add(accident);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("accident", accidentService.findById(id));
        model.addAttribute("types", accidentTypeService.getTypes());
        return "update";
    }

    @PostMapping("/updateAccident")
    public String updateAccident(@ModelAttribute Accident accident, @RequestParam("typeID") int id) {
        accident.setType(accidentTypeService.findById(id));
        accidentService.update(accident);
        return "redirect:/";
    }
}
