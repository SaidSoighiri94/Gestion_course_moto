package soighiri.com.coursemoto.controller.admin;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import soighiri.com.coursemoto.dto.CircuitDto;
import soighiri.com.coursemoto.model.Circuit;
import soighiri.com.coursemoto.service.CircuitService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class CircuitController {
    private CircuitService circuitService;
    @Autowired
    public CircuitController(CircuitService circuitService){
        this.circuitService = circuitService;
    }
    @GetMapping("/listeCircuits")
    public String index(Model model) {
        List<Circuit> circuits = circuitService.getAllCircuits();
        model.addAttribute("lesCircuits", circuits);
        return"admin/circuit/index";
    }

    // Methode permettant d'ajouter un nouveau circuit
    @GetMapping("/circuit/create")
    public String create(Model model){
        model.addAttribute("circuitDto", new CircuitDto());
        return "admin/circuit/creat";
    }
    @PostMapping("/circuit/create")
    public String store(@ModelAttribute("circuitDto") @Valid CircuitDto circuitDto,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return"admin/circuit/creat";
        }
        circuitService.saveCircuitFromCircuitDto(circuitDto);
        return"redirect:/admin/listeCircuits";
    }

    //Methode permettant d'afficher un circuit par son id
    @GetMapping(value = "/circuit/detail/{idCircuit}")

    public String getCicuit(@PathVariable Long idCircuit,Model model) {
        Circuit circuit = circuitService.getCircuit(idCircuit);
        if (circuit == null) {
            return "error/notFound";
        }
        model.addAttribute("circuit", circuit);
        return "admin/circuit/circuitDetail";
    }

        //Methode de modification d'un circuit
        @GetMapping(value = "circuit/edit/{idCircuit}")
        public String editCircuit (@PathVariable Long idCircuit, Model model)
        {
            Circuit circuit = circuitService.getCircuit(idCircuit);
            if (circuit == null) {
                return "error/notFound";
            }
            CircuitDto circuitDto = circuitService.convertEntityToDto(circuit);
            model.addAttribute("circuitDto", circuitDto);
            return "admin/circuit/edit";
        }
       @PostMapping(value = "circuit/edit")
        public String updateCircuit (@ModelAttribute  @Valid CircuitDto circuitDto, BindingResult bindingResult) {
           if (bindingResult.hasErrors()) {
               return"admin/circuit/edit";
           }
        circuitService.saveCircuitFromCircuitDto(circuitDto);
        return "redirect:/admin/listeCircuits";
       }
       //Methode pour la suppression
        @GetMapping(value="circuit/delete/{idCircuit}")
        public String deleteCircuit(@PathVariable Long idCircuit){
            circuitService.deleteCircuitById(idCircuit);
            return "redirect:/admin/listeCircuits";
        }
    }




