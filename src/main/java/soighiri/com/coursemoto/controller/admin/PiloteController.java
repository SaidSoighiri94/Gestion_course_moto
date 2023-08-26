package soighiri.com.coursemoto.controller.admin;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import soighiri.com.coursemoto.dto.PiloteDto;
import soighiri.com.coursemoto.model.Pilote;
import soighiri.com.coursemoto.model.Categorie;
import soighiri.com.coursemoto.service.PiloteService;
import soighiri.com.coursemoto.service.CategorieService;

import java.util.List;

@Controller
@RequestMapping(value="/admin")
public class PiloteController {
    private final PiloteService piloteService;
    // Déclaration de CategorieService
    private final CategorieService categorieService;

    @Autowired
    public PiloteController(PiloteService piloteService, CategorieService categorieService) {
        this.piloteService = piloteService;
        this.categorieService = categorieService;
    }

    // Afficher la liste des pilotes
    @GetMapping(value = "/pilote/listePilotes")
    public String index(Model model) {
        List<Pilote> pilotes = piloteService.getAllPilotes();
        model.addAttribute("lesPilotes", pilotes);
        return "admin/pilote/index";
    }

    // Afficher les détails d'un pilote
    @GetMapping(value = "/pilote/detail/{idPilote}")
    public String getPiloteById(@PathVariable Long idPilote, Model model) {
        Pilote pilote = piloteService.getPiloteById(idPilote);
        if (pilote == null) {
            return "error/notFound";
        }
        model.addAttribute("pilote", pilote);
        return "admin/pilote/piloteDetail";
    }

    // Supprimer un pilote
    @GetMapping(value = "/pilote/delete/{idPilote}")
    public String deletePiloteById(@PathVariable Long idPilote, Model model) {
        piloteService.deletePiloteById(idPilote);
        return "redirect:/admin/pilote/listePilotes";
    }

    // Afficher le formulaire de création d'un pilote
    @GetMapping(value = "/pilote/create")
    public String create(Model model) {
        model.addAttribute("piloteDto", new PiloteDto());
        // Récupération de la liste de catégories
        List<Categorie> categories = categorieService.getAllCategories();
        // Ajout de la liste de catégories au modèle
        model.addAttribute("categories", categories);
        return "admin/pilote/creat";
    }

    // Enregistrer un nouveau pilote
    @PostMapping(value = "/pilote/create")
    public String store(@ModelAttribute("piloteDto") @Valid PiloteDto piloteDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/pilote/creat";
        }
        piloteService.savePiloteFromPiloteDto(piloteDto);
        return "redirect:/admin/pilote/listePilotes";
    }

    // Afficher le formulaire de modification d'un pilote
    @GetMapping(value = "/pilote/edit/{idPilote}")
    public String editPilote(@PathVariable Long idPilote, Model model) {
        Pilote pilote = piloteService.getPiloteById(idPilote);
        if (pilote == null) {
            return "error/notFound";
        }
        PiloteDto piloteDto = piloteService.convertEntityToDto(pilote);

        // Récupération de la liste de catégories
        List<Categorie> categories = categorieService.getAllCategories();
        model.addAttribute("piloteDto", piloteDto);

        // Ajout de la liste de catégories au modèle
        model.addAttribute("categories", categories);
        return "admin/pilote/edit";
    }

    // Mettre à jour les informations d'un pilote
    @PostMapping(value = "/pilote/edit")
    public String updatePilote(@ModelAttribute @Valid PiloteDto piloteDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/pilote/edit";
        }
        piloteService.updatePiloteFromPiloteDto(piloteDto);
        return "redirect:/admin/pilote/listePilotes";
    }
}
