package id.ac.ui.cs.rpl.flextime.controller;

import id.ac.ui.cs.rpl.flextime.model.AssignmentSchedules;
import id.ac.ui.cs.rpl.flextime.model.ClassSchedules;
import id.ac.ui.cs.rpl.flextime.model.FitnessPlan;
import org.springframework.ui.Model;
import id.ac.ui.cs.rpl.flextime.model.SessionPlan;
import id.ac.ui.cs.rpl.flextime.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private AssignmentSchedulesService assignmentService;

    @Autowired
    private ClassSchedulesService classService;

    @Autowired
    private FitnessPlanService fitnessService;

    @GetMapping("/")
    public String home(Model model) {

        UUID userId = userService.findByUsername(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName()).getId();

        FitnessPlan fitnessPlan = fitnessService.getFitnessPlanByCustomerId(userId.toString());
        List<AssignmentSchedules> assignmentSchedules = assignmentService.findAssignmentByCustomerId(userId.toString());
        List<ClassSchedules> classSchedules = classService.findClassByCustomerId(userId.toString());

        model.addAttribute("fitnessPlan", fitnessPlan);
        model.addAttribute("assignmentSchedules", assignmentSchedules);
        model.addAttribute("classSchedules", classSchedules);

        return "home";
    }

    @PostMapping("")
    public String addSessionToFitnessPlan(Model model){
        return "";
    }

    @PostMapping("")
    public String deleteSessionFromFitnessPlan(){
        return "";
    }

    @PostMapping
    public String editSessionFromFitnessPlan(){
        return "";
    }


}
