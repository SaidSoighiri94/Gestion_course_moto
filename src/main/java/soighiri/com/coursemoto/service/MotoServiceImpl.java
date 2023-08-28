package soighiri.com.coursemoto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soighiri.com.coursemoto.dto.MotoDto;
import soighiri.com.coursemoto.model.Moto;
import soighiri.com.coursemoto.repository.MotoRepository;

import java.util.List;

@Service
public class MotoServiceImpl implements MotoService {
    private MotoRepository motoRepository;

    @Autowired
    public MotoServiceImpl(MotoRepository motoRepository) {
        this.motoRepository = motoRepository;
    }

    @Override
    public Moto saveMotoFromMotoDto(MotoDto motoDto) {
        Moto moto = convertDtoToEntity(motoDto);

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
    public void deletemotoById(Long idMoto) {


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
            motoDto.setModeleMoto(moto.getModeleMoto());
        }

        // On retourne un MotoDto
        return motoDto;
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

