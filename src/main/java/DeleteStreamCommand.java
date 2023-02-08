import java.util.ArrayList;
import java.util.List;

/* removes a string from the list of streams */
public class DeleteStreamCommand implements Command {

    private final List<Stream> streams;
    private final String line;

    public DeleteStreamCommand (List<Stream> streams, String line) {

        this.streams = streams;
        this.line = line;
    }

    @Override
    public void doCommand() {

        List<String> words = new ArrayList<>(List.of(line.split(" ")));
        words.remove(1);

        /* searching for the index of the stream that needs to be removed  */
        int index = 0;
        for(Stream stream : streams) {
            if (stream.getId() == Integer.parseInt((words.get(1)).trim()))
                break;

            index++;
        }

        /* removing the stream from the list */
        streams.remove(index);
    }
}
