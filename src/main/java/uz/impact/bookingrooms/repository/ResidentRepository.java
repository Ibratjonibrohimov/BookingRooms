package uz.impact.bookingrooms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.impact.bookingrooms.entity.Resident;

import java.util.Optional;
@Repository

public interface ResidentRepository extends JpaRepository<Resident,Long> {
    Optional<Resident> findByName(String name);
}
