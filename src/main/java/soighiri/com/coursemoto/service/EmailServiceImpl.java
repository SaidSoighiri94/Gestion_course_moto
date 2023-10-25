package soighiri.com.coursemoto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import soighiri.com.coursemoto.dto.EmailDto;
import soighiri.com.coursemoto.model.Email;
import soighiri.com.coursemoto.repository.EmailRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EmailServiceImpl implements EmailService{
    @Autowired
    private JavaMailSender emailSender;

    // on injecte notre EmailRepositoy pour archive les mail envoyé
    @Autowired
    private EmailRepository emailRepository;
    @Override
    public Email saveEmailFromEmailDto(EmailDto emailDto) {

        //On convertie l'objet EmailDto en Email
        Email email = convertDtoToEntity(emailDto);
         //Puis on sauvegarde l'email
        return emailRepository.save(email);
    }
    @Override
    public Email updateEmailFromEmailDto(EmailDto emailDto) {
        // on verifie si l'email existe d'abord
        Optional<Email> optionalEmail = emailRepository.findById(emailDto.getIdEmail());
        if (optionalEmail.isPresent()) {
            // On met à jours l'e-mail en utilisant le fichier EmailDto
            Email emailToUpdate = optionalEmail.get();
            emailToUpdate.setRecipient(emailDto.getRecipient());
            emailToUpdate.setSubject(emailDto.getSubject());
            emailToUpdate.setContent(emailDto.getContent());

            return emailRepository.save(emailToUpdate);
        } else {
            // On gere le cas ou il n'existe pas un email
            throw new IllegalArgumentException("L'e-mail avec l'ID" + emailDto.getIdEmail() + "n'existe pas.");
        }
       // Email email = convertDtoToEntity(emailDto);
       // return emailRepository.save(email);
    }

    @Override
    public Email getEmail(Long idEmail) {
        return emailRepository.findById(idEmail).orElseThrow(() -> new IllegalArgumentException("L'e-mail avec 'ID " + idEmail + "n'existe pas"));
    }

    @Override
    public void deleteEmailById(Long idEmail) {
    emailRepository.deleteById(idEmail);
    }

    @Override
    public void sendEmailAndArchive(EmailDto emailDto) {
        // On extrait les informations necessaire de l'objet EmailDto
        String to = emailDto.getRecipient();
        String subject = emailDto.getSubject();
        String text = emailDto.getContent();

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
    @Override
    public List<Email> emailList() {
        return emailRepository.findAll();
    }

    @Override
    public EmailDto convertEntityToDto(Email email) {
        EmailDto emailDto = new EmailDto();
        emailDto.setIdEmail(email.getIdEmail());
        emailDto.setRecipient(email.getRecipient());
        emailDto.setSubject(email.getSubject());
        emailDto.setContent(email.getContent());
        emailDto.setSentDate(email.getSentDate());

        //on retourne un emailDto
        return emailDto;
    }
    // Methode pour convertir un EmailDto en Email entity
    private Email convertDtoToEntity(EmailDto emailDto){
        Email email = new Email();
        email.setIdEmail(emailDto.getIdEmail());
        email.setRecipient(emailDto.getRecipient());
        email.setSubject(emailDto.getSubject());
        email.setContent(emailDto.getContent());
        email.setSentDate(emailDto.getSentDate());
        return email;
    }
}