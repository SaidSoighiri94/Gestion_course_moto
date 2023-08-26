package soighiri.com.coursemoto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import nonapi.io.github.classgraph.utils.LogNode;


@Entity
public class Moto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMoto;
     private String MarqueMoto;
     private String versionMoto;
     private String puissanceMoto;
     private String modeleMoto;

     public Moto(){

     }

    public Moto(String marqueMoto, String versionMoto, String puissanceMoto, String modeleMoto) {
        MarqueMoto = marqueMoto;
        this.versionMoto = versionMoto;
        this.puissanceMoto = puissanceMoto;
        this.modeleMoto = modeleMoto;
    }

    public Long getIdMoto() {
        return idMoto;
    }

    public void setIdMoto(Long idMoto) {
        this.idMoto = idMoto;
    }

    public String getMarqueMoto() {
        return MarqueMoto;
    }

    public void setMarqueMoto(String marqueMoto) {
        MarqueMoto = marqueMoto;
    }

    public String getVersionMoto() {
        return versionMoto;
    }

    public void setVersionMoto(String versionMoto) {
        this.versionMoto = versionMoto;
    }

    public String getPuissanceMoto() {
        return puissanceMoto;
    }

    public void setPuissanceMoto(String puissanceMoto) {
        this.puissanceMoto = puissanceMoto;
    }

    public String getModeleMoto() {
        return modeleMoto;
    }

    public void setModeleMoto(String modeleMoto) {
        this.modeleMoto = modeleMoto;
    }

    @Override
    public String toString() {
        return "Moto{" +
                "idMoto=" + idMoto +
                ", MarqueMoto='" + MarqueMoto + '\'' +
                ", versionMoto='" + versionMoto + '\'' +
                ", puissanceMoto='" + puissanceMoto + '\'' +
                ", modeleMoto='" + modeleMoto + '\'' +
                '}';
    }
}
