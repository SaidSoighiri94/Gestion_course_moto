package soighiri.com.coursemoto.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import soighiri.com.coursemoto.annotation.PasswordMatches;
import soighiri.com.coursemoto.model.Role;
@Data
@NoArgsConstructor
@AllArgsConstructor

//Annotaion personnel qui verifie la concordance des mot de passe
@PasswordMatches(message = "Les mot de passe ne sont pas identiques")
public class UtilisateurDto {
    private Long idUtilisateur;
    @Email(message = "Il faut un email valide")
    @NotBlank(message = "L'email est obligatoire")

    // Ajout d'une regex qui verifie si lutilisateur a mis un e-mail valide
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "L'email n'est pas valide")
    private String email;
    @Size(min = 8)
    private String mdp1NonEncoder;
    private String mdp2NonEncoder;
    private Role role;


}
