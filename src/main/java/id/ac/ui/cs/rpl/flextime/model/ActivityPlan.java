package id.ac.ui.cs.rpl.flextime.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name="tbl_activity")
public class ActivityPlan {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID activityId;

    @ManyToOne
    @JoinColumn(name="class_schedule_id")
    private ClassSchedules classSchedules;

    @ManyToOne
    @JoinColumn(name="assignment_schedule_id ")
    private AssignmentSchedules assignmentSchedules;

    @ElementCollection
    private Map<Date, UUID> sessionSchedules;

}
