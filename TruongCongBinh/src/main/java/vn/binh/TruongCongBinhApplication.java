package vn.binh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import vn.binh.Config.StorageProperties_23110184;
import vn.binh.service.IStorageService_23110184;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties_23110184.class)
public class TruongCongBinhApplication {

	public static void main(String[] args) {
		SpringApplication.run(TruongCongBinhApplication.class, args);
	}

	@Bean
	CommandLineRunner init(IStorageService_23110184 storageService) {
		return (args) -> {
			storageService.init();
		};
	}
}
