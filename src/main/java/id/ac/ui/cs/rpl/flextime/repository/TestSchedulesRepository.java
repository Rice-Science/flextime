package id.ac.ui.cs.rpl.flextime.repository;
import id.ac.ui.cs.rpl.flextime.model.TestSchedules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TestSchedulesRepository extends JpaRepository<TestSchedules, UUID> {
    List<TestSchedules> findTestByCustomer_Id(UUID customer_id);
}