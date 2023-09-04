package soighiri.com.coursemoto.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Role {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long idRole;

    @Column(nullable = false, unique = true)
    @Basic(optional = false)
    private String nomRole;

    @OneToMany(mappedBy = "role",cascade = {CascadeType.PERSIST, CascadeType.REMOVE},orphanRemoval = true)
    private List<Utilisateur> utilisateurs = new ArrayList<>();

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public String getNomRole() {
        return nomRole;
    }

    public void setNomRole(String nomRole) {
        this.nomRole = nomRole;
    }

    public List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(List<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }
}
