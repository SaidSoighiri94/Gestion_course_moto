package soighiri.com.coursemoto.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity

public class Pilote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPilote;
    private String nomPilote;
    private String PrenomPilote;
    private int numeroPilote;
    private Date dateNaissance;
    private String emailPilote;
    private String  adressePilote;
    private String telPilote;
    @ManyToMany
    @JoinTable(
            name  = "pilote_categorie",
    joinColumns = @JoinColumn(name="id_pilote"),
    inverseJoinColumns = @JoinColumn(name = "id_categorie")
    )
    private Set<Categorie> categories = new LinkedHashSet<>();
    @ManyToOne
    @JoinColumn(name="ecurie_id")
    private Ecurie ecurie;
    public Pilote() {
    }
    public Pilote(String nomPilote, String prenomPilote, int numeroPilote, Date dateNaissance, String emailPilote, String adressePilote, String telPilote, Set<Categorie> categories, Ecurie ecurie) {
        this.nomPilote = nomPilote;
        PrenomPilote = prenomPilote;
        this.numeroPilote = numeroPilote;
        this.dateNaissance = dateNaissance;
        this.emailPilote = emailPilote;
        this.adressePilote = adressePilote;
        this.telPilote = telPilote;
        this.categories = categories;
        this.ecurie = ecurie;
    }

    public Long getIdPilote() {
        return idPilote;
    }

    public void setIdPilote(Long idPilote) {
        this.idPilote = idPilote;
    }

    public String getNomPilote() {
        return nomPilote;
    }

    public void setNomPilote(String nomPilote) {
        this.nomPilote = nomPilote;
    }

    public String getPrenomPilote() {
        return PrenomPilote;
    }

    public void setPrenomPilote(String prenomPilote) {
        PrenomPilote = prenomPilote;
    }

    public int getNumeroPilote() {
        return numeroPilote;
    }

    public void setNumeroPilote(int numeroPilote) {
        this.numeroPilote = numeroPilote;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getEmailPilote() {
        return emailPilote;
    }

    public void setEmailPilote(String emailPilote) {
        this.emailPilote = emailPilote;
    }

    public String getAdressePilote() {
        return adressePilote;
    }

    public void setAdressePilote(String adressePilote) {
        this.adressePilote = adressePilote;
    }

    public String getTelPilote() {
        return telPilote;
    }

    public void setTelPilote(String telPilote) {
        this.telPilote = telPilote;
    }

    public Set<Categorie> getCategories() {
        return categories;
    }

    public void setCategories(Set<Categorie> categories) {
        this.categories = categories;
    }

    public Ecurie getEcurie() {
        return ecurie;
    }

    public void setEcurie(Ecurie ecurie) {
        this.ecurie = ecurie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pilote pilote = (Pilote) o;

        if (numeroPilote != pilote.numeroPilote) return false;
        if (!idPilote.equals(pilote.idPilote)) return false;
        if (!nomPilote.equals(pilote.nomPilote)) return false;
        if (!PrenomPilote.equals(pilote.PrenomPilote)) return false;
        if (!dateNaissance.equals(pilote.dateNaissance)) return false;
        if (!emailPilote.equals(pilote.emailPilote)) return false;
        if (!adressePilote.equals(pilote.adressePilote)) return false;
        return telPilote.equals(pilote.telPilote);
    }

    @Override
    public int hashCode() {
        int result = idPilote.hashCode();
        result = 31 * result + nomPilote.hashCode();
        result = 31 * result + PrenomPilote.hashCode();
        result = 31 * result + numeroPilote;
        result = 31 * result + dateNaissance.hashCode();
        result = 31 * result + emailPilote.hashCode();
        result = 31 * result + adressePilote.hashCode();
        result = 31 * result + telPilote.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Pilote{" +
                "idPilote=" + idPilote +
                ", nomPilote='" + nomPilote + '\'' +
                ", PrenomPilote='" + PrenomPilote + '\'' +
                ", numeroPilote=" + numeroPilote +
                ", dateNaissance=" + dateNaissance +
                ", emailPilote='" + emailPilote + '\'' +
                ", adressePilote='" + adressePilote + '\'' +
                ", telPilote='" + telPilote + '\'' +
                '}';
    }
}
