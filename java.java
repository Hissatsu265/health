import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class JavaWebApp {
    public static void main(String[] args) {
        SpringApplication.run(JavaWebApp.class, args);
    }
}

@RestController
class HelloController {
    @GetMapping("/")
    public String home() {
        return "Welcome to Java Web API!";
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Hello, " + name + "!";
    }
}
