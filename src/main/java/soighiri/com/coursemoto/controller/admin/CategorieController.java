package soighiri.com.coursemoto.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import soighiri.com.coursemoto.dto.CategorieDto;
import soighiri.com.coursemoto.dto.CircuitDto;
import soighiri.com.coursemoto.model.Categorie;
import soighiri.com.coursemoto.service.CategorieService;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class CategorieController {
    private CategorieService categorieService;
    @Autowired
    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @GetMapping(value="/listeCategories")
    public String index(Model model){
        List<Categorie> categorie= categorieService.getAllCategories();
        model.addAttribute("lesCategories", categorie);
        return "admin/categorie/index";
    }
    @GetMapping(value="/categorie/create")
    public String create(Model model){
        model.addAttribute("categorietDto", new CategorieDto());
        return "admin/categorie/create";
    }
    public String store(@ModelAttribute("circuitDto") CategorieDto categorieDto){
        categorieService.saveCategorieFromCategorieDto(categorieDto);
        return"redirect:/admin/listeCategories";
    }



}
