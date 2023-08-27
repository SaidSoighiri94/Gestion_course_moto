package soighiri.com.coursemoto.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PiloteDto {
    private Long idPilote;

    @NotBlank(message = "Nom du pilote requis")
    @Size(min = 10, max = 60, message = "Longueure incorrecte")
    private String nomPilote;
    private String PrenomPilote;

    @NotNull(message ="Le numero du pilote est obligatoire")
    @Min(value = 1, message = "Le numero du pilote doit etre entre 1 a 200")
    @Max(value =200, message="Le numero pilote doit etre entre 1 a 200" )
    private int numeroPilote;

    @NotNull(message="Date naissance requise")
    @Past(message ="La date de naissance doit etre au  passé")
    private Date dateNaissance;

    private String emailPilote;
    private String adressePilote;
    private String telPilote;


}
