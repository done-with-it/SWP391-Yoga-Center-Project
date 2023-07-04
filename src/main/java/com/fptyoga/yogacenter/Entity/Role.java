package com.fptyoga.yogacenter.Entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "role_table")
public class Role implements Serializable {

    @Id
    @Column(name = "role_id")
    private Long roleid;

    @Column(name = "role_name")
    private String roleName;

    // @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    // private Set<User> user;
}
