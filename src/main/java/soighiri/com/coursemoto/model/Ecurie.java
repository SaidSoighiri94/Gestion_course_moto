package soighiri.com.coursemoto.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ecurie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEcurie;
    private String nomEcurie;
    private String description;
    private Date dateCreation;
    private String responsable;
    private String emailEcurie;
    private String telEcurie;
    private String adresseEcurie;
    @OneToMany(mappedBy ="ecurie", cascade = CascadeType.ALL)
    private List<Pilote> pilotes;
    @OneToMany(mappedBy = "ecurie", cascade = CascadeType.ALL)
    private List<Moto> motos;
    public Ecurie() {
    }
    public Ecurie(String nomEcurie, String description, Date dateCreation, String responsable, String emailEcurie, String telEcurie, String adresseEcurie) {

        this.nomEcurie = nomEcurie;
        this.description = description;
        this.dateCreation = dateCreation;
        this.responsable = responsable;
        this.emailEcurie = emailEcurie;
        this.telEcurie = telEcurie;
        this.adresseEcurie = adresseEcurie;
    }

    public Long getIdEcurie() {
        return idEcurie;
    }

    public void setIdEcurie(Long idEcurie) {
        this.idEcurie = idEcurie;
    }

    public String getNomEcurie() {
        return nomEcurie;
    }

    public void setNomEcurie(String nomEcurie) {
        this.nomEcurie = nomEcurie;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getEmailEcurie() {
        return emailEcurie;
    }

    public void setEmailEcurie(String emailEcurie) {
        this.emailEcurie = emailEcurie;
    }

    public String getTelEcurie() {
        return telEcurie;
    }

    public void setTelEcurie(String telEcurie) {
        this.telEcurie = telEcurie;
    }

    public String getAdresseEcurie() {
        return adresseEcurie;
    }

    public void setAdresseEcurie(String adresseEcurie) {
        this.adresseEcurie = adresseEcurie;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Pilote> getPilotes() {
        return pilotes;
    }

    public void setPilotes(List<Pilote> pilotes) {
        this.pilotes = pilotes;
    }

    public List<Moto> getMotos() {
        return motos;
    }

    public void setMotos(List<Moto> motos) {
        this.motos = motos;
    }

    @Override
    public String toString() {
        return "Ecurie{" +
                "idEcurie=" + idEcurie +
                ", nomEcurie='" + nomEcurie + '\'' +
                ", description='" + description + '\'' +
                ", dateCreation=" + dateCreation +
                ", responsable='" + responsable + '\'' +
                ", emailEcurie='" + emailEcurie + '\'' +
                ", telEcurie='" + telEcurie + '\'' +
                ", adresseEcurie='" + adresseEcurie + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ecurie ecurie = (Ecurie) o;

        if (!idEcurie.equals(ecurie.idEcurie)) return false;
        if (!nomEcurie.equals(ecurie.nomEcurie)) return false;
        if (!description.equals(ecurie.description)) return false;
        if (!dateCreation.equals(ecurie.dateCreation)) return false;
        if (!responsable.equals(ecurie.responsable)) return false;
        if (!emailEcurie.equals(ecurie.emailEcurie)) return false;
        if (!telEcurie.equals(ecurie.telEcurie)) return false;
        return adresseEcurie.equals(ecurie.adresseEcurie);
    }

    @Override
    public int hashCode() {
        int result = idEcurie.hashCode();
        result = 31 * result + nomEcurie.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + dateCreation.hashCode();
        result = 31 * result + responsable.hashCode();
        result = 31 * result + emailEcurie.hashCode();
        result = 31 * result + telEcurie.hashCode();
        result = 31 * result + adresseEcurie.hashCode();
        return result;
    }
}
