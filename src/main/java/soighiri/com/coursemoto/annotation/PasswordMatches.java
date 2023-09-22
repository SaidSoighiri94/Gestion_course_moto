package soighiri.com.coursemoto.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

// On définit l'annotation et on definit le validateur à utiliser
@Documented
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordMatches {

    // On affiche un message d'erreur s'ils sont pas identiques
    String message() default "Les mots de passe ne sont pas identiques";

    Class<?>[] groups() default {};
    Class<? extends Payload>[]  payload() default {};


}
