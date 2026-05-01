package com.fst.gestioncours.services;

import com.fst.gestioncours.entities.Classe;
import com.fst.gestioncours.entities.CoursClassroom;
import com.fst.gestioncours.entities.Utilisateur;
import com.fst.gestioncours.enums.Niveau;
import com.fst.gestioncours.enums.Specialite;
import com.fst.gestioncours.repositories.ClasseRepository;
import com.fst.gestioncours.repositories.CoursClassroomRepository;
import com.fst.gestioncours.repositories.UtilisateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GestionCoursService {

    private final UtilisateurRepository utilisateurRepository;
    private final ClasseRepository classeRepository;
    private final CoursClassroomRepository coursClassroomRepository;

    // ── a) Ajouter un utilisateur ─────────────────────────────────────────────
    public Utilisateur ajouterUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    // ── b) Ajouter une classe ─────────────────────────────────────────────────
    public Classe ajouterClasse(Classe c) {
        return classeRepository.save(c);
    }

    // ── c) Ajouter un CoursClassroom et l'affecter à une classe ──────────────
    public CoursClassroom ajouterCoursClassroom(CoursClassroom cc, Integer codeClasse) {
        Classe classe = classeRepository.findById(codeClasse)
                .orElseThrow(() -> new RuntimeException("Classe non trouvée: " + codeClasse));
        cc.setClasse(classe);
        return coursClassroomRepository.save(cc);
    }

    // ── d) Affecter un utilisateur à une classe ───────────────────────────────
    public void affecterUtilisateurClasse(Integer idUtilisateur, Integer codeClasse) {
        Utilisateur utilisateur = utilisateurRepository.findById(idUtilisateur)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé: " + idUtilisateur));
        Classe classe = classeRepository.findById(codeClasse)
                .orElseThrow(() -> new RuntimeException("Classe non trouvée: " + codeClasse));

        List<Classe> classes = utilisateur.getClasses();
        if (classes == null) classes = new ArrayList<>();
        if (!classes.contains(classe)) {
            classes.add(classe);
        }
        utilisateur.setClasses(classes);
        utilisateurRepository.save(utilisateur);
    }

    // ── e) Nombre d'utilisateurs par niveau ──────────────────────────────────
    public Integer nbUtilisateursParNiveau(Niveau nv) {
        return utilisateurRepository.countUtilisateursByNiveau(nv);
    }

    // ── f) Désaffecter un CoursClassroom de sa classe ─────────────────────────
    public void desaffecterCoursClassroomClasse(Integer idCours) {
        CoursClassroom cc = coursClassroomRepository.findById(idCours)
                .orElseThrow(() -> new RuntimeException("CoursClassroom non trouvé: " + idCours));
        cc.setClasse(null);
        coursClassroomRepository.save(cc);
    }

    // ── g) Archiver tous les CoursClassrooms (Scheduler toutes les 60s) ───────
    @Scheduled(fixedRate = 60000)
    public void archiverCoursClassrooms() {
        List<CoursClassroom> tous = coursClassroomRepository.findAll();
        tous.forEach(cc -> cc.setArchive(true));
        coursClassroomRepository.saveAll(tous);
        System.out.println("[Scheduler] Tous les CoursClassrooms ont été archivés.");
    }

    // ── h) Nombre d'heures par spécialité et niveau ───────────────────────────
    public Integer nbHeuresParSpecEtNiv(Specialite sp, Niveau nv) {
        return coursClassroomRepository.sumNbHeuresBySpecialiteAndNiveau(sp, nv);
    }
}
