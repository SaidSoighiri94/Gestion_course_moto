package soighiri.com.coursemoto.controller.api;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import soighiri.com.coursemoto.dto.UtilisateurDto;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class ApiInscriptionController {
    @PostMapping("/api/verification/inscription")
    public ResponseEntity<?> verification(@RequestBody @Valid UtilisateurDto utilisateurDto , BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            List<ObjectError> errors = new ArrayList<>();
            bindingResult.getAllErrors().forEach(error -> errors.add(error));
            return ResponseEntity.badRequest().body(errors);
        } else {
            return ResponseEntity.ok(new ArrayList<ObjectError>());
        }
    }


}
