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
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_table")
@ToString
public class User implements Serializable {

    public User(String email2) {
    }

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

    @Column(name = "gender")
    private String gender;

    
    // @Pattern(regexp = "^0\\d{8,11}$", message = "Phone must start with 0 and have 9 to 12 digits")
    // @Size(min = 9, max = 12, message = "Phone must have 9 to 12 digits")
    @Column(name = "phone")
    private String phone;

    @Column(name = "facebook")
    private String facebook;

    @Column(name = "instagram")
    private String instagram;

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

    

}
