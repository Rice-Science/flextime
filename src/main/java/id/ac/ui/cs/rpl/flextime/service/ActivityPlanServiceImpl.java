package id.ac.ui.cs.rpl.flextime.service;

import id.ac.ui.cs.rpl.flextime.model.*;
import id.ac.ui.cs.rpl.flextime.repository.ActivityPlanRepository;
import id.ac.ui.cs.rpl.flextime.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ActivityPlanServiceImpl implements ActivityPlanService {

    @Autowired
    private ActivityPlanRepository activityPlanRepository;
    @Autowired
    private AssignmentSchedulesService assignmentSchedulesService;
    @Autowired
    private ClassSchedulesService classSchedulesService;
    @Autowired
    private UserRepository userRepository;


    public ActivityPlan createActivityPlan(User user) {
        UUID userId = user.getId();
        List<AssignmentSchedules> assignmentSchedules = assignmentSchedulesService.findAssignmentByCustomerId(userId.toString());
        List<ClassSchedules> classSchedules = classSchedulesService.findClassByCustomerId(userId.toString());

        ActivityPlan  activityPlan = new ActivityPlan();

        activityPlan.setUser(user);

        for (AssignmentSchedules assignmentSchedule : assignmentSchedules) {
            activityPlan.setAssignmentSchedules(assignmentSchedule);
        }

        for (ClassSchedules classSchedule : classSchedules){
            activityPlan.setClassSchedules(classSchedule);
        }

        return activityPlan;
    }

    public void addSession(UUID sessionId, Date time, ActivityPlan activityPlan) {
        Map<Date, UUID> sessionSchedules =  activityPlan.getSessionSchedules();
        sessionSchedules.put(time, sessionId);
    }

    public ActivityPlan getActivityPlanByUser_Id(UUID userId) {
        return activityPlanRepository.findActivityPlanByUser_Id(userId);
    }

    public void removeSession(Date time, ActivityPlan activityPlan) {
        Map<Date, UUID> sessionSchedules = activityPlan.getSessionSchedules();
        sessionSchedules.remove(time);
    }

    public Date calculateEndTimeSession( Date time, int duration) {
        // Create a Calendar instance and set it to the given start time
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);

        // Add the duration to the calendar (assuming duration is in seconds)
        calendar.add(Calendar.SECOND, duration);

        return calendar.getTime();
    }

    public Optional<ActivityPlan> getActivityPlanById(UUID id) {
        return activityPlanRepository.findById(id);
    }







}
