package soighiri.com.coursemoto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CircuitDto {
    private Long idCircuit;
    @NotBlank(message = "Nom Circuit requis")
    @Size(min = 1,  max = 60, message ="Longueur incorrecte")
    private String nomCircuit;
    private String adresseCircuit;
    private Double longueur;



}
