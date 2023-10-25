package soighiri.com.coursemoto.controller.visiteur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import soighiri.com.coursemoto.dto.PasswordResetDto;
import soighiri.com.coursemoto.model.PasswordResetToken;
import soighiri.com.coursemoto.model.Utilisateur;
import soighiri.com.coursemoto.repository.UtilisateurRepository;
import soighiri.com.coursemoto.service.PasswordResetTokenService;
import soighiri.com.coursemoto.service.UtilisateurService;

@Controller
@RequestMapping(value = "/visiteur")
public class PassworsResetController {

    // Injectez le service pour gérer les utilisateurs
    @Autowired
    private UtilisateurService utilisateurService;

    // Injectez le service pour gérer les tokens de réinitialisation de mot de passe
    @Autowired
    private PasswordResetTokenService passwordResetTokenService;
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    // Étape 1 : Affiche le formulaire de demande de réinitialisation de mot de passe
    @GetMapping(value = "/passwordReset")
    public String showResetPasswordForm(Model model) {
        // Verifer la validiter du token et afficher la page de reinitilisation

        model.addAttribute("passwordResetDto", new PasswordResetDto());
        // Affiche la page de demande de réinitialisation de mot de passe
        return "visiteur/formSendEmail";
    }

    // Étape 2 : Traite la demande de réinitialisation de mot de passe
    @PostMapping(value = "/passwordReset")
    public String resetPassword(@ModelAttribute("passwordResetDto") PasswordResetDto passwordResetDto, BindingResult bindingResult) {

        // Vérifie si des erreurs de validation existent
        if (bindingResult.hasErrors()) {
            return "visiteur/formSendEmail";
        }
            // Vérifie si l'adresse e-mail est associée à un utilisateur

            Utilisateur utilisateur = utilisateurService.findByEmail(passwordResetDto.getEmail());
            if (utilisateur != null) {
                // Crée un token et l'associe à l'utilisateur
                PasswordResetToken token = passwordResetTokenService.createPasswordResetToken(utilisateur);

                // Envoie un e-mail au propriétaire de l'adresse e-mail avec le lien de réinitialisation
                sendPasswordResetEmail(utilisateur, token.getToken());

                // Redirige vers une page de confirmation
                return "visiteur/emailConfirmSuccess";

            } else {

                // Si l'e-mail n'est pas associé à un utilisateur, redirige vers une page d'erreur
                return "visiteur/resetPasswordError";
            }
        }
    private void sendPasswordResetEmail(Utilisateur utilisateur, String token) {
        passwordResetTokenService.sendPasswordResetEmail(utilisateur.getEmail(), token);
    }

    // Étape 3 : Affiche la page de réinitialisation du mot de passe
    @GetMapping(value = "/resetPassword")
    public String showPasswordResetPage(Model model, @RequestParam("token") String token) {
        PasswordResetToken resetToken = passwordResetTokenService.findByToken(token);
        if (resetToken != null && !passwordResetTokenService.isTokenExpired(resetToken)) {

            // Le token est valide, afficher la page de réinitialisation du mot de passe
            model.addAttribute("token", token);
            return "visiteur/formChangePassword";
        } else {
            return "resetPasswordTokenInvalid";

        }
    }

    // Étape 4 : Traite la réinitialisation du mot de passe
    @PostMapping(value = "/resetPassword")
    public String changePassword(@RequestParam("token") String token, @RequestParam("newPassword") String newPassword) {
        PasswordResetToken resetToken = passwordResetTokenService.findByToken(token);
        if (resetToken != null && !passwordResetTokenService.isTokenExpired(resetToken)) {

            // Le token est valide, réinitialisez le mot de passe
            Utilisateur utilisateur = resetToken.getUtilisateur();
            utilisateur.setMdpUtilisateur(newPassword);
            utilisateurService.updateUtilisateur(utilisateur);

            // Supprimez le token pour éviter une utilisation future
            passwordResetTokenService.deleteToken(resetToken);

            return "visiteur/resetPasswordSuccess";
        } else {
            // Le token est invalide ou expiré, afficher une page d'erreur
            return "visiteur/resetPasswordTokenInvalid";
        }
    }
}

