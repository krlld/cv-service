package by.kirilldikun.cvservice.repository;

import by.kirilldikun.cvservice.entity.Candidate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    Page<Candidate> findAllByDescriptionContainsIgnoreCase(String query, Pageable pageable);
}
