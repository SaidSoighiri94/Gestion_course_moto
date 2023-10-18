package soighiri.com.coursemoto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    private final ResourceLoader resourceLoader;
    @Autowired
    public FileUploadServiceImpl(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    @Override
    public String telechargerFichier(MultipartFile fichier, String nameFolder) throws IOException {
        if (fichier.isEmpty()) {
            throw new IllegalArgumentException("Le fichier est vide");
        }
        String nomFichierUnique = UUID.randomUUID().toString() +"_" + fichier.getOriginalFilename();
        String cheminDossier = "src/main/resources/static/images/moto" + nameFolder +"/";
        String cheminComplet = cheminDossier + nomFichierUnique;
        File dossier = new File(cheminDossier);
        if (!dossier.exists()) {
            dossier.mkdirs(); // Creer le dossier s'il n'existe pas
        }
        try(FileOutputStream fileOutputStream = new FileOutputStream(cheminComplet)) {
            fileOutputStream.write(fichier.getBytes());
        }
        return nomFichierUnique;
    }
    @Override
    public void deleteFile(String nameFile, String nameFolder) throws  IOException {
        String cheminDossier = "src/main/resources/static/images/moto" + nameFolder +"/";
        String cheminComplet = cheminDossier + nameFile;
        ClassPathResource resource = new ClassPathResource(cheminComplet);
        if(resource.exists()) {
            resource.getFile().delete();
        }

    }
}
