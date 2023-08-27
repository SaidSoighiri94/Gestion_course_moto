package soighiri.com.coursemoto.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import soighiri.com.coursemoto.dto.MotoDto;
import soighiri.com.coursemoto.model.Moto;
import soighiri.com.coursemoto.service.MotoService;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class MotoController {
    private MotoService motoService;
    @Autowired
    public MotoController(MotoService motoService) {
        this.motoService = motoService;
    }
    @GetMapping("/moto/listeMotos")
    public String  index(Model model){
        List<Moto> motos = motoService.getAllMotos();
        model.addAttribute("lesMotos", motos);
        return "admin/moto/index";
    }
    // methode permettant d ajoute une moto
    @GetMapping(value ="/moto/create")
    public  String  create(Model model){
    model.addAttribute("motoDto",new MotoDto());
    return "admin/moto/creat";
    }
}

