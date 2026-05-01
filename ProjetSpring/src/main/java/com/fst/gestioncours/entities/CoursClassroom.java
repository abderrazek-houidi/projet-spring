package com.fst.gestioncours.entities;

import com.fst.gestioncours.enums.Specialite;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CoursClassroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCours;

    @Enumerated(EnumType.STRING)
    private Specialite specialite;

    private String nom;
    private Integer nbHeures;
    private Boolean archive;

    @ManyToOne
    @JoinColumn(name = "codeClasse")
    private Classe classe;
}
