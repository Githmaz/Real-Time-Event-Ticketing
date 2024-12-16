package edu.iit.ticketingservice.repository;

import edu.iit.ticketingservice.dao.TicketPackageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketPackageRepository extends CrudRepository<TicketPackageEntity, Long> {
    Optional<TicketPackageEntity> findByPackageId(String packageId);

}
