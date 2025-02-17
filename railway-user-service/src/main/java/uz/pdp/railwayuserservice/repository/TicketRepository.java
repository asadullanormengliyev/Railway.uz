package uz.pdp.railwayuserservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.railwayuserservice.entity.Ticket;
import uz.pdp.railwayuserservice.entity.UserEntity;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {
    List<Ticket> findByUser(UserEntity user);
}
