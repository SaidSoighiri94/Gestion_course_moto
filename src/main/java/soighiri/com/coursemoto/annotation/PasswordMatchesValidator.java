package soighiri.com.coursemoto.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import soighiri.com.coursemoto.dto.UtilisateurDto;

//On implemnte le validateur pour cette annotation
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches,Object> {
    @Override
    public void initialize(PasswordMatches ConstraintAnnotaion) {

    }
    @Override
    public boolean isValid(Object ob, ConstraintValidatorContext context){

        // on convertit l'objet en UtilisateurDto
        UtilisateurDto utilisateurDto = (UtilisateurDto) ob;

        //Puis on compare les 2 mots de passes
        return utilisateurDto.getMdp1NonEncoder().equals(utilisateurDto.getMdp2NonEncoder());
    }
}
