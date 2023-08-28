package soighiri.com.coursemoto.controller.admin;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import soighiri.com.coursemoto.dto.CategorieDto;
import soighiri.com.coursemoto.model.Categorie;
import soighiri.com.coursemoto.model.Pilote;
import soighiri.com.coursemoto.service.CategorieService;
import soighiri.com.coursemoto.service.PiloteService;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class CategorieController {
    private final CategorieService categorieService;
    private final PiloteService piloteService;

    @Autowired
    public CategorieController(CategorieService categorieService, PiloteService piloteService) {
        this.categorieService = categorieService;
        this.piloteService = piloteService;
    }

    @GetMapping(value = "categorie/listeCategories")
    public String index(Model model){
        List<Categorie> categories = categorieService.getAllCategories();
        model.addAttribute("lesCategories", categories);
        return "admin/categorie/index";
    }

    @GetMapping(value = "/categorie/create")
    public String create(Model model){
        model.addAttribute("categorieDto", new CategorieDto());
        List<Pilote> pilotes = piloteService.getAllPilotes();
        model.addAttribute("pilotes", pilotes);
        return "admin/categorie/creat";
    }

    @PostMapping(value = "/categorie/create")
    public String store(@ModelAttribute("categorieDto") @Valid CategorieDto categorieDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "admin/categorie/creat";
        }
        categorieService.saveCategorieFromCategorieDto(categorieDto);
        return "redirect:/admin/categorie/listeCategories";
    }

    @GetMapping(value = "/categorie/edit/{idCategorie}")
    public String editCategorie(@PathVariable Long idCategorie, Model model){
        Categorie categorie = categorieService.getCategorieById(idCategorie);
        if(categorie == null){
            return "error/notFound";
        }
        CategorieDto categorieDto = categorieService.convertEntityToDto(categorie);
        List<Pilote> pilotes = piloteService.getAllPilotes();
        model.addAttribute("categorieDto", categorieDto);
        model.addAttribute("pilotes", pilotes);
        return "admin/categorie/edit";
    }

    @PostMapping(value = "categorie/edit")
    public String updateCategorie(@ModelAttribute @Valid CategorieDto categorieDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "admin/categorie/edit";
        }
        categorieService.saveCategorieFromCategorieDto(categorieDto);
        return "redirect:/admin/categorie/listeCategories";
    }

    @GetMapping(value = "categorie/detail/{idCategorie}")
    public String getCategoriebyId(@PathVariable Long idCategorie, Model model ){
        Categorie categorie = categorieService.getCategorieById(idCategorie);
        if (categorie == null){
            return "error/notFound";
        }
        model.addAttribute("categorie", categorie);
        return "admin/categorie/categorieDetail";
    }

    @PostMapping(value = "categorie/delete/{idCategorie}")
    public String deleteCategoieById(@PathVariable Long idCategorie){
        categorieService.deleteCategorieById(idCategorie);
        return "redirect:/admin/categorie/listeCategories";
    }
}
