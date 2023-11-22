package soighiri.com.coursemoto.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
    //Lister la liste de toute les ecuries
    @GetMapping(value = "/ecurie/listeEcuries")
    public String index(Model model){
        List<Ecurie> ecuries = ecurieService.getAllEcuries();
        model.addAttribute(" ecuries",ecuries);
        return "admin/ecurie/index";
    }
    //methode permettant d'fficher une ecurie
    @GetMapping(value="/ecurie/details/{idEcurie}")
    public String getEcurieById(@PathVariable Long idEcurie,Model model){
        Ecurie ecurie = ecurieService.getEcurieById(idEcurie);
        if (ecurie == null){
            return "error/notFound";
        }
        model.addAttribute("ecurie",ecurie);
        return "admin/ecurie/ecurieDetail";
    }

}
