package soighiri.com.coursemoto.controller.admin;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class HomeController {
@GetMapping(value="/home")
    public String accueil(){
        return "home"; // le nom du fichier "home.html" sans l'extension html
    }
}
