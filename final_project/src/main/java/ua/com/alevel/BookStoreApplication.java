package ua.com.alevel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ua.com.alevel.persistence.entity.user.Admin;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.persistence.repository.user.AdminRepository;
import ua.com.alevel.persistence.repository.user.PersonalRepository;

@SpringBootApplication
public class BookStoreApplication {

    @Autowired // 1 - @Autowired on field
    private PersonalRepository personalRepository;

    private BCryptPasswordEncoder passwordEncoder;

    private final AdminRepository adminRepository;

    // inject on constructor
    public BookStoreApplication(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Autowired // 1 - @Autowired on setter
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(BookStoreApplication.class, args);
    }

//    @EventListener(ApplicationReadyEvent.class)
    public void testListener() {
        String pass = "123qwerty!";
        System.out.println("pass = " + pass);

        String hash1 = passwordEncoder.encode(pass);
        String hash2 = passwordEncoder.encode(pass);
        String hash3 = passwordEncoder.encode(pass);

        System.out.println("hash1 = " + hash1);
        System.out.println("hash2 = " + hash2);
        System.out.println("hash3 = " + hash3);

        Admin admin = new Admin();
        admin.setEmail("admin@mail.com");
        admin.setPassword(hash1);

        Personal personal = new Personal();
        personal.setEmail("personal@mail.com");
        personal.setPassword(hash2);

//        adminRepository.save(admin);
        personalRepository.save(personal);
    }
}
