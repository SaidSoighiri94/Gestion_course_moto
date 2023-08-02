package soighiri.com.coursemoto.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/pilote/listePilote")
    public String index(Model model){
        List<Pilote> pilotes = piloteService.getAllPilotes();
        model.addAttribute("lesPilotes", pilotes);
        return"admin/pilote/index";
    }


}
