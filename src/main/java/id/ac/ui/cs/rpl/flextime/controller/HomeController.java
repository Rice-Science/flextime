package id.ac.ui.cs.rpl.flextime.controller;

import id.ac.ui.cs.rpl.flextime.model.*;
import org.springframework.ui.Model;
import id.ac.ui.cs.rpl.flextime.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private SessionPlanService sessionPlanService;
    @Autowired
    private TrainingService trainingService;
    @Autowired
    private CustomizationService customizationService;

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

        return "homePage/sessionDetails";
    }

    @PostMapping("/{sessionPlanId}/add/{trainingId}")
    public String createCustomization(@PathVariable String sessionPlanId, Model model, @PathVariable String trainingId, @ModelAttribute("customization") Customization customization){
        SessionPlan sessionPlan = sessionPlanService.getSessionPlanById(sessionPlanId).orElseThrow();
        Training training = trainingService.getTrainingById(trainingId).orElseThrow();

        customizationService.saveCustomization(customization);

        CustomerTraining customerTraining = new CustomerTraining();
        customerTraining.setSessionPlan(sessionPlan);
        customerTraining.setTraining(training);
        customerTraining.setCustomization(customization);
        customerTrainingService.save(customerTraining);

        return "homepage/sessionDetails";
    }

    @PostMapping("/{sessionPlanId}/delete")
    public String deleteSessionFromFitnessPlan(Model model, @PathVariable String sessionPlanId){
        sessionPlanService.deleteSessionPlanById(sessionPlanId);
        return "homePage/sessionDetails";
    }

    @PostMapping("/{sessionPlanId}/edit/{customerTrainingId}")
    public String editCustomization( @PathVariable String sessionPlanId, @PathVariable String customerTrainingId, Model model){
            CustomerTraining customerTraining = customerTrainingService.getCustomerTrainingById(customerTrainingId);
            model.addAttribute("customization", customerTraining.getCustomization());
            model.addAttribute("customerTrainingId", customerTrainingId);

        return "customization/edit";
    }


}
