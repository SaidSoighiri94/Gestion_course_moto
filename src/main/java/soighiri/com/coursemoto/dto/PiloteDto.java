package soighiri.com.coursemoto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @Size(min = 20, max = 60, message = "Longueure incorrecte")
    private String nomPilote;
    private String PrenomPilote;

    @NotBlank(message ="Le numero du pilote est obligatoire")
    @Size(min = 1,  max = 20, message = "Longueure incorrecte")
    private int numeroPilote;

    @NotBlank(message="Date naissance requise")
    @Size(min = 8, max= 10, message="Format ou longueure incorrecte")
    private Date dateNaissance;

    private String emailPilote;
    private String adressePilote;
    private String telPilote;


}
