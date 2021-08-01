//package edu.vanderbilt.cs.cyberbull.services.auth.token;
//
//import edu.vanderbilt.cs.cyberbull.services.auth.user.User;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.time.LocalDate;
//import java.util.UUID;
//
//@Entity
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//public class ConfirmationToken {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String confirmationToken;
//    private LocalDate createdDate;
//    private ConfirmationTokenRepository confirmationTokenRepository;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setConfirmationToken(String confirmationToken) {
//        this.confirmationToken = confirmationToken;
//    }
//
//    public LocalDate getCreatedDate() {
//        return createdDate;
//    }
//
//    public void setCreatedDate(LocalDate createdDate) {
//        this.createdDate = createdDate;
//    }
//
//    public ConfirmationTokenRepository getConfirmationTokenRepository() {
//        return confirmationTokenRepository;
//    }
//
//    public void setConfirmationTokenRepository(ConfirmationTokenRepository confirmationTokenRepository) {
//        this.confirmationTokenRepository = confirmationTokenRepository;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
//    @JoinColumn(nullable = false, name = "user_id")
//    private User user;
//
//    public ConfirmationToken(User user) {
//        this.user = user;
//        this.createdDate = LocalDate.now();
//        this.confirmationToken = UUID.randomUUID().toString();
//    }
//
//    public String getConfirmationToken(){
//        return confirmationToken;
//    }
//    void deleteConfirmationToken(Long id){
//        confirmationTokenRepository.deleteById(id);
//    }
//}
