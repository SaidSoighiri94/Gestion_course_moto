package soighiri.com.coursemoto.service;
import soighiri.com.coursemoto.dto.EmailDto;
import soighiri.com.coursemoto.model.Email;
import java.util.List;

public interface EmailService {

     // Sauvegarde un email en utilisant un EmailDto
     Email saveEmailFromEmailDto(EmailDto emailDto);

     // Met à jour un email en utilisant un EmailDto
     Email updateEmailFromEmailDto(EmailDto emailDto);

     // Récupère un circuit en fonction de son ID
     Email getEmail(Long idEmail);

     // Supprime un circuit de la base de données en fonction de son ID
     void  deleteEmailById(Long idEmail);
     void sendEmailAndArchive(EmailDto emailDto);

     //Methode pour recuperer la listes des email archivés
     List<Email> emailList();

     //Convertir un objet Circuit en EmailDto
     EmailDto convertEntityToDto(Email email);
}
