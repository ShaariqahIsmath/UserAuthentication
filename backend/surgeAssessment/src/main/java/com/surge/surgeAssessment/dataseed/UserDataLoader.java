package com.surge.surgeAssessment.dataseed;


import com.surge.surgeAssessment.dao.User;
import com.surge.surgeAssessment.dao.UserType;
import com.surge.surgeAssessment.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserDataLoader implements CommandLineRunner {

    @Autowired
    UsersRepository userRepository;

    @Override
    public void run(String... args) {
        loadAdminUserData();
    }

    private void loadAdminUserData() {
        if (userRepository.count() == 0) {
            User adminUser = new User();
            adminUser.setFirstName("Shaariqah");
            adminUser.setLastName("Ismath");
            adminUser.setEmail("shaariqah@gmail.com");
            adminUser.setPassword(new BCryptPasswordEncoder().encode("test123"));
            adminUser.setDateOfBirth("2001-03-23");;
            adminUser.setMobileNumber("0773038486");
            adminUser.setStatus(true);
            adminUser.setAccountType(UserType.ADMIN.getUserValue());
            userRepository.save(adminUser);
        }
    }
}
