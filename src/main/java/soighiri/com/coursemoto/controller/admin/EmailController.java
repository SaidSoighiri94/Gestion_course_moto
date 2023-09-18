package soighiri.com.coursemoto.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import soighiri.com.coursemoto.model.Email;
import soighiri.com.coursemoto.service.EmailService;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class EmailController {

    @Autowired
    private EmailService emailService;

    /**
     *
     1er methode
     * on peut l'ecrire de cette fçcon mais cel veut dire qu on va devoir choisir manuellement
     *
     * @GetMapping(value = "/email/sendEmail")
     *     public ModelAndView showSentFormEmail(){
     *         return new ModelAndView("admin/email/fromEmail");
     *     }
     *     @PostMapping(value = "/email/sendEmail")
     *     public ModelAndView EnvoyerEmail(@RequestParam("recpient") String recipient,@RequestParam("subject") String subject,@RequestParam("content") String content){
     *         emailService.sendEmailAndArchive(recipient, subject, content);
     *         return new ModelAndView("admin/email/emailEnvoye");
     *         }
     */


    /*** 2eme methode
     * On l'ecrit de cette facon et il prend automatique l'objet
     *
     */

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
        model.addAttribute("email",new Email());
        return "admin/email/formEmail";
    }
    //Pour l'envoie d'un nouveau e-mail
    @PostMapping(value = "/email/sendEmail")
    public String SendEmail(@ModelAttribute Email email){
        emailService.sendEmailAndArchive(email.getRecipient(), email.getSubject(), email.getContent()); // Nous utilisons le service pour l'envoie d'e-mail
        return "redirect:/admin/email/listEmail";
    }

}
