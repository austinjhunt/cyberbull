//package edu.vanderbilt.cs.cyberbull.services.auth.token;
//
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//@AllArgsConstructor
//public class ConfirmationTokenService {
//    private final ConfirmationTokenRepository confirmationTokenRepository;
//    public ConfirmationTokenService(){
//        confirmationTokenRepository = new IConfirmationTokenRepository();
//    }
//    public void saveConfirmationToken(ConfirmationToken confirmationToken) {
//        confirmationTokenRepository.save(confirmationToken);
//    }
//    public void deleteConfirmationToken(Long id){
//        confirmationTokenRepository.deleteById(id);
//    }
//
//    public Optional<ConfirmationToken> findConfirmationTokenByToken(String token) {
//         return Optional.ofNullable(this.confirmationTokenRepository.findByConfirmationToken(token));
//    }
//}
