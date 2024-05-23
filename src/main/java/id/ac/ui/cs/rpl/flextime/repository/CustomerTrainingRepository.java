package id.ac.ui.cs.rpl.flextime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import id.ac.ui.cs.rpl.flextime.model.CustomerTraining;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerTrainingRepository extends JpaRepository<CustomerTraining, UUID> {
    CustomerTraining getCustomerTrainingById(UUID id);
    List<CustomerTraining> findCustomerTrainingsBySessionPlan_Id(UUID id);
    void deleteAllBySessionPlan_Id(UUID id);
}
