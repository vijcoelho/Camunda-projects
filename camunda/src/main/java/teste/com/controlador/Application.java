package teste.com.controlador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
//[Form HTML] → (POST) → [Controller] → (RuntimeService) → [Camunda] → [Delegate] → [MongoDB]
  public static void main(String... args) {
    SpringApplication.run(Application.class, args);
  }

}