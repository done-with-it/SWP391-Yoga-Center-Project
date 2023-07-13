package com.fptyoga.yogacenter.Entity;

import java.io.Serializable;
import java.time.LocalDate;

// import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_table")
public class User implements Serializable {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;

    @Column(name = "password")
    private String password;

    @Column(name = "fullname", columnDefinition = "NVARCHAR(50)")
    private String fullname;

    @Column(name = "email")
    private String email;

    @Column(name = "address", columnDefinition = "NVARCHAR(MAX)")
    private String address;

    @Column(name = "facebook")
    private String facebook;

    @Column(name = "instagram")
    private String instagram;

    @Column(name = "gender")
    private String gender;

    @Column(name = "phone")
    private String phone;

    @Column(name = "status")
    private boolean status;

    @Column(name = "img")
    @Lob
    private byte[] img;

    @Column(name = "date_of_birth")
    private String dob;

    @Column(name = "registration_date")
    private LocalDate registrationdate;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    // @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    // private Set<Class> class1;

    // @OneToMany(mappedBy = "user2", cascade = CascadeType.ALL)
    // private Set<Feedback> feedbacks;

    // @Override
    // public String getPassword() {
    // return password;
    // }

    // @Override
    // public String getUsername() {
    // return email;
    // }

    // @Override
    // public boolean isAccountNonExpired() {
    // return true;
    // }

    // @Override
    // public boolean isAccountNonLocked() {
    // return true;
    // }

    // @Override
    // public boolean isCredentialsNonExpired() {
    // return true;
    // }

    // @Override
    // public boolean isEnabled() {
    // return status;
    // }

    // @Override
    // public Collection<? extends GrantedAuthority> getAuthorities() {
    // List<GrantedAuthority> authorities = new ArrayList<>();
    // authorities.add(new SimpleGrantedAuthority("ROLE_admin"));
    // // Thêm các GrantedAuthority khác nếu cần thiết
    // return authorities;
    // }

}
