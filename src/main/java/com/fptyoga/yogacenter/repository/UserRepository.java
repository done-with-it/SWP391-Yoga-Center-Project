package com.fptyoga.yogacenter.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fptyoga.yogacenter.Entity.User;



@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    List<User> findByRole_RoleidAndStatus(Long roleId, Boolean status);
    
    User findByEmailAndPassword(String email, String password);

    List<User> findByStatus(Boolean status);

    

    boolean existsByEmail(String email);

    @Query("SELECT MONTH(u.registrationdate) AS month, COUNT(u.userid) AS totalAmount " +
            "FROM User u " +
            "WHERE u.role.roleName = 'customers' AND u.status = true " +
            "GROUP BY MONTH(u.registrationdate)")
    List<Object[]> getMonthlyUser();
}
