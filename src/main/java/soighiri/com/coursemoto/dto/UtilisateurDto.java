package soighiri.com.coursemoto.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import soighiri.com.coursemoto.model.Role;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurDto {
    private Long idUtilisateur;
    @Email(message = "Il faut un email valide")
    @NotBlank(message = "L'email esr obligatoire")
    private String email;
    //TODO Ajouter une annotation perso pour vérifier la concordance entre le mdp1 et mdp2
    @Size(min = 8)
    private String mdp1NonEncoder;
    private String mdp2NonEncoder;
    private Role role;


}
