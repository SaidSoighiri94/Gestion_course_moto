package soighiri.com.coursemoto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soighiri.com.coursemoto.dto.PiloteDto;
import soighiri.com.coursemoto.model.Categorie;
import soighiri.com.coursemoto.model.Pilote;
import soighiri.com.coursemoto.repository.CategorieRepository;
import soighiri.com.coursemoto.repository.PiloteRepository;

import java.util.List;

@Service
public class PiloteServiceImpl implements PiloteService {

    private final PiloteRepository piloteRepository;
    private final CategorieRepository categorieRepository;

    @Autowired
    public PiloteServiceImpl(PiloteRepository piloteRepository, CategorieRepository categorieRepository) {
        this.piloteRepository = piloteRepository;
        this.categorieRepository = categorieRepository;
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
            piloteDto.setDateNaissance(pilote.getDateNaissance());
            piloteDto.setAdressePilote(pilote.getAdressePilote());
            piloteDto.setEmailPilote(pilote.getEmailPilote());
            piloteDto.setTelPilote(pilote.getTelPilote());
            piloteDto.setCategories(pilote.getCategories());
        }
        return piloteDto;
    }

    private Pilote convertDtoToEntity(PiloteDto piloteDto) {
        Pilote pilote = new Pilote();
        pilote.setIdPilote(piloteDto.getIdPilote());
        pilote.setNomPilote(piloteDto.getNomPilote());
        pilote.setPrenomPilote(piloteDto.getPrenomPilote());
        pilote.setNumeroPilote(piloteDto.getNumeroPilote());
        pilote.setDateNaissance(piloteDto.getDateNaissance());
        pilote.setAdressePilote(piloteDto.getAdressePilote());
        pilote.setEmailPilote(piloteDto.getEmailPilote());
        pilote.setTelPilote(piloteDto.getTelPilote());
        pilote.setCategories(piloteDto.getCategories());
        return pilote;
    }

    @Override
    public void addCategorieToPilote(Long idPilote, Long idCategorie) {
        Pilote pilote = piloteRepository.findById(idPilote).orElse(null);
        Categorie categorie = categorieRepository.findById(idCategorie).orElse(null);
        if(pilote != null && categorie !=null) {
            pilote.getCategories().add(categorie);
            piloteRepository.save(pilote);
        }
    }
}
