package soighiri.com.coursemoto.service;

import soighiri.com.coursemoto.dto.EcurieDto;
import soighiri.com.coursemoto.dto.PiloteDto;
import soighiri.com.coursemoto.model.Ecurie;
import soighiri.com.coursemoto.model.Pilote;

import java.util.List;

public interface EcurieService {

    // Methode de sauvegarde d'une ecurie via un EcurieDto
    Ecurie saveEcurieFromEcurieDto(EcurieDto ecurieDto);

    //Methode de modification d'une ecurie via un EcurieDto
    Ecurie updateEcurieFromEcurieDto(EcurieDto ecurieDto);

    //Affichage d'un circuit via par son Id
    Ecurie getEcurieById(Long idEcurie);

    // Metode pour supprimer une ecurie
    void deleteEcurieById(Long idecurie);

    // Methode pour afficher toutes les ecurie;
    List<Ecurie> getAllEcuries();

    //Methode de conversion d'un Entity en Dto
    EcurieDto convertEntityToDto(Ecurie ecurie);
    void addPiloteToEcurie(Long idEcurie, PiloteDto piloteDto);

}
