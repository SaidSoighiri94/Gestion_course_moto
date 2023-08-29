package soighiri.com.coursemoto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soighiri.com.coursemoto.dto.EcurieDto;
import soighiri.com.coursemoto.dto.PiloteDto;
import soighiri.com.coursemoto.model.Ecurie;
import soighiri.com.coursemoto.model.Pilote;
import soighiri.com.coursemoto.repository.EcurieRepository;
import soighiri.com.coursemoto.repository.PiloteRepository;

import java.util.List;

@Service
public class EcurieServiceImpl implements EcurieService {
    private  EcurieRepository ecurieRepository;
    private final PiloteRepository piloteRepository;

    @Autowired

    public EcurieServiceImpl(EcurieRepository ecurieRepository,
                             PiloteRepository piloteRepository) {
        this.ecurieRepository = ecurieRepository;
        this.piloteRepository = piloteRepository;
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
        piloteRepository.deleteById(idecurie);

    }

    @Override
    public List<Ecurie> getAllCircuits() {

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

    private Ecurie convertDtoToEntity(EcurieDto ecurieDto) {
        Ecurie Ecurie = new Ecurie();

        ecurie.setNomEcurie(ecurieDto.getNomEcurie());
        return ecurie;
    }

}
