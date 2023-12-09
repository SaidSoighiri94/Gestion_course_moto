package soighiri.com.coursemoto.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCourse",nullable = false)
    private Long idCourse;

    private Date dateCourse;
    private String heureCourse;
    private String nomCourse;
    private int nombreTour;
    @ManyToOne
    @JoinColumn(name = "circuit_id")
    private Circuit circuit;
    @ManyToOne
    @JoinColumn( name = "categorie_id")
    private Categorie categorie;
    public Course() {
    }

    public Course(Date dateCourse, String heureCourse, String nomCourse, int nombreTour, Circuit circuit, Categorie categorie) {
        this.dateCourse = dateCourse;
        this.heureCourse = heureCourse;
        this.nomCourse = nomCourse;
        this.nombreTour = nombreTour;
        this.circuit = circuit;
        this.categorie = categorie;
    }

// Ajout les getters et setters appropriés
    public Long getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(Long idCourse) {
        this.idCourse = idCourse;
    }

    public Date getDateCourse() {
        return dateCourse;
    }

    public void setDateCourse(Date dateCourse) {
        this.dateCourse = dateCourse;
    }

    public String getHeureCourse() {
        return heureCourse;
    }

    public void setHeureCourse(String heureCourse) {
        this.heureCourse = heureCourse;
    }

    public String getNomCourse() {
        return nomCourse;
    }

    public void setNomCourse(String nomCourse) {
        this.nomCourse = nomCourse;
    }

    public int getNombreTour() {
        return nombreTour;
    }

    public void setNombreTour(int nombreTour) {
        this.nombreTour = nombreTour;
    }

    public Circuit getCircuit() {
        return circuit;
    }

    public void setCircuit(Circuit circuit) {
        this.circuit = circuit;
    }
}
