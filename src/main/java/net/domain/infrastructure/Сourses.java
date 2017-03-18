package net.domain.infrastructure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Сourses {
    @Id
    private long id;
    private String name;
    private String description;
    private int countLessons;
    private String recommendations;
    @OneToMany
    private List<Paragraf> paragrafList;
}
