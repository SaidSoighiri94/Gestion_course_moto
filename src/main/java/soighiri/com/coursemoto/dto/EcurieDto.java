package soighiri.com.coursemoto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EcurieDto {
    private Long idEcurie;
    @NotBlank(message = "Le nom de l'ecurie est obligatoire")
    @Size(min = 1, max = 150, message = "Le nom doit être entre de 1 à 150 caractere")
    private String nomEcurie;

    @NotBlank(message="Une description est requise")
    @Size(min = 4, max=250 ,message="La description doit etre de 4 a 255 caracteres")
    private String description;

    @NotBlank(message = "nom du responsable requis")
    @Size(min =2, max = 150, message = "Longueure du nom incorrecte ")
    private String responsable;

    @Past(message = "La date doit etre au passé")
    @NotNull(message = "La da de creaatio est requise")
    private Date dateCreation;
    @NotBlank(message = "Le champs email est requis")
    @Size(min = 3,max = 255, message = "Longueure de mail incorrecte")
    private String emailEcurie;
    @NotBlank(message = "numero tel requis")
    @Size(message ="Longueure incorrecte", max = 150, min = 4)
    private String telEcurie;
    @NotBlank(message = "Adresse requis")
    @Size(min = 4, max = 255, message = "Logueure incorrecte")
    private String adresseEcurie;

}
