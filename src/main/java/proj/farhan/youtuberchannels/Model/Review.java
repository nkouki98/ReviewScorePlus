package proj.farhan.youtuberchannels.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Document(collection = "reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    private ObjectId id;

//    @NotBlank(message = "Review body is required")
    private String body;

    @NotNull(message = "Sentiment score is required")
    @Min(value = 0, message = "Sentiment score must be at least 0")
    @Max(value = 100, message = "Sentiment score must be at most 100")
    private Integer sentimentScore;

    public Review(String body, Integer sentimentScore) {
        this.sentimentScore = sentimentScore;
        this.body = body;
    }

    public Integer getSentimentScore() {
        return sentimentScore != null ? sentimentScore : 0; // Return 0 if sentimentScore is null
    }

    public void setSentimentScore(int i) {
        this.sentimentScore = sentimentScore;
    }
}
