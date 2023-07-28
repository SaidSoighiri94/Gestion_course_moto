package soighiri.com.coursemoto.service;

import soighiri.com.coursemoto.dto.CategorieDto;
import soighiri.com.coursemoto.dto.CircuitDto;
import soighiri.com.coursemoto.model.Categorie;
import soighiri.com.coursemoto.model.Circuit;

import java.util.List;

public interface CategorieService {

    //Sauvegarder une categorie unecategorie en utlisant un Dto
    Categorie  saveCategorieFromCategorieDto(CategorieDto categorieDto);

    //Mettre a jour une categorie en utilisant une categorieDto
    Categorie updateCategorieFromCategorieDto(CategorieDto categorieDto);

    //Supprimer une categorie de la base de donnée en fonction de l'Id

    void deleteCategorie(Long idCategorie);

    //Récupère un circuit en fonction de son ID
    Categorie getCategorieById(Long idCategorie);

    //afficher la liste de tous les categorie
   List<Categorie> getAllCategories();

   // Covertir un entity vers un Dto
    CategorieDto convertEntityToDto(Categorie categorie);

}
