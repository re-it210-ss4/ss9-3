import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"controller", "dto", "validation"})
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}