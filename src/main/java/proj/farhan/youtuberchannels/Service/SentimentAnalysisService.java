package proj.farhan.youtuberchannels.Service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import proj.farhan.youtuberchannels.RapidAPIConfig;

@Service
public class SentimentAnalysisService {
    private final RestTemplate restTemplate;
    private final RapidAPIConfig rapidAPIConfig;
    @Autowired
    public SentimentAnalysisService(RestTemplate restTemplate, RapidAPIConfig rapidAPIConfig){
        this.restTemplate = restTemplate;
        this.rapidAPIConfig = rapidAPIConfig;
    }

    public int analyzeSentiment(String reviewText) {
        String url = "https://japerk-text-processing.p.rapidapi.com/sentiment/";
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-host", "japerk-text-processing.p.rapidapi.com");
        headers.set("x-rapidapi-key", rapidAPIConfig.getApiKey());
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("text", reviewText);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        String responseBody = response.getBody();

        // Parse the response JSON
        JSONObject jsonObject = new JSONObject(responseBody);
        // Get the probability object
        JSONObject probabilityObject = jsonObject.getJSONObject("probability");
        // Get the "pos" value from the probability object
        double posProbability = probabilityObject.getDouble("pos");

        int sentimentScoreInt = (int) (posProbability * 100);
        System.out.println(sentimentScoreInt);
        System.out.println(response);
        return sentimentScoreInt;
    }
}



