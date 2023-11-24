package soighiri.com.coursemoto.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Circuit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCircuit;
    private String nomCircuit;
    private String adresseCircuit;
    private Double longueur;
    @OneToMany(mappedBy = "circuit",cascade = CascadeType.ALL)
    private List<Course> courses;

    public Circuit() {
    }

    public Circuit( Long idCircuit,String nomCircuit, String adresseCircuit, Double longueur) {
        this.idCircuit = idCircuit;
        this.nomCircuit = nomCircuit;
        this.adresseCircuit = adresseCircuit;
        this.longueur = longueur;
    }

    public Long getIdCircuit() {
        return idCircuit;
    }

    public void setIdCircuit(Long idCircuit) {
        this.idCircuit = idCircuit;
    }

    public String getNomCircuit() {
        return nomCircuit;
    }

    public void setNomCircuit(String nomCircuit) {
        this.nomCircuit = nomCircuit;
    }

    public String getAdresseCircuit() {
        return adresseCircuit;
    }

    public void setAdresseCircuit(String adresseCircuit) {
        this.adresseCircuit = adresseCircuit;
    }

    public Double getLongueur() {
        return longueur;
    }

    public void setLongueur(Double longueur) {
        this.longueur = longueur;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Cicuit{" +
                "idCircuit=" + idCircuit +
                ", nomCircuit='" + nomCircuit + '\'' +
                ", adresseCircuit='" + adresseCircuit + '\'' +
                ", longueur=" + longueur +
                '}';
    }

}
