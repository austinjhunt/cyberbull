//package edu.vanderbilt.cs.cyberbull.services.auth.user;
//
//import edu.vanderbilt.cs.cyberbull.services.auth.email.EmailSenderService;
//import edu.vanderbilt.cs.cyberbull.services.auth.token.ConfirmationToken;
//import edu.vanderbilt.cs.cyberbull.services.auth.token.ConfirmationTokenService;
//import lombok.AllArgsConstructor;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.text.MessageFormat;
//import java.util.Optional;
//
//@Service
//@AllArgsConstructor
//public class UserService implements UserDetailsService {
//    private final UserRepository userRepository;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//    private final ConfirmationTokenService confirmationTokenService;
//    private final EmailSenderService emailSenderService;
//    public UserService(){
//        this.userRepository = new IUserRepository();
//        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        this.confirmationTokenService = new ConfirmationTokenService();
//        this.emailSenderService = new EmailSenderService();
//    }
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        final Optional<User> optionalUser = userRepository.findByEmail(email);
//
//        if (optionalUser.isPresent()) {
//            return optionalUser.get();
//        }
//        else {
//            throw new UsernameNotFoundException(MessageFormat.format("User with email {0} cannot be found.", email));
//        }
//    }
//
//    public void signUpUser(User user) {
//        final String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
//        user.setPassword(encryptedPassword);
//        final User createdUser = userRepository.save(user);
//        final ConfirmationToken confirmationToken = new ConfirmationToken(user);
//        confirmationTokenService.saveConfirmationToken(confirmationToken);
//    }
//    public void confirmUser(ConfirmationToken confirmationToken) {
//        final User user = confirmationToken.getUser();
//        user.setEnabled(true);
//        userRepository.save(user);
//        confirmationTokenService.deleteConfirmationToken(confirmationToken.getId());
//
//    }
//    public void sendConfirmationMail(String userMail, String token) {
//
//        final SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setTo(userMail);
//        mailMessage.setSubject("Mail Confirmation Link!");
//        mailMessage.setFrom("<MAIL>");
//        mailMessage.setText(
//                "Thank you for registering. Please click on the below link to activate your account." + "http://localhost:8080/sign-up/confirm?token="
//                        + token);
//
//        emailSenderService.sendEmail(mailMessage);
//    }
//
//}
