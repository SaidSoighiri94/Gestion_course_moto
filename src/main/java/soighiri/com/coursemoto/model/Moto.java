package soighiri.com.coursemoto.model;

import jakarta.persistence.*;
import nonapi.io.github.classgraph.utils.LogNode;
import org.springframework.web.multipart.MultipartFile;


@Entity
public class Moto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMoto;
     private String MarqueMoto;
     private String versionMoto;
     private String puissanceMoto;
     private String modeleMoto;
     private String imagePath;
     @Transient
     private MultipartFile multipartFile;

     public Moto(){

     }
    public Moto(String marqueMoto, String versionMoto, String puissanceMoto, String modeleMoto,String imagePath, MultipartFile multipartFile) {
        MarqueMoto = marqueMoto;
        this.versionMoto = versionMoto;
        this.puissanceMoto = puissanceMoto;
        this.modeleMoto = modeleMoto;
        this.imagePath = imagePath;
        this.multipartFile = multipartFile;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
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
