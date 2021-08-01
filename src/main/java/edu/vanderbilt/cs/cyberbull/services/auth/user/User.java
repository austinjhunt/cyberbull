//package edu.vanderbilt.cs.cyberbull.services.auth.user;
//
//import edu.vanderbilt.cs.cyberbull.services.auth.entity.UserRole;
//import lombok.*;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import java.util.Collection;
//import java.util.Collections;
//
//@Getter
//@Setter
//@Builder
//@EqualsAndHashCode
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity(name = "Users")
//public class User implements UserDetails {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String name;
//    private String surname;
//    private String email;
//    private String password;
//    @Builder.Default
//    private UserRole userRole = UserRole.USER;
//
//    @Builder.Default
//    private Boolean locked = false;
//
//    @Builder.Default
//    private Boolean enabled = false;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//
//        final SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userRole.name());
//        return Collections.singletonList(simpleGrantedAuthority);
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return email;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return !locked;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return enabled;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getSurname() {
//        return surname;
//    }
//
//    public void setSurname(String surname) {
//        this.surname = surname;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public UserRole getUserRole() {
//        return userRole;
//    }
//
//    public void setUserRole(UserRole userRole) {
//        this.userRole = userRole;
//    }
//
//    public Boolean getLocked() {
//        return locked;
//    }
//
//    public void setLocked(Boolean locked) {
//        this.locked = locked;
//    }
//
//    public Boolean getEnabled() {
//        return enabled;
//    }
//
//    public void setEnabled(Boolean enabled) {
//        this.enabled = enabled;
//    }
//}