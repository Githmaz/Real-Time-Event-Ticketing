package edu.iit.ticketingservice.repository;

import edu.iit.ticketingservice.dao.CustomerPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CustomerPlanRepository extends JpaRepository<CustomerPlanEntity,Long> {
    Optional<CustomerPlanEntity> findByPlanName(String planName);
    Optional<CustomerPlanEntity> findByPlanId(String planId);
}
