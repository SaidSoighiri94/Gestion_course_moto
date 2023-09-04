package soighiri.com.coursemoto.service;

import soighiri.com.coursemoto.dto.UtilisateurDto;
import soighiri.com.coursemoto.model.Utilisateur;

public interface UtilisateurService {
    Utilisateur inscrireUtilisateur(UtilisateurDto utilisateurDto);
    Utilisateur convertDtoToEntity(UtilisateurDto utilisateurDto);


}
