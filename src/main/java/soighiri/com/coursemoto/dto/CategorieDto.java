package soighiri.com.coursemoto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import soighiri.com.coursemoto.model.Pilote;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategorieDto {
private Long idCategorie;
@NotBlank(message = "Nom categorie requis")
@Size(min = 6, max = 30, message = "Longueur incorrecte")
private String nomCategorie;
@NotBlank(message = "Description requise")
@Size(min =1, max=250, message = "Logueur du texte incorrecte")

private String descriptionCategorie;
private Set<Pilote> pilotes;
}
