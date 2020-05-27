package com.silviucanton.bugreportingsystem.controller;

import com.silviucanton.bugreportingsystem.domain.Bug;
import com.silviucanton.bugreportingsystem.domain.Developer;
import com.silviucanton.bugreportingsystem.domain.QATester;
import com.silviucanton.bugreportingsystem.domain.UpdateMessage;
import com.silviucanton.bugreportingsystem.service.BugService;
import com.silviucanton.bugreportingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

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

    @GetMapping("/markBugAsSolved")
    public String markAsSolved(@RequestParam("bugId") int theId) {

        bugService.markBugAsSolved(theId);

        return "redirect:/bugreporting/bugs/list";
    }

    @GetMapping("/trackBug")
    public String trackBug(@RequestParam("bugId") int theId) {

        bugService.trackBug(theId);

        return "redirect:/bugreporting/bugs/list";
    }

    @PostMapping("/report")
    public String saveBug(@ModelAttribute("bug") Bug theBug) {

        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<QATester> loggedTester = userService.findTesterByUsername(userDetails.getUsername());

        loggedTester.ifPresent(theBug::setReporter);

        bugService.saveBug(theBug);
        //Post/Redirect/Get Pattern
        return "redirect:/bugreporting/bugs/list";
    }

    @MessageMapping("/update")
    @SendTo("/topic/observer")
    public UpdateMessage greeting(UpdateMessage message) throws Exception {
        System.out.println(message.getName());
        Thread.sleep(1000);
        return new UpdateMessage(HtmlUtils.htmlEscape(message.getName()) + "!");
    }

}
