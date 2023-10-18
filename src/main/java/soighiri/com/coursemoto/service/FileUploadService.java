package soighiri.com.coursemoto.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadService {
    // Methode pou telecharger un fichier
    String telechargerFichier(MultipartFile fichier, String nameFolder) throws IOException;

    //Methode pour Supprimer un fichier
    void deleteFile(String nameFile,String nameFolder) throws IOException;

}
