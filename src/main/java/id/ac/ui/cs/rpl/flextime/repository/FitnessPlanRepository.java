package id.ac.ui.cs.rpl.flextime.repository;

import id.ac.ui.cs.rpl.flextime.model.FitnessPlan;
import id.ac.ui.cs.rpl.flextime.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FitnessPlanRepository extends JpaRepository<FitnessPlan, UUID> {
    List<FitnessPlan> findByCustomer(User customer);

    FitnessPlan findFitnessPlanByCustomer_Id(UUID customerId);
}
