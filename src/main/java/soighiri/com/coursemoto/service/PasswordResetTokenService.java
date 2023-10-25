package soighiri.com.coursemoto.service;

import soighiri.com.coursemoto.model.PasswordResetToken;
import soighiri.com.coursemoto.model.Utilisateur;

public interface PasswordResetTokenService {
    PasswordResetToken createPasswordResetToken(Utilisateur utilisateur);
    PasswordResetToken findByToken(String token);
    boolean isTokenExpired(PasswordResetToken token);
    void sendPasswordResetEmail(String recipientAddress, String token);
    void deleteToken(PasswordResetToken token);

    // on peut rajouter d'autre methode  si necessaire

}
