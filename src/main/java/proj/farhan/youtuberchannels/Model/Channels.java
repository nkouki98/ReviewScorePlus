package proj.farhan.youtuberchannels.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "channels")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Channels {
    @Id
    private ObjectId id;
    private String channelTitle;
    private String channelId;
    private Integer subscriberCount;
    private String thumbnailUrl;
    private String channelUrl;
    private Integer confidenceMeasure;
    @DocumentReference
    private List<Review> reviews;

    public List<Review> getReviews() {
        return reviews;
    }

    public Object getChannelId() {
        return channelId;
    }

    public void setChannelId(String channel123) {
        this.channelId = channelId;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

}
