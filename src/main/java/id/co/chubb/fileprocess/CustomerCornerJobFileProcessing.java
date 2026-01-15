package id.co.chubb.fileprocess;

import id.co.chubb.fileprocess.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class})
public class CustomerCornerJobFileProcessing {

	public static void main(String[] args) {
		SpringApplication.run(CustomerCornerJobFileProcessing.class, args);
	}

}
