package soighiri.com.coursemoto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private String DescriptionCat;

    public Categorie() {
    }

    public Categorie(String nomCategorie, String descriptionCat) {
        this.nomCategorie = nomCategorie;
        DescriptionCat = descriptionCat;
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

    public String getDescriptionCat() {
        return DescriptionCat;
    }

    public void setDescriptionCat(String descriptionCat) {
        DescriptionCat = descriptionCat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Categorie categorie = (Categorie) o;

        if (!idCategorie.equals(categorie.idCategorie)) return false;
        if (!nomCategorie.equals(categorie.nomCategorie)) return false;
        return DescriptionCat.equals(categorie.DescriptionCat);
    }

    @Override
    public int hashCode() {
        int result = idCategorie.hashCode();
        result = 31 * result + nomCategorie.hashCode();
        result = 31 * result + DescriptionCat.hashCode();
        return result;
    }
}
