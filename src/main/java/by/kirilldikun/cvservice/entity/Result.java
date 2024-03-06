package by.kirilldikun.cvservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.ZonedDateTime;

@Entity
@Table(name = "results")
@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ZonedDateTime date;

    private Integer mark;

    @ManyToOne
    private CandidateTest candidateTest;
}
