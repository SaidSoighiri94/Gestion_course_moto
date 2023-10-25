package soighiri.com.coursemoto.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import soighiri.com.coursemoto.dto.UtilisateurDto;
import soighiri.com.coursemoto.model.Utilisateur;
import soighiri.com.coursemoto.repository.RoleRepository;
import soighiri.com.coursemoto.repository.UtilisateurRepository;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {
    private UtilisateurRepository utilisateurRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Utilisateur inscrireUtilisateur(UtilisateurDto utilisateurDto) {
        Utilisateur utilisateur = this.convertDtoToEntity(utilisateurDto);
        utilisateur.setRole(roleRepository.findById(1L).get());
        utilisateur.setMdpUtilisateur(passwordEncoder.encode(utilisateur.getMdpUtilisateur()));
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public Utilisateur convertDtoToEntity(UtilisateurDto utilisateurDto) {
        Utilisateur utilisateur;
        if (utilisateurDto.getIdUtilisateur() == null) {
            utilisateur = new Utilisateur();
        } else {
            utilisateur = utilisateurRepository.findById(utilisateurDto.getIdUtilisateur()).orElse(new Utilisateur());
        }
        utilisateur.setEmail(utilisateurDto.getEmail());
        utilisateur.setMdpUtilisateur(utilisateurDto.getMdp1NonEncoder());
        utilisateur.setRole(utilisateurDto.getRole());
        return utilisateur;
    }

    @Override
    public Utilisateur findByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }

    @Override
    public Utilisateur updateUtilisateur(Utilisateur utilisateur) {
        // Assurons-nous que l'utilisateur que nous recevons est déjà présent en base de données.
        // Vous devrez probablement effectuer une vérification pour vous assurer que l'utilisateur existe.


        // Mise à jour de l'utilisateur en base de données
        Utilisateur utilisateurExist  = utilisateurRepository.findById(utilisateur.getIdUtilisateur()).orElse(null);

        if(utilisateurExist != null){
           // utilisateurExist.setEmail(utilisateur.getEmail());
            utilisateurExist.setMdpUtilisateur(passwordEncoder.encode(utilisateur.getMdpUtilisateur()));
            // Vous pouvez mettre à jour d'autres champs ici si nécessaire

            return utilisateurRepository.save(utilisateurExist);
        } else {
            // Gérer le cas où l'utilisateur n'existe pas
            throw new EntityNotFoundException("L'utilisateur n'a pas été trouvé dans la base de données.");
        }

    }



}