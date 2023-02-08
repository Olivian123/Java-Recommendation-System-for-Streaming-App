import java.util.ArrayList;

public class StreamerReader extends DataCSV<Streamer> {

    public StreamerReader (ArrayList<Streamer> list) {
        super(list);
    }


    @Override
    protected void processData(ArrayList<Streamer> list, String[] parameters) {

        int streamerType = Integer.parseInt(parameters[0]);
        int id = Integer.parseInt(parameters[1]);
        String name = parameters[2];

        Streamer streamer = new Streamer(streamerType, id, name);
        list.add(streamer);
    }
}
