package lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ShoeShopManagementSystemBackendApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(ShoeShopManagementSystemBackendApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
