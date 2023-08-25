package hu.demo.csv;

import hu.demo.csv.service.DataService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CsvDemoApplication
{

	public static void main(String[] args) {
		SpringApplication.run(CsvDemoApplication.class, args);
	}

	@Bean
	CommandLineRunner run(DataService dataService) {
		return args -> {
			dataService.processData("CSDemo.csv");
		};
	}
}
