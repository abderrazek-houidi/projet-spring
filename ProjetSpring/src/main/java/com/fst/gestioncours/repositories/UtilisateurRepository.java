package com.fst.gestioncours.repositories;

import com.fst.gestioncours.entities.Utilisateur;
import com.fst.gestioncours.enums.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    @Query("SELECT COUNT(DISTINCT u) FROM Utilisateur u JOIN u.classes c WHERE c.niveau = :niveau")
    Integer countUtilisateursByNiveau(@Param("niveau") Niveau niveau);
}
