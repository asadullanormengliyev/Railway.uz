package uz.pdp.railwayuserservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import uz.pdp.railwayuserservice.entity.Ticket;
import uz.pdp.railwayuserservice.entity.UserEntity;
import uz.pdp.railwayuserservice.repository.TicketRepository;
import uz.pdp.railwayuserservice.repository.UserRepository;
import uz.pdp.railwayuserservice.service.event.TicketCreateEvent;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {
    private static final String TOPIC = "ticket-topic";
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    @KafkaListener(topics = TOPIC, groupId = "group-id2")
    public void consume(TicketCreateEvent ticketEvent) {
        UserEntity user = userRepository.findById(ticketEvent.getOwnerId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Ticket ticket = Ticket.builder()
                .user(user)
                .ticketId(ticketEvent.getTicketId())
                .fromStationName(ticketEvent.getFromStationName())
                .toStationName(ticketEvent.getToStationName())
                .price(ticketEvent.getPrice())
                .createdDate(ticketEvent.getCreatedDate())
                .leavingDate(ticketEvent.getLeavingDate())
                .arrivalDate(ticketEvent.getArrivalDate())
                .status(ticketEvent.getStatus())
                .build();
        ticketRepository.save(ticket);
    }

    public List<Ticket> getTicketsByUserId(int userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
         return ticketRepository.findByUser(user);
    }

}
