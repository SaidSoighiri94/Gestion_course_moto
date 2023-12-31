package soighiri.com.coursemoto.model;

import jakarta.persistence.*;

@Entity
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long idUtilisateur;

    @Column(nullable = false, unique = true)
    @Basic(optional = false)
    private String email;

    @Column(nullable = false, unique = true)
    @Basic(optional = false)
    private String mdpUtilisateur;

    //Note il est possible d'avoir une association Many to Many entre Utilisateur et Role
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public Utilisateur() {
    }

    public Utilisateur(Long idUtilisateur, String email, String mdpUtilisateur) {
        this.idUtilisateur = idUtilisateur;
        this.email = email;
        this.mdpUtilisateur = mdpUtilisateur;
    }

    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdpUtilisateur() {
        return mdpUtilisateur;
    }

    public void setMdpUtilisateur(String mdpUtilisateur) {
        this.mdpUtilisateur = mdpUtilisateur;
    }
}
