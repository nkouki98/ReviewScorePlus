package proj.farhan.youtuberchannels;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RapidAPIConfig {
    @Value("${rapidapi.api-key}")
    private String apiKey;

    public String getApiKey() {
        return apiKey;
    }
}
