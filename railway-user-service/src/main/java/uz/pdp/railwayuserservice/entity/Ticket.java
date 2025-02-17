package uz.pdp.railwayuserservice.entity;

import jakarta.persistence.*;
import lombok.*;
 import uz.pdp.railwayuserservice.entity.enums.TicketStatus;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Ticket {
    @Id
    private String ticketId;
    private String fromStationName;
    private String toStationName;
    private Double price;
    private Instant createdDate;
    private Instant leavingDate;
    private Instant arrivalDate;
    @Enumerated(EnumType.STRING)
    private TicketStatus status;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}
