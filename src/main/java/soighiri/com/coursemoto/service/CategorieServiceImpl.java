package soighiri.com.coursemoto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soighiri.com.coursemoto.dto.CategorieDto;
import soighiri.com.coursemoto.model.Categorie;
import soighiri.com.coursemoto.repository.CategorieRepository;

import java.util.List;
@Service
public class CategorieServiceImpl implements CategorieService{
    private CategorieRepository categorieRepository;

    public CategorieServiceImpl(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    @Override
    public Categorie saveCategorieFromCategorieDto(CategorieDto categorieDto) {
        return null;
    }

    @Override
    public Categorie updateCategorieFromCategorieDto(CategorieDto categorieDto) {
        return null;
    }

    @Override
    public void deleteCategorie(Long idCategorie) {

    }

    @Override
    public Categorie getCategorieById(Long idCategorie) {
        return null;
    }

    @Override
    public List<Categorie> getAllCategories() {
        return null;
    }

    @Override
    public CategorieDto convertEntityToDto(Categorie categorie) {
        return null;
    }
}
