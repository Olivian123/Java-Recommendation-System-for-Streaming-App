import java.util.ArrayList;

public class StreamReader extends DataCSV<Stream> {

    public StreamReader (ArrayList<Stream> list) {
        super(list);
    }

    @Override
    protected void processData(ArrayList<Stream> list, String[] parameters) {

        int streamType = Integer.parseInt(parameters[0]);
        int id = Integer.parseInt(parameters[1]);
        int streamGenre = Integer.parseInt(parameters[2]);
        long noOfStreams = Long.parseLong(parameters[3]);
        int streamerId = Integer.parseInt(parameters[4]);
        long length = Long.parseLong(parameters[5]);
        long dateAdded = Long.parseLong(parameters[6]);
        String name = parameters[7];

        Stream stream = new Stream(streamType, id, streamGenre, noOfStreams,
                streamerId, length, dateAdded, name);
        list.add(stream);
    }
}
