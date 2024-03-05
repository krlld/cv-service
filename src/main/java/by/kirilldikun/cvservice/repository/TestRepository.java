package by.kirilldikun.cvservice.repository;

import by.kirilldikun.cvservice.entity.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {

    Page<Test> findAllByNameContainsIgnoreCase(String query, Pageable pageable);
}
