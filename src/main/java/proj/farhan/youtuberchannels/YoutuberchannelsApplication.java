package proj.farhan.youtuberchannels;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class YoutuberchannelsApplication {

	public static void main(String[] args) {
		SpringApplication.run(YoutuberchannelsApplication.class, args);
	}

}
