package proj.farhan.youtuberchannels.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proj.farhan.youtuberchannels.Service.ChannelService;
import proj.farhan.youtuberchannels.Model.Channels;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/channels")
public class ChannelsController {
    @Autowired
    private ChannelService channelService;
    @GetMapping
    public ResponseEntity<List<Channels>> getAllChannels() {
       return new ResponseEntity<List<Channels>>(channelService.allChannels(), HttpStatus.OK);

    }

    @GetMapping("/{channel_id}")
    public ResponseEntity<Optional<Channels>> getSingleChannel(@PathVariable String channel_id) {
        return new ResponseEntity<Optional<Channels>>(channelService.singleChannel(channel_id), HttpStatus.OK);
    }

}
