package soighiri.com.coursemoto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CircuitDto {
    private Long idCircuit;

    @NotBlank(message = "Nom Circuit requis")
    @Size(min = 1, max = 60, message = "Longueur incorrecte")
    private String nomCircuit;

    @NotBlank(message = "Adresse Circuit requise")
    private String adresseCircuit;

    @NotNull(message = "Longueur Circuit requise")
    @Positive(message = "La longueur doit être un nombre positif")
    private Double longueur;

}

