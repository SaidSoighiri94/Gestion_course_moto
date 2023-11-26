package soighiri.com.coursemoto.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import soighiri.com.coursemoto.dto.EmailDto;
import soighiri.com.coursemoto.model.Email;
import soighiri.com.coursemoto.service.EmailService;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class EmailController {

    @Autowired
    private EmailService emailService;

    // on affiche la liste des email archivés
    @GetMapping(value = "/email/listEmail")
    public String showMailList(Model model){
        List<Email> emails = emailService.emailList();
        model.addAttribute("emails",emails);
        return "admin/email/listEmail";
    }

    // Pour afficher le formulaire d'envoi d'e-mail
    @GetMapping(value = "/email/newEmail")
    public String newMail(Model model){
        // on utilise un Dto pour le formulaire
        EmailDto emailDto =new EmailDto();
        model.addAttribute("emailDto",emailDto);
        return "admin/email/formEmail";
    }
    //Pour l'envoie d'un nouveau e-mail
    @PostMapping(value = "/email/sendEmail")
    public String SendEmail(@ModelAttribute("emailDto") EmailDto emailDto,BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("bindingResult",bindingResult);
            return"admin/email/formEmail";
        }
        emailService.sendEmailAndArchive(emailDto);
        ///emailService.sendEmailAndArchive(email.getRecipient(), email.getSubject(), email.getContent()); // Nous utilisons le service pour l'envoie d'e-mail
        return "redirect:/admin/email/listEmail";
    }
    // Pour consulter le contenu d'un e-mail
    @GetMapping(value = "/email/viewEmail/{id}")
    public ModelAndView viewEmail(@PathVariable(name = "id") Long idEmail) {
        ModelAndView modelAndView = new ModelAndView("admin/email/viewEmail");
        Email email = emailService.getEmail(idEmail);
        modelAndView.addObject("email", email);
        return modelAndView;
    }
}
