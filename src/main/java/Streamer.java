import java.util.ArrayList;
import java.util.List;

public class Streamer extends Account{

    int streamerType;
    int id;
    String name;

    Streamer(int streamerType, int id, String name) {
        this.streamerType = streamerType;
        this.id = id;
        this.name = name;
    }

    public String toString () {
        return streamerType + " " + id + " " + name;
    }

    public List<Stream> getAccountStreams(List<Stream> streams) {

        List<Stream> streamerStreams = new ArrayList<>();

        for(Stream stream : streams)
            if(stream.streamerId == id)
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
