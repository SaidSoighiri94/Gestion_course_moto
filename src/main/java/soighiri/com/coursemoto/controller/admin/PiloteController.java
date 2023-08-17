package soighiri.com.coursemoto.controller.admin;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import soighiri.com.coursemoto.dto.CircuitDto;
import soighiri.com.coursemoto.dto.PiloteDto;
import soighiri.com.coursemoto.model.Circuit;
import soighiri.com.coursemoto.model.Pilote;
import soighiri.com.coursemoto.service.PiloteService;

import java.util.List;

@Controller
@RequestMapping(value="/admin")
public class PiloteController {
    private PiloteService piloteService;

    @Autowired
    public PiloteController(PiloteService piloteService) {
        this.piloteService = piloteService;
    }

    @GetMapping(value = "/pilote/listePilote")
    public String index(Model model) {
        List<Pilote> pilotes = piloteService.getAllPilotes();
        model.addAttribute("lesPilotes", pilotes);
        return "admin/pilote/index";
    }

    // Methode permettant de selectionner un pilote selon son id
    @GetMapping(value = "/pilote/detail/{idPilote}")

    public String getPiloteById(@PathVariable Long idPilote, Model model) {
        Pilote pilote = piloteService.getPiloteById(idPilote);
        if (pilote == null) {
            return "error/notFound";
        }
        model.addAttribute("pilote", pilote);
        return "admin/pilote/piloteDetail";
    }

    //Methode permettande supprimer un pilote par on id
    @GetMapping(value = "/pilote/delete/{idPilote}")
    public String deletePiloteById(@PathVariable Long idPilote, Model model) {
        piloteService.deletePiloteById(idPilote);
        return "admin/pilote/index";
    }

    //Methode permettant d'ajouter un pilote
    @GetMapping(value = "/pilote/create")
    public String create(Model model) {
        model.addAttribute("piloteDto", new PiloteDto());
        return "admin/pilote/creat";
    }

    @PostMapping(value = "pilote/create")
    public String store(@ModelAttribute("piloteDto") @Valid PiloteDto piloteDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/pilote/creat";
        }
        piloteService.savePiloteFromPiloteDto(piloteDto);
        return "redirect:/admin/pilote/listePilote";

    }
    // Methode de modification d'un pilote

    @GetMapping(value = "/pilote/edit/{idPilote}")
    public String editPilote(@PathVariable Long idPilote, Model model) {

        Pilote pilote = piloteService.getPiloteById(idPilote);
        if (pilote == null) {

            return "error/notFound";
        }
        PiloteDto piloteDto = piloteService.convertEntityToDto(pilote);
        model.addAttribute("piloteDto", piloteDto);
            return "admin/piote/edit";
    }
    //Traitement du formulaire
    @PostMapping(value = "pilote/edit")
    public String updatePilote(@ModelAttribute @Valid PiloteDto piloteDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return"admin/pilote/edit";
        }
    piloteService.updatePiloteFromPiloteDto(piloteDto);
        return "redirect:/admin/pilote/listePilote";
    }
}
