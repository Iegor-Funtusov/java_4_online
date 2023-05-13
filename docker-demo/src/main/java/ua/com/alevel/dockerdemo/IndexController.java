package ua.com.alevel.dockerdemo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Controller
public class IndexController {

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";

    @GetMapping
    public String index() {
        return "index";
    }

    @PostMapping("/upload")
    public String uploadImage(Model model, @RequestParam("image") MultipartFile file) throws IOException {

        File file1 = new File(file.getOriginalFilename());
        file1.createNewFile();

        try (OutputStream os = new FileOutputStream(file1)) {
            os.write(file.getBytes());
        } catch (IOException e) {
            System.out.println("e = " + e.getMessage());
        }

        model.addAttribute("msg", "Uploaded images: " + file.getOriginalFilename());
        return "index";
    }
}
