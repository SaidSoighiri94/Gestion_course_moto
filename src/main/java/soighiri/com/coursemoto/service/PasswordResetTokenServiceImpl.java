package soighiri.com.coursemoto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import soighiri.com.coursemoto.model.Email;
import soighiri.com.coursemoto.model.PasswordResetToken;
import soighiri.com.coursemoto.model.Utilisateur;
import soighiri.com.coursemoto.repository.PasswordResetTokenRepository;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Service
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {
    @Autowired
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final JavaMailSender emailSender; // Injection du service JavaMailSender

    public PasswordResetTokenServiceImpl(PasswordResetTokenRepository passwordResetTokenRepository, JavaMailSender emailSender) {
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.emailSender = emailSender;
    }
    @Override

    public PasswordResetToken createPasswordResetToken(Utilisateur utilisateur) {
        PasswordResetToken token = new PasswordResetToken();
        token.setUtilisateur(utilisateur);
        token.setToken(generateUniqueToken());

        // Configurez la date d'expiration, par exemple, dans 2 heures
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR,2);
        token.setExpiryDate(calendar.getTime());
        return passwordResetTokenRepository.save(token);
    }
    @Override
    public PasswordResetToken findByToken(String token) {
        return passwordResetTokenRepository.findByToken(token);
    }
    @Override
    public boolean isTokenExpired(PasswordResetToken token) {
        return token.getExpiryDate().before((new Date()));
    }
    // Méthode pour générer un token aléatoire
    private String generateUniqueToken(){
        // Générez un token aléatoire, par exemple, en utilisant UUID (Universally Unique Identifier)
        return UUID.randomUUID().toString();
    }

    // Méthode pour envoyer un e-mail de réinitialisation de mot de passe
    @Override
    public void sendPasswordResetEmail(String recipientAdress, String token) {
        String subject = "Réinitilisation de mot de passe ";
        String resetUrl = "http://localhost:9001/visiteur/resetPassword?token=" + token;
        String message = "Cliquez sur le lien suivant pour réinitialiser votre mot de passe : " + resetUrl;

        // Pour envoyer un e-mail de reinitilisation

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(recipientAdress);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        emailSender.send(simpleMailMessage);
    }

    @Override
    public void deleteToken(PasswordResetToken token) {
        // Ici, vous pouvez mettre en œuvre la suppression du token en utilisant le repository approprié.
        // Par exemple, si vous avez un repository pour les tokens de réinitialisation de mot de passe,
        // appelez la méthode pour supprimer le token.

         passwordResetTokenRepository.delete(token);
    }
}
