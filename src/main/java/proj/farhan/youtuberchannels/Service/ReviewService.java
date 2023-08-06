package proj.farhan.youtuberchannels.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import proj.farhan.youtuberchannels.Model.Channels;
import proj.farhan.youtuberchannels.Repository.ReviewRespository;
import proj.farhan.youtuberchannels.Model.Review;

@Service
public class ReviewService {
    @Autowired
    private ReviewRespository reviewRespository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public Review createReview(String reviewBody, String channelId, int sentimentScore) {
        Review review = reviewRespository.insert(new Review(reviewBody, sentimentScore));
//        review.setSentimentScore(sentimentScore);
        mongoTemplate.update(Channels.class)
                .matching(Criteria.where("channelId").is(channelId))
                .apply(new Update().push("reviews").value(review))
                .first();
        return review;
    }


}