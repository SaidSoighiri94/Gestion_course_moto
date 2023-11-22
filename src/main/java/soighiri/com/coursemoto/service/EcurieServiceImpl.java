package soighiri.com.coursemoto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soighiri.com.coursemoto.dto.EcurieDto;
import soighiri.com.coursemoto.dto.PiloteDto;
import soighiri.com.coursemoto.model.Ecurie;
import soighiri.com.coursemoto.model.Pilote;
import soighiri.com.coursemoto.repository.EcurieRepository;

import java.util.List;

@Service
public class EcurieServiceImpl implements EcurieService {
    private final EcurieRepository ecurieRepository;
    @Autowired

    public EcurieServiceImpl(EcurieRepository ecurieRepository) {
        this.ecurieRepository = ecurieRepository;

    }

    @Override
    public Ecurie saveEcurieFromEcurieDto(EcurieDto ecurieDto) {
        Ecurie ecurie = convertDtoToEntity(ecurieDto);
        return ecurieRepository.save(ecurie);
    }

    @Override
    public Ecurie updateEcurieFromEcurieDto(EcurieDto ecurieDto) {
        Ecurie ecurie = convertDtoToEntity(ecurieDto);
        return ecurieRepository.save(ecurie);
    }

    @Override
    public Ecurie getEcurieById(Long idEcurie) {

        return ecurieRepository.findById(idEcurie).orElse(null) ;
    }
    @Override
    public void deleteEcurieByid(Long idecurie) {
        ecurieRepository.deleteById(idecurie);

    }

    @Override
    public List<Ecurie>  getAllEcuries() {
        return ecurieRepository.findAll();
    }

    @Override
    public EcurieDto convertEntityToDto(Ecurie ecurie) {
        EcurieDto ecurieDto = new EcurieDto();
        if (ecurie != null) {
            ecurieDto.setIdEcurie(ecurie.getIdEcurie());
            ecurieDto.setNomEcurie(ecurie.getNomEcurie());
            ecurieDto.setResponsable(ecurie.getResponsable());
            ecurieDto.setDateCreation(ecurie.getDateCreation());
            ecurieDto.setAdresseEcurie(ecurie.getAdresseEcurie());
            ecurieDto.setEmailEcurie(ecurie.getEmailEcurie());
            ecurieDto.setTelEcurie(ecurie.getTelEcurie());
            ecurieDto.setDescription(ecurie.getDescription());
        }
        return ecurieDto;
    }

    @Override
    public void addPiloteToEcurie(Long idEcurie, PiloteDto piloteDto) {
       Ecurie ecurie = ecurieRepository.findById(idEcurie).orElseThrow(() -> new RuntimeException("Ecurie non trouvée"));
       if (piloteDto != null) {
           //Mapper les données du DTO vers l'entité Pilote
           Pilote pilote = new Pilote();
           pilote.setNomPilote(piloteDto.getNomPilote());
           pilote.setPrenomPilote(piloteDto.getPrenomPilote());
           pilote.setNumeroPilote(piloteDto.getNumeroPilote());
           pilote.setDateNaissance(piloteDto.getDateNaissance());
           pilote.setEmailPilote(piloteDto.getEmailPilote());
           pilote.setAdressePilote(piloteDto.getAdressePilote());
           pilote.setTelPilote(piloteDto.getTelPilote());
           pilote.setCategories(piloteDto.getCategories());
           pilote.setEcurie(ecurie);
           ecurie.getPiloteList().add(pilote);
           ecurieRepository.save(ecurie);
       }

    }

    private Ecurie convertDtoToEntity(EcurieDto ecurieDto) {
        Ecurie ecurie = new Ecurie();

        ecurie.setIdEcurie(ecurieDto.getIdEcurie());
        ecurie.setNomEcurie(ecurieDto.getNomEcurie());
        ecurie.setResponsable(ecurieDto.getResponsable());
        ecurie.setDateCreation(ecurieDto.getDateCreation());
        ecurie.setAdresseEcurie(ecurieDto.getAdresseEcurie());
        ecurie.setEmailEcurie(ecurieDto.getEmailEcurie());
        ecurie.setTelEcurie(ecurieDto.getTelEcurie());
        ecurie.setDescription(ecurieDto.getDescription());
        return ecurie;
    }

}
