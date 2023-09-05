package soighiri.com.coursemoto.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import soighiri.com.coursemoto.dto.UtilisateurDto;
import soighiri.com.coursemoto.model.Utilisateur;
import soighiri.com.coursemoto.service.UtilisateurService;

@Controller
public class HomeController {
    private UtilisateurService utilisateurService;

    public HomeController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    /**
     * Affiche le formulaire Login TODO
     */
    @GetMapping(value = "/login")
    public String login() {
        return "visiteur/login";
    }

    /**
     * Affiche le formulaire d'inscription
     */
    @GetMapping(value = "/inscription")
    public String inscription(Model model) {
        model.addAttribute("utilisateur", new Utilisateur());
        return "visiteur/inscription";
    }

    @PostMapping(value = "/inscription")
    public String traitementInsciption(@Valid @ModelAttribute UtilisateurDto utilisateurDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Le formulaire comporte une erreur");
            return "visiteur/inscription";
        }
        utilisateurService.inscrireUtilisateur(utilisateurDto);
        redirectAttributes.addFlashAttribute("succesMessage", "Inscription reussie");
        return "redirect:/login";
    }

    /**
     * EXEMPLE DE PAGE VISITEUR
     * Dirige vers la page d'acceuil (visible par tout les utilisateurs)
     */


    @Autowired
    @GetMapping(value = "/home")
    public String accueil() {
        return "home"; // le nom du fichier "home.html" sans l'extension html
    }

}
