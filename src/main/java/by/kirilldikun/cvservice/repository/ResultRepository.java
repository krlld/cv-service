package by.kirilldikun.cvservice.repository;

import by.kirilldikun.cvservice.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Long> {

    void deleteAllByCandidateTestId(Long candidateTestId);
}
