package soighiri.com.coursemoto.service;

import soighiri.com.coursemoto.dto.MotoDto;
import soighiri.com.coursemoto.model.Moto;

import java.util.List;

public interface MotoService {
    // Sauvegarder une moto a l'aide son id
    Moto saveMotoFromMotoDto(MotoDto motoDto);

    // Affiher une moto par son Id
    Moto getMotoById(Long idMoto);

    //Afficher une liste des motos
    List<Moto> getAllMotos();

    //Modifier Une moto en utilisant un dto
     Moto updateMotoFromMotoDto(MotoDto motoDto);

     //supprimer Un fichier a l iade de son id
    void DeletemotoById(Long idMoto);

    // Convertir l'entity en DTO

    MotoDto convertEntityToDto(Moto moto);

}
