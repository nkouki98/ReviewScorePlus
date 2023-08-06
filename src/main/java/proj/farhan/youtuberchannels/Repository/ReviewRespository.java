package proj.farhan.youtuberchannels.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import proj.farhan.youtuberchannels.Model.Review;

@Repository
public interface ReviewRespository extends MongoRepository<Review, ObjectId> {
//leverage built in tool for db operations methods like save, findById, findAll, delete
}
