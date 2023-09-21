package soighiri.com.coursemoto.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDto {
    private Long idEmail;

    @Email(message = "L'e-mail du destinataire est requis")
    @Size(min = 6, max = 100, message = "Erreur: longueur incorrecte")
    private String recipient;

    @NotBlank(message = "Le sujet de l'e-mail est obligatoire")
    @Size(min = 4, max = 100, message = "Erreur: longueur incoorect")
    private String subject;

    @NotBlank(message = "Erreur: Contenu de l'e-mail requis")
    @Size(min = 2)
    private String content;
    private LocalDateTime sentDate;


}
