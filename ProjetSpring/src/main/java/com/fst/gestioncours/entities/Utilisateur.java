package com.fst.gestioncours.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUtilisateur;

    private String prenom;
    private String nom;
    private String password;

    @ManyToMany
    @JoinTable(
        name = "utilisateur_classe",
        joinColumns = @JoinColumn(name = "idUtilisateur"),
        inverseJoinColumns = @JoinColumn(name = "codeClasse")
    )
    private List<Classe> classes;
}
