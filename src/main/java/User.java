import java.util.ArrayList;
import java.util.List;

public class User extends Account {

    private final int id;
    private final String name;
    private final List<Integer> streams;

    User (int id, String name, List<Integer> streamIDs) {
        this.id = id;
        this.name = name;
        this.streams = streamIDs;
    }

    /* returns the users streams */
    public List<Stream> getAccountStreams(List<Stream> streams) {

        List<Stream> userStreams = new ArrayList<>();

        for(Integer streamIds : this.streams)
            for(Stream stream : streams)
                if(streamIds == stream.getId()) {
                    userStreams.add(stream);
                    break;
                }

        return userStreams;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getName () {return name; }

    public  void updateStreams (int streamId) {

        streams.add(streamId);
    }
}
