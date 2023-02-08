import java.util.ArrayList;
import java.util.List;

public class DeleteStreamCommand implements Command {

    List<Stream> streams;
    String line;

    public DeleteStreamCommand (List<Stream> streams, String line) {

        this.streams = streams;
        this.line = line;
    }

    @Override
    public void doCommand() {

        List<String> words = new ArrayList<>(List.of(line.split(" ")));
        words.remove(1);

        int index = 0;
        for(Stream stream : streams) {
            if (stream.getId() == Integer.parseInt((words.get(1)).trim()))
                break;

            index++;
        }

        streams.remove(index);
    }
}
