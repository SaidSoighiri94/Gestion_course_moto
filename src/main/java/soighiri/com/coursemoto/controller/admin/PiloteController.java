package soighiri.com.coursemoto.controller.admin;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import soighiri.com.coursemoto.dto.EcurieDto;
import soighiri.com.coursemoto.dto.PiloteDto;
import soighiri.com.coursemoto.model.Ecurie;
import soighiri.com.coursemoto.model.Pilote;
import soighiri.com.coursemoto.model.Categorie;
import soighiri.com.coursemoto.service.EcurieService;
import soighiri.com.coursemoto.service.PiloteService;
import soighiri.com.coursemoto.service.CategorieService;

import java.util.List;

@Controller
@RequestMapping(value="/admin")
public class PiloteController {
    private final PiloteService piloteService;
    private final CategorieService categorieService;
    private final EcurieService ecurieService;
    @Autowired
    public PiloteController(PiloteService piloteService, CategorieService categorieService, EcurieService ecurieService) {
        this.piloteService = piloteService;
        this.categorieService = categorieService;
        this.ecurieService = ecurieService;
    }

    @GetMapping(value = "/pilote/listePilotes")
    public String index(Model model) {
        List<Pilote> pilotes = piloteService.getAllPilotes();
        model.addAttribute("lesPilotes", pilotes);
        return "admin/pilote/index";
    }

    @GetMapping(value = "/pilote/detail/{idPilote}")
    public String getPiloteById(@PathVariable Long idPilote, Model model) {
        Pilote pilote = piloteService.getPiloteById(idPilote);
        if (pilote == null) {
            return "error/notFound";
        }
        model.addAttribute("pilote", pilote);
        return "admin/pilote/piloteDetail";
    }

    @GetMapping(value = "/pilote/delete/{idPilote}")
    public String deletePiloteById(@PathVariable Long idPilote) {
        piloteService.deletePiloteById(idPilote);
        return "redirect:/admin/pilote/listePilotes";
    }

    @GetMapping(value = "/pilote/create")
    public String create(Model model) {
        model.addAttribute("piloteDto", new PiloteDto());
        List<Categorie> categories = categorieService.getAllCategories();
        List<Ecurie> ecuries = ecurieService.getAllEcuries();

        // Pour verifier si la liste est bien charger dans la console
        //System.out.println("Ecuries: " + ecuries);
        model.addAttribute("categories", categories);
        model.addAttribute("ecuries", ecuries);
        return "admin/pilote/creat";
    }

    @PostMapping(value = "/pilote/create")
    public String store(@ModelAttribute("piloteDto") @Valid PiloteDto piloteDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Categorie> categories = categorieService.getAllCategories();
            List<Ecurie> ecuries = ecurieService.getAllEcuries();
            model.addAttribute("categories", categories);
            model.addAttribute("ecuries",ecuries);
            return "admin/pilote/creat";
        }
        piloteService.savePiloteFromPiloteDto(piloteDto);
        return "redirect:/admin/pilote/listePilotes";
    }

    @GetMapping(value = "/pilote/edit/{idPilote}")
    public String editPilote(@PathVariable Long idPilote, Model model) {
        Pilote pilote = piloteService.getPiloteById(idPilote);
        if (pilote == null) {
            return "error/notFound";
        }
        PiloteDto piloteDto = piloteService.convertEntityToDto(pilote);
        List<Categorie> categories = categorieService.getAllCategories();
        List<Ecurie> ecuries = ecurieService.getAllEcuries();
        model.addAttribute("piloteDto", piloteDto);
        model.addAttribute("categories", categories);
        model.addAttribute("ecuries",ecuries);
        return "admin/pilote/edit";
    }

    @PostMapping(value = "/pilote/edit")
    public String updatePilote(@ModelAttribute @Valid PiloteDto piloteDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Categorie> categories = categorieService.getAllCategories();
            List<Ecurie> ecuries = ecurieService.getAllEcuries();
            model.addAttribute("categories", categories);
            model.addAttribute("ecuries",ecuries);
            return "admin/pilote/edit";
        }
        piloteService.updatePiloteFromPiloteDto(piloteDto);
        return "redirect:/admin/pilote/listePilotes";
    }
}
