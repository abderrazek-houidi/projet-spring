package com.fst.gestioncours.controllers;

import com.fst.gestioncours.entities.Classe;
import com.fst.gestioncours.entities.CoursClassroom;
import com.fst.gestioncours.entities.Utilisateur;
import com.fst.gestioncours.enums.Niveau;
import com.fst.gestioncours.enums.Specialite;
import com.fst.gestioncours.services.GestionCoursService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class GestionCoursController {

    private final GestionCoursService gestionCoursService;

    // ── a) Ajouter un utilisateur ─────────────────────────────────────────────
    @PostMapping("/utilisateurs")
    public Utilisateur ajouterUtilisateur(@RequestBody Utilisateur utilisateur) {
        return gestionCoursService.ajouterUtilisateur(utilisateur);
    }

    // ── b) Ajouter une classe ─────────────────────────────────────────────────
    @PostMapping("/classes")
    public Classe ajouterClasse(@RequestBody Classe c) {
        return gestionCoursService.ajouterClasse(c);
    }

    // ── c) Ajouter un CoursClassroom affecté à une classe ────────────────────
    @PostMapping("/cours/{codeClasse}")
    public CoursClassroom ajouterCoursClassroom(
            @RequestBody CoursClassroom cc,
            @PathVariable Integer codeClasse) {
        return gestionCoursService.ajouterCoursClassroom(cc, codeClasse);
    }

    // ── d) Affecter un utilisateur à une classe ───────────────────────────────
    @PutMapping("/utilisateurs/{idUtilisateur}/classes/{codeClasse}")
    public void affecterUtilisateurClasse(
            @PathVariable Integer idUtilisateur,
            @PathVariable Integer codeClasse) {
        gestionCoursService.affecterUtilisateurClasse(idUtilisateur, codeClasse);
    }

    // ── e) Nombre d'utilisateurs par niveau ──────────────────────────────────
    @GetMapping("/utilisateurs/count/niveau/{nv}")
    public Integer nbUtilisateursParNiveau(@PathVariable Niveau nv) {
        return gestionCoursService.nbUtilisateursParNiveau(nv);
    }

    // ── f) Désaffecter un CoursClassroom ─────────────────────────────────────
    @PutMapping("/cours/{idCours}/desaffecter")
    public void desaffecterCoursClassroomClasse(@PathVariable Integer idCours) {
        gestionCoursService.desaffecterCoursClassroomClasse(idCours);
    }

    // ── h) Nombre d'heures par spécialité et niveau ───────────────────────────
    @GetMapping("/cours/heures/specialite/{sp}/niveau/{nv}")
    public Integer nbHeuresParSpecEtNiv(
            @PathVariable Specialite sp,
            @PathVariable Niveau nv) {
        return gestionCoursService.nbHeuresParSpecEtNiv(sp, nv);
    }
}
