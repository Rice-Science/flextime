package id.ac.ui.cs.rpl.flextime.controller;

import id.ac.ui.cs.rpl.flextime.model.*;
import org.springframework.ui.Model;
import id.ac.ui.cs.rpl.flextime.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("")
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

    @Autowired
    private CustomerTrainingService customerTrainingService;

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

    @GetMapping("/{sessionPlanId}")
    public String getSessionPlanTrainingDetails(Model model, @PathVariable String sessionPlanId){
        List<CustomerTraining> customerTrainings = customerTrainingService.getCustomerTrainingsBySessionPlanId(sessionPlanId);

        model.addAttribute("customerTrainings", customerTrainings);

        return "";
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
