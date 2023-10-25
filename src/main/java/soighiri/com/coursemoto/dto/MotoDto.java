package soighiri.com.coursemoto.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MotoDto {
    private Long idMoto;
    @NotBlank(message = "Marque de la moto requise")
    @Size(min = 4, message = "Les caractere ne doivent pas être moin de 4")
    private String marqueMoto;

    @NotBlank(message = "Veuillez indiquer une version")
    @Size(max = 10, message = "il faut 10 caractere")
    private String versionMoto;

    @NotBlank(message = "Ce champs ne doit pat être vide")
    @Size(min = 6, max = 20, message = "Les caractere ne doivent etre entre 6 à 20")
    private String puissanceMoto;

    @NotBlank(message = "Ce champs est obligatoire")
    @Size(min = 6, max = 20, message ="Les caractere ne doivent etre entre 6 à 20"  )
    private String modeleMoto;

    //@NotBlank(message = "Veuillez indiquer un chemin vers l'image")
    private String imagePath;

    private MultipartFile fichierImage; // le telechargement du fichier


}
