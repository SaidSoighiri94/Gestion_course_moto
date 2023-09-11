package soighiri.com.coursemoto.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soighiri.com.coursemoto.model.Utilisateur;
import soighiri.com.coursemoto.repository.UtilisateurRepository;
import java.util.HashSet;
import java.util.Set;
@Service
public class SecurityUserDetailsServiceImpl implements SecurityUserDetailsSerivce, UserDetailsService {
    private UtilisateurRepository utilisateurRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public SecurityUserDetailsServiceImpl(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Étape 1 : Obtenez les informations de l'utilisateur en recherchant par e-mail (username).
        Utilisateur utilisateur = utilisateurRepository.findByEmail(username);

        // Étape 2 : Vérifiez si un utilisateur a été trouvé, sinon, lancez une exception.
        if(utilisateur == null){
            throw new UsernameNotFoundException("Utlisateur non trouvé" + username);
        }
        // Étape 3 : Obtenez le nom du rôle de l'utilisateur.
         String nomRole = utilisateur.getRole().getNomRole();

        // Étape 4 : Créez un ensemble d'autorisations (permissions).
        Set<GrantedAuthority> permissions = new HashSet<>();
        permissions.add(new SimpleGrantedAuthority(nomRole));

        // Étape 5 : Créez un objet UserDetails (implémentant l'interface UserDetails) avec les informations de l'utilisateur.
        // Cet objet sera utilisé par Spring Security pour authentifier l'utilisateur.
        UserDetails userDetails = new User(utilisateur.getEmail(),utilisateur.getMdpUtilisateur(),permissions);

        // Étape 6 : Retournez l'objet UserDetails.
        return userDetails;
    }
}
