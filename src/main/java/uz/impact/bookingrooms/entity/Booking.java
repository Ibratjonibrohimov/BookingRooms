package uz.impact.bookingrooms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Resident resident;
    @Column(name = "start_at")
    private LocalDateTime start;
    @Column(name = "end_at")
    private LocalDateTime end;
    @ManyToOne
    private Room room;

    public Booking(Resident resident, LocalDateTime start, LocalDateTime end, Room room) {
        this.resident = resident;
        this.start = start;
        this.end = end;
        this.room = room;
    }
}
