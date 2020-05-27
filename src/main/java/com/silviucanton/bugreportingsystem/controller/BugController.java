package com.silviucanton.bugreportingsystem.controller;

import com.silviucanton.bugreportingsystem.domain.Bug;
import com.silviucanton.bugreportingsystem.domain.Developer;
import com.silviucanton.bugreportingsystem.domain.QATester;
import com.silviucanton.bugreportingsystem.service.BugService;
import com.silviucanton.bugreportingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/bugreporting/bugs")
public class BugController {

    private final BugService bugService;

    private final UserService userService;

    @Autowired
    public BugController(BugService bugService, UserService userService) {
        this.bugService = bugService;
        this.userService = userService;
    }

    @GetMapping("/list")
    public String bugList(Model theModel) {

        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<QATester> loggedTester = userService.findTesterByUsername(userDetails.getUsername());
        Optional<Developer> loggedDeveloper = userService.findDeveloperByUsername(userDetails.getUsername());

        if(loggedTester.isPresent()) {
            QATester tester = loggedTester.get();
            List<Bug> bugList = bugService.getAllBugs(tester);
            theModel.addAttribute("bugs", bugList);
        }

        if(loggedDeveloper.isPresent()) {
            Developer developer = loggedDeveloper.get();
            List<Bug> bugList = bugService.getUnsolvedBugs();
            theModel.addAttribute("bugs", bugList);
        }

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
