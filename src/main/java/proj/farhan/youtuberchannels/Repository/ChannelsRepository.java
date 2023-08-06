package proj.farhan.youtuberchannels.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import proj.farhan.youtuberchannels.Model.Channels;

import java.util.Optional;

@Repository
public interface ChannelsRepository extends MongoRepository<Channels, ObjectId> {
    Optional<Channels> findChannelByChannelId(String channel_id);
}
