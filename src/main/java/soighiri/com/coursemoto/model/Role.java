package soighiri.com.coursemoto.model;

import jakarta.persistence.*;

@Entity
public class Role {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long idRole;

    @Column(nullable = false, unique = true)
    @Basic(optional = false)
    private String nomRole;




}
