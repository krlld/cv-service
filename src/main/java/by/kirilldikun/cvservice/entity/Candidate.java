package by.kirilldikun.cvservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Entity
@Table(name = "candidates")
@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String secondName;

    private String firstName;

    private String patronymic;

    private Long photoFileId;

    private String description;

    private Long cvFileId;

    @ManyToMany
    @JoinTable(
            name = "candidate_directions",
            joinColumns = @JoinColumn(name = "candidate_id"),
            inverseJoinColumns = @JoinColumn(name = "direction_id"))
    List<Direction> directions;
}
