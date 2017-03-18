package net.domain.infrastructure;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Lesson {
    @Id
    private long id;
    @OneToMany
    private Paragraf paragraf;
    private int order;
}
