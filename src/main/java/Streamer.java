import java.util.ArrayList;
import java.util.List;

public class Streamer extends Account{

    private final int streamerType;
    private final int id;
    private final String name;

    Streamer(int streamerType, int id, String name) {
        this.streamerType = streamerType;
        this.id = id;
        this.name = name;
    }

    /* returns the streamers streams */
    @Override
    public List<Stream> getAccountStreams(List<Stream> streams) {

        List<Stream> streamerStreams = new ArrayList<>();

        for(Stream stream : streams)
            if(stream.getStreamerId() == id)
                streamerStreams.add(stream);

        return streamerStreams;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

}
