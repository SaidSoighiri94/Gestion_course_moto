package soighiri.com.coursemoto.controller.admin;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
    // Enregistrer le pilote
    @PostMapping(value = "/moto/create")
    public String store(@ModelAttribute("motoDto") @Valid MotoDto motoDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "admin/moto/creat";
        }
        motoService.saveMotoFromMotoDto(motoDto);
        return "redirect:/admin/moto/listeMotos";
    }

    // Modification d'une moto
    @GetMapping(value = "/moto/edit/{idMoto}")
    public String editMoto(@PathVariable Long idMoto,Model model){
        Moto moto = motoService.getMotoById(idMoto);
        if(moto == null){
            return "error/notFound";
        }
        MotoDto motoDto = motoService.convertEntityToDto(moto);
        model.addAttribute("motoDto",motoDto);
        return "admin/moto/edit";
    }
    //Pour sauvegarder les modifications
    @PostMapping(value = "/moto/edit")
    public String  updateMoto(@ModelAttribute @Valid MotoDto motoDto,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "admin/moto/edit";
        }
        motoService.saveMotoFromMotoDto(motoDto);
        return"redirect:/admin/moto/listeMotos";
    }
    //Methode de Suppression d'un Moto
    @GetMapping(value ="/moto/delete/{idMoto}")
    public String deleteMoto(@PathVariable Long idMoto){
        motoService.deletemotoById(idMoto);
        return"redirect:/admin/moto/listeMotos";
    }

}

