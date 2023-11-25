package soighiri.com.coursemoto.service;

import soighiri.com.coursemoto.dto.PiloteDto;
import soighiri.com.coursemoto.model.Pilote;

import java.util.List;

public interface PiloteService {
    Pilote savePiloteFromPiloteDto(PiloteDto piloteDto);

    Pilote getPiloteById(Long IdPilote);

    Pilote updatePiloteFromPiloteDto(PiloteDto piloteDto);

    void deletePiloteById(Long idPilote);

    List<Pilote> getAllPilotes();
    PiloteDto convertEntityToDto(Pilote pilote);
    void addCategorieToPilote(Long idPilote, Long idCategorie,Long idEcurie);
}
