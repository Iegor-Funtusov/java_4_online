package ua.com.alevel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ua.com.alevel.elastic.document.PostIndex;
import ua.com.alevel.persistence.entity.user.Admin;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.repository.user.AdminRepository;
import ua.com.alevel.persistence.repository.user.PersonalRepository;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.Optional;

@EnableScheduling
@SpringBootApplication
public class MessageStareApplication {

    @Value("${initPersonals}")
    private boolean initPersonals;

    private final AdminRepository adminRepository;
    private final PersonalRepository personalRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ElasticsearchOperations elasticsearchOperations;

    public MessageStareApplication(
            AdminRepository adminRepository,
            PersonalRepository personalRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            ElasticsearchOperations elasticsearchOperations) {
        this.adminRepository = adminRepository;
        this.personalRepository = personalRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.elasticsearchOperations = elasticsearchOperations;
    }

    public static void main(String[] args) {
        SpringApplication.run(MessageStareApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() throws IOException {
        String email = "admin@gmail.com";
        String password = "12345678";
        Optional<User> optionalAdmin = adminRepository.findByEmail(email);
        if (optionalAdmin.isEmpty()) {
            Admin admin = new Admin();
            admin.setEmail(email);
            admin.setPassword(bCryptPasswordEncoder.encode(password));
            adminRepository.save(admin);
        }
        if (initPersonals) {
            System.out.println("start init");
            for (int i = 0; i < 111; i++) {
                Personal personal = new Personal();
                personal.setPassword(bCryptPasswordEncoder.encode(password));
                personal.setEmail("personal" + i + "@gmail.com");
                personalRepository.save(personal);
            }
            System.out.println("finish init");
        }
    }

    @PreDestroy
    public void resetElastic() {
        elasticsearchOperations.indexOps(PostIndex.class).delete();
    }
}
