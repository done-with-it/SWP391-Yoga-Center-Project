package com.fptyoga.yogacenter.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fptyoga.yogacenter.Entity.User;
import java.util.List;



@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    List<User> findByRole_Roleid(Long roleId);
    User getUserByEmail(String email);
    User findUserByEmail(String email);
}
