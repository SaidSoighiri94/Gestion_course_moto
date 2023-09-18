package soighiri.com.coursemoto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import soighiri.com.coursemoto.model.Email;
import soighiri.com.coursemoto.repository.EmailRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;

    // on injecte notre EmailRepositoy pour archive les mail envoyé
    @Autowired
    private EmailRepository emailRepository;

    public void sendEmailAndArchive(String to,String subject,String text) {
        // pour envoyer l'email
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        emailSender.send(simpleMailMessage);

        /**
         * Pour archiver les emails
         */
        Email email = new Email();
        email.setRecipient(to);
        email.setSubject(subject);
        email.setSentDate(LocalDateTime.now()); // Date au moment de l'envoi
        email.setContent(text);
        emailRepository.save(email);
     }
     //Methode pour recuperer la listes des email archivés
    public List<Email> emailList(){
        return emailRepository.findAll();
    }

}
