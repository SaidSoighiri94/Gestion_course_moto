package soighiri.com.coursemoto.model;

import jakarta.persistence.*;

//@Data
//@NoArgsConstructor  //Constructeur sans argument
//@AllArgsConstructor //Constructeur avec tout les argument
// Premiere chose : transformer cette classe en une entité
@Entity
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategorie;
    private String nomCategorie;

    private String descriptionCategorie;

    public Categorie() {
    }

    public Categorie(String nomCategorie, String descriptionCategorie) {
        this.nomCategorie = nomCategorie;
        this.descriptionCategorie = descriptionCategorie;
    }

    public Long getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Long idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public String getDescriptionCategorie() {
        return descriptionCategorie;
    }

    public void setDescriptionCategorie(String descriptionCategorie) {
        this.descriptionCategorie = descriptionCategorie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Categorie categorie = (Categorie) o;

        if (!idCategorie.equals(categorie.idCategorie)) return false;
        if (!nomCategorie.equals(categorie.nomCategorie)) return false;
        return descriptionCategorie.equals(categorie.descriptionCategorie);
    }

    @Override
    public int hashCode() {
        int result = idCategorie.hashCode();
        result = 31 * result + nomCategorie.hashCode();
        result = 31 * result + descriptionCategorie.hashCode();
        return result;
    }
}
