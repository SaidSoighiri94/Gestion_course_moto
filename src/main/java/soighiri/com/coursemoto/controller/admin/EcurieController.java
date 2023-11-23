package soighiri.com.coursemoto.controller.admin;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import soighiri.com.coursemoto.dto.EcurieDto;
import soighiri.com.coursemoto.model.Ecurie;
import soighiri.com.coursemoto.service.EcurieService;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class EcurieController {
    private EcurieService ecurieService;

    @Autowired
    public EcurieController(EcurieService ecurieService) {
        this.ecurieService = ecurieService;
    }

    // Lister la liste de toutes les écuries
    @GetMapping(value = "/ecurie/listeEcuries")
    public String index(Model model) {
        List<Ecurie> ecuries = ecurieService.getAllEcuries();
        model.addAttribute("lesEcuries", ecuries);
        return "admin/ecurie/index";
    }

    // Méthode permettant d'afficher une écurie
    @GetMapping(value = "/ecurie/details/{idEcurie}")
    public String getEcurieById(@PathVariable Long idEcurie, Model model) {
        Ecurie ecurie = ecurieService.getEcurieById(idEcurie);
        if (ecurie == null) {
            return "error/notFound";
        }
        model.addAttribute("ecurie", ecurie);
        return "admin/ecurie/ecurieDetail";
    }

    // Méthode pour supprimer une écurie
    @GetMapping("/ecurie/delete/{idEcurie}")
    public String deleteEcurieById(@PathVariable Long idEcurie) {
        ecurieService.deleteEcurieById(idEcurie);
        return "redirect:/admin/ecurie/listeEcuries";
    }

    // Méthode pour afficher le formulaire de création d'une écurie
    @GetMapping("/ecurie/create")
    public String create(Model model) {
        model.addAttribute("ecurieDto", new EcurieDto());
        return "admin/ecurie/create";
    }

    // Méthode pour traiter la création d'une écurie
    @PostMapping("/ecurie/create")
    public String store(@ModelAttribute("ecurieDto") @Valid EcurieDto ecurieDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/ecurie/create";
        }
        ecurieService.saveEcurieFromEcurieDto(ecurieDto);
        return "redirect:/admin/ecurie/listeEcuries";
    }

    // Méthode pour afficher le formulaire d'édition d'une écurie
    @GetMapping("/ecurie/edit/{idEcurie}")
    public String editEcurie(@PathVariable Long idEcurie, Model model) {
        EcurieDto ecurieDto = ecurieService.convertEntityToDto(ecurieService.getEcurieById(idEcurie));
        model.addAttribute("ecurieDto", ecurieDto);
        return "admin/ecurie/edit";
    }

    // Méthode pour traiter la mise à jour d'une écurie
    @PostMapping("/ecurie/edit")
    public String updateEcurie(@ModelAttribute @Valid EcurieDto ecurieDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/ecurie/edit";
        }
        ecurieService.updateEcurieFromEcurieDto(ecurieDto);
        return "redirect:/admin/ecurie/listeEcuries";
    }
}
