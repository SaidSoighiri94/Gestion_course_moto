package soighiri.com.coursemoto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soighiri.com.coursemoto.dto.PiloteDto;
import soighiri.com.coursemoto.model.Pilote;
import soighiri.com.coursemoto.repository.PiloteRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PiloteServiceImpl implements PiloteService {

    private final PiloteRepository piloteRepository;

    @Autowired
    public PiloteServiceImpl(PiloteRepository piloteRepository) {
        this.piloteRepository = piloteRepository;
    }

    @Override
    public Pilote savePiloteFromPiloteDto(PiloteDto piloteDto) {
        Pilote pilote = convertDtoToEntity(piloteDto);
        return piloteRepository.save(pilote);
    }

    @Override
    public Pilote getPiloteById(Long idPilote) {
        return piloteRepository.findById(idPilote).orElse(null);
    }

    @Override
    public Pilote updatePiloteFromPiloteDto(PiloteDto piloteDto) {
        Pilote pilote = convertDtoToEntity(piloteDto);
        return piloteRepository.save(pilote);
    }

    @Override
    public void deletePiloteById(Long idPilote) {
        piloteRepository.deleteById(idPilote);
    }

    @Override
    public List<Pilote> getAllPilotes() {
        return piloteRepository.findAll();
    }

    @Override
    public PiloteDto convertEntityToDto(Pilote pilote) {
        PiloteDto piloteDto = new PiloteDto();
        if (pilote != null) {
            piloteDto.setIdPilote(pilote.getIdPilote());
            piloteDto.setNomPilote(pilote.getNomPilote());
            piloteDto.setPrenomPilote(pilote.getPrenomPilote());
            piloteDto.setNumeroPilote(pilote.getNumeroPilote());
        }
        return piloteDto;
    }

    private Pilote convertDtoToEntity(PiloteDto piloteDto) {
        Pilote pilote = new Pilote();
        pilote.setIdPilote(piloteDto.getIdPilote());
        pilote.setNomPilote(piloteDto.getNomPilote());
        pilote.setPrenomPilote(piloteDto.getPrenomPilote());
        pilote.setNumeroPilote(piloteDto.getNumeroPilote());
        return pilote;
    }
}
