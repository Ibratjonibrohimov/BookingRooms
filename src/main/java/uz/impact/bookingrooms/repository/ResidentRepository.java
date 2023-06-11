package uz.impact.bookingrooms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.impact.bookingrooms.entity.Resident;

public interface ResidentRepository extends JpaRepository<Resident,Long> {
}
