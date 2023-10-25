package soighiri.com.coursemoto.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordResetDto {
    @Email(message = "L'e-mail du destinataire est requis")
    @NotBlank(message = "L'email est obligatoire")
    @Size(min = 6, max = 100, message = "Erreur: longueur incorrecte")

    // Ajout d'une regex qui verifie si lutilisateur a mis un e-mail valide
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "L'email n'est pas valide")
    private String email;

}
