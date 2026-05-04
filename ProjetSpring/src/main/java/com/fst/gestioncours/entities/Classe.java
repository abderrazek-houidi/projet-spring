package com.fst.gestioncours.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fst.gestioncours.enums.Niveau;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Classe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codeClasse;

    private String titre;

    @Enumerated(EnumType.STRING)
    private Niveau niveau;

    @OneToMany(mappedBy = "classe")
    @JsonIgnore
    private List<CoursClassroom> coursClassrooms;

    @OneToMany(mappedBy = "classe")
    @JsonIgnore
    private List<Utilisateur> utilisateurs;
}
