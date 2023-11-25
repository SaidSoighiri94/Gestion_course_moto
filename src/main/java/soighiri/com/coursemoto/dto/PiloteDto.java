package soighiri.com.coursemoto.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import soighiri.com.coursemoto.model.Categorie;
import soighiri.com.coursemoto.model.Ecurie;

import java.sql.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PiloteDto {
    private Long idPilote;

    @NotBlank(message = "Nom du pilote requis")
    @Size(min = 2, max = 60, message = "Longueure incorrecte")
    private String nomPilote;
    private String PrenomPilote;

    //@NotEmpty(message ="Le numero du pilote est obligatoire")
    @Min(value = 1, message = "Le numero du pilote doit etre entre 1 a 200")
    @Max(value =200, message="Le numero pilote doit etre entre 1 a 200" )
    private int numeroPilote;

    @NotNull(message="Date naissance requise")
    @Past(message ="La date de naissance doit etre au  passé")
    private Date dateNaissance;

    @Email(message = "L'adresse e-mail doit être valide")
    private String emailPilote;

    @NotBlank(message = "L'adresse du pilote est requise")
    private String adressePilote;

    @Pattern(regexp = "\\d{10}", message = "Le numéro de téléphone doit contenir 10 chiffres")
    private String telPilote;
    @NotNull(message = "La categorie  est requise")
    private Set<Categorie> categories;
    @NotNull(message = "L'écurie est requise")
    private Ecurie ecurie;


}
