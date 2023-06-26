// package com.fptyoga.yogacenter.Data;

// import java.time.LocalDate;
// import java.util.Random;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;

// import com.fptyoga.yogacenter.Entity.Role;
// import com.fptyoga.yogacenter.Entity.User;
// import com.fptyoga.yogacenter.repository.UserRepository;
// import com.github.javafaker.Faker;

// import jakarta.annotation.PostConstruct;

// @Component
// public class DataSeeder {
//     private final UserRepository userRepository;
//     private final Faker faker;


//     @Autowired
//     public DataSeeder(UserRepository userRepository) {
//         this.userRepository = userRepository;
//         this.faker = new Faker();
//     }

//     @PostConstruct
//     public void seedData() {


//         Random random = new Random();
        

//         Role role = new Role();
//         role.setRoleid(4l);
//         LocalDate hardcodedDate = LocalDate.of(2023, 6, 26);
//         for (int i = 0; i < 10; i++) {
//             int sex = random.nextInt(2);
//             User user = new User();
//             user.setFullname(faker.name().fullName());
//             user.setAddress(faker.address().fullAddress());
//             user.setPhone(faker.phoneNumber().cellPhone());
//             user.setDob(faker.date().birthday(20, 50).toString());
//             user.setEmail(faker.internet().emailAddress());
//             user.setImg(faker.avatar().image());
//             user.setPassword(faker.internet().password(false));
//             if(sex == 0){
//                 user.setGender("female");
//             } else user.setGender("male");

//             user.setRegistrationdate(hardcodedDate);
//             user.setRole(role);

//             userRepository.save(user);

//         }
//     }

// }
