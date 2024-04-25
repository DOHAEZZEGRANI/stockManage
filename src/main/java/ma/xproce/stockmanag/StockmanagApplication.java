package ma.xproce.stockmanag;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StockmanagApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(StockmanagApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
    }
}
