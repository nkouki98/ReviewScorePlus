package proj.farhan.youtuberchannels.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proj.farhan.youtuberchannels.Service.ChannelService;
import proj.farhan.youtuberchannels.Model.Review;
import proj.farhan.youtuberchannels.Service.ReviewService;
import proj.farhan.youtuberchannels.Service.SentimentAnalysisService;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    private ChannelService channelService;
    private final SentimentAnalysisService sentimentAnalysisService;

    public ReviewController(ReviewService reviewService, SentimentAnalysisService sentimentAnalysisService,
            ChannelService channelService) {
        this.reviewService = reviewService;
        this.sentimentAnalysisService = sentimentAnalysisService;
        this.channelService = channelService;
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Map<String, String> payload){
        String reviewText = payload.get("reviewBody");
        String channelId = payload.get("channelId");
        int sentimentScore = sentimentAnalysisService.analyzeSentiment(reviewText);

        // Call the reviewService to create the review
        Review createdReview = reviewService.createReview(reviewText, channelId, sentimentScore);
        //Calculate confidence measure here
//         channelService.updateConfidence(channelId);
        // Return the ResponseEntity with the createdReview and HttpStatus.CREATED
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
//        return new ResponseEntity<Review>(reviewService.createReview(payload.get("reviews"), payload.get("channelId")), HttpStatus.CREATED);

    }
}
