package proj.farhan.youtuberchannels.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import proj.farhan.youtuberchannels.Model.Channels;
import proj.farhan.youtuberchannels.Repository.ChannelsRepository;
import proj.farhan.youtuberchannels.Model.Review;
import proj.farhan.youtuberchannels.Repository.ReviewRespository;

import java.util.List;
import java.util.Optional;

@Service
public class ChannelService {
    @Autowired
    private ChannelsRepository channelsRepository;
    private ReviewRespository reviewRepository;
    @Autowired
    private MongoTemplate  mongoTemplate;
    public List<Channels> allChannels() {
        return channelsRepository.findAll();
    }

    public Optional<Channels> singleChannel(String channel_id){
        return channelsRepository.findChannelByChannelId(channel_id);
    }

    @Scheduled(fixedRate = 30000) // Run every 30s
    @Async
    public void updateConfidenceMeasuresBatch() {
        List<Channels> allChannels = mongoTemplate.findAll(Channels.class);

        for (Channels channel : allChannels) {
            List<Review> reviews = channel.getReviews();
            int totalSentimentScore = 0;
            int validReviewCount = 0;

            for (Review review : reviews) {
                if (review.getSentimentScore() != null) {
                    totalSentimentScore += review.getSentimentScore();
                    validReviewCount++;
                }
            }

            int averageSentimentScore = validReviewCount == 0 ? 0 : totalSentimentScore / validReviewCount;

            // Update the confidence measure for the current channel
            mongoTemplate.update(Channels.class)
                    .matching(Criteria.where("channelId").is(channel.getChannelId()))
                    .apply(new Update().set("confidenceMeasure", averageSentimentScore))
                    .first();
        }
    }
}

