package by.kirilldikun.cvservice.repository;

import by.kirilldikun.cvservice.entity.Direction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectionRepository extends JpaRepository<Direction, Long> {

    Page<Direction> findAllByNameContainsIgnoreCase(String query, Pageable pageable);
}
