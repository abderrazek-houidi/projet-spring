package com.fst.gestioncours.repositories;

import com.fst.gestioncours.entities.CoursClassroom;
import com.fst.gestioncours.enums.Niveau;
import com.fst.gestioncours.enums.Specialite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CoursClassroomRepository extends JpaRepository<CoursClassroom, Integer> {

    @Query("SELECT COALESCE(SUM(cc.nbHeures), 0) FROM CoursClassroom cc " +
           "WHERE cc.specialite = :specialite AND cc.classe.niveau = :niveau")
    Integer sumNbHeuresBySpecialiteAndNiveau(@Param("specialite") Specialite specialite,
                                              @Param("niveau") Niveau niveau);

    @Query("SELECT cc FROM CoursClassroom cc WHERE cc.classe IS NOT NULL")
    List<CoursClassroom> findAllWithClasse();
}
