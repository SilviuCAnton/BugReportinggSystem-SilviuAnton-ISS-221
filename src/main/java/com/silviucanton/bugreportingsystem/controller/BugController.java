package com.silviucanton.bugreportingsystem.controller;

import com.silviucanton.bugreportingsystem.domain.Bug;
import com.silviucanton.bugreportingsystem.service.BugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/bugreporting/bugs")
public class BugController {

    private final BugService bugService;

    @Autowired
    public BugController(BugService bugService) {
        this.bugService = bugService;
    }

    @GetMapping("/list")
    public String bugList(Model theModel) {
        List<Bug> bugList = bugService.getAllBugs();
        theModel.addAttribute("bugs", bugList);
        return "bug-list";
    }

    @GetMapping("/showFormForReporting")
    public String reportBug(Model theModel) {
        Bug theBug = new Bug();
        theModel.addAttribute("bug", theBug);
        return "report-bug";
    }

    @PostMapping("/report")
    public String saveBug(@ModelAttribute("bug") Bug theBug) {
        bugService.saveBug(theBug);
        //Post/Redirect/Get Pattern
        return "redirect:/bugreporting/bugs/list";
    }
}
