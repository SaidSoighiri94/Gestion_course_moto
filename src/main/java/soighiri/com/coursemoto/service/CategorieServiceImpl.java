package soighiri.com.coursemoto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soighiri.com.coursemoto.dto.CategorieDto;
import soighiri.com.coursemoto.model.Categorie;
import soighiri.com.coursemoto.model.Pilote;
import soighiri.com.coursemoto.repository.CategorieRepository;
import soighiri.com.coursemoto.repository.PiloteRepository;

import java.util.List;
@Service
public class CategorieServiceImpl implements CategorieService{
    private CategorieRepository categorieRepository;
    private final PiloteRepository piloteRepository;
    @Autowired
    public CategorieServiceImpl(CategorieRepository categorieRepository, PiloteRepository piloteRepository) {
        this.categorieRepository = categorieRepository;
        this.piloteRepository = piloteRepository;
    }

    @Override
    public Categorie saveCategorieFromCategorieDto(CategorieDto categorieDto) {
        Categorie categorie = convertDtoToEntity(categorieDto);
        return categorieRepository.save(categorie);
    }

    @Override
    public Categorie updateCategorieFromCategorieDto(CategorieDto categorieDto) {
        Categorie categorie = convertDtoToEntity(categorieDto);
        return categorieRepository.save(categorie);
    }

    @Override
    public void deleteCategorieById(Long idCategorie) {
        categorieRepository.deleteById(idCategorie);

    }

    @Override
    public Categorie getCategorieById(Long idCategorie) {
        return categorieRepository.findById(idCategorie).orElse(null);
    }

    @Override
    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    @Override
    public void addPiloteToCategorie(long idCategorie, Long idPilote) {
        Categorie categorie = categorieRepository.findById(idCategorie).orElse(null);
        Pilote pilote = piloteRepository.findById(idPilote).orElse(null);
    }

    @Override
    public CategorieDto convertEntityToDto(Categorie categorie) {
        CategorieDto categorieDto = new CategorieDto();
        if(categorie!=null){
            categorieDto.setIdCategorie(categorie.getIdCategorie());
            categorieDto.setNomCategorie(categorie.getNomCategorie());
            categorieDto.setDescriptionCategorie(categorie.getDescriptionCategorie());
        }
        return categorieDto;
    }

    private Categorie convertDtoToEntity(CategorieDto categorieDto) {
        Categorie categorie = new Categorie();
        if(categorieDto!=null){
            categorie.setIdCategorie(categorieDto.getIdCategorie());
            categorie.setNomCategorie(categorieDto.getNomCategorie());
            categorie.setDescriptionCategorie(categorieDto.getDescriptionCategorie());
        }
        return categorie;
    }


}
