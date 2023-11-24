package soighiri.com.coursemoto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soighiri.com.coursemoto.dto.EcurieDto;
import soighiri.com.coursemoto.dto.MotoDto;
import soighiri.com.coursemoto.model.Ecurie;
import soighiri.com.coursemoto.model.Moto;
import soighiri.com.coursemoto.repository.EcurieRepository;
import soighiri.com.coursemoto.repository.MotoRepository;

import java.io.IOException;
import java.util.List;

@Service
public class MotoServiceImpl implements MotoService {
    private final MotoRepository motoRepository;
    private final FileUploadService fileUploadService;
    private final EcurieRepository ecurieRepository;
    @Autowired
    public MotoServiceImpl(MotoRepository motoRepository, FileUploadService fileUploadService, EcurieRepository ecurieRepository) {
        this.motoRepository = motoRepository;
        this.fileUploadService = fileUploadService;
        this.ecurieRepository = ecurieRepository;
    }



    @Override
    public Moto saveMotoFromMotoDto(MotoDto motoDto) throws IOException{


            Moto moto = convertDtoToEntity(motoDto);
            // Si une nouvelle image est fournie, téléchargez-la
            if (motoDto.getFichierImage() != null){
                String nomFichierImage = fileUploadService.telechargerFichier(motoDto.getFichierImage(),"moto");

                //supprimer l'image si necessaire
                if(moto.getImagePath() != null) {
                    fileUploadService.deleteFile(moto.getImagePath(),"moto");
                }
                moto.setImagePath(nomFichierImage);
            }

            // Sauvegarder la moto dans la base de données
            return motoRepository.save(moto);

    }
    @Override
    public Moto getMotoById(Long idMoto) {


        // Récupérer la moto par son ID
        return motoRepository.findById(idMoto).orElse(null) ;
    }

    @Override
    public List<Moto> getAllMotos() {


        // Récupérer la liste de toutes les motos
        return motoRepository.findAll();
    }

    @Override
    public Moto updateMotoFromMotoDto(MotoDto motoDto) {
        Moto moto = convertDtoToEntity(motoDto);

        // Mettre à jour la moto dans la base de données
        return motoRepository.save(moto);
    }
    @Override
    public void deleteMotoById(Long idMoto) {

        // Supprimer la moto par son ID
        motoRepository.deleteById(idMoto);
    }

    @Override
    public MotoDto convertEntityToDto(Moto moto) {

        // Convertir l'entité Moto en DTO MotoDto
        MotoDto motoDto = new MotoDto();

            if(moto !=null){
                motoDto.setIdMoto(moto.getIdMoto());
                motoDto.setMarqueMoto(moto.getMarqueMoto());
                motoDto.setVersionMoto(moto.getVersionMoto());
                motoDto.setPuissanceMoto(moto.getPuissanceMoto());
                motoDto.setModeleMoto(moto.getModeleMoto());
                motoDto.setImagePath(moto.getImagePath());
            }

            // On retourne un MotoDto
            return motoDto;
    }

    @Override
    public void addMotoToEcurie(Long idMoto, Long idEcurie) {
        Moto moto = motoRepository.findById(idMoto).orElseThrow(() -> new RuntimeException("Moto non trouvée"));
        Ecurie ecurie = ecurieRepository.findById(idEcurie).orElseThrow(() -> new RuntimeException("Ecurie non trouvée"));
        moto.setEcurie(ecurie);
        ecurie.getMotos().add(moto);
        ecurieRepository.save(ecurie);
    }

    // Méthode pour convertir un DTO MotoDto en entité Moto
    private Moto convertDtoToEntity(MotoDto motoDto) {
        Moto moto = new Moto();
        moto.setIdMoto(motoDto.getIdMoto());
        moto.setMarqueMoto(motoDto.getMarqueMoto());
        moto.setVersionMoto(motoDto.getVersionMoto());
        moto.setModeleMoto(motoDto.getModeleMoto());
        moto.setPuissanceMoto(motoDto.getPuissanceMoto());

        // Retourner l'entité Moto remplie

        return moto;
    }

}

