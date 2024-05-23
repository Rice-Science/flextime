package id.ac.ui.cs.rpl.flextime.service;

import id.ac.ui.cs.rpl.flextime.model.ActivityPlan;
import id.ac.ui.cs.rpl.flextime.model.SessionPlan;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class ActivityPlanServiceImpl implements ActivityPlanService {

    public ActivityPlan createActivityPlan(UUID userId) {
        return null;
    }

    public void addSession(SessionPlan sessionPlan, Date time, ActivityPlan activityPlan) {
    }

    public ActivityPlan findActivityPlanByUser_Id(UUID userId) {
        return null;
    }

    public void removeSession(Date time, ActivityPlan activityPlan) {
    }




}
