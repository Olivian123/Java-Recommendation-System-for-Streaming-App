import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class AddStreamCommand implements Command {

    private final List<Stream> streams;
    private final String line;

    public AddStreamCommand (List<Stream> streams, String line) {

        this.streams = streams;
        this.line = line;
    }

    @Override
    public void doCommand() {

        List<String> words = new ArrayList<>(List.of(line.split(" ")));
        words.remove(1);

        /* modifying the list of words, knowing that, probably, the name of the song was divided by the split */
        addNameToParameterList(words);

        String[] parameters = new String[words.size()];
        parameters = words.toArray(parameters);

        /* creating the new stream from the parameters */
        Stream newStream = parseParametersFromAdd(parameters);

        streams.add(newStream);
    }

    /* parses the information and returns a stream object */
    private Stream parseParametersFromAdd(String[] parameters) {

        /* parsing the data from the command */
        int streamerId = Integer.parseInt(parameters[0]);
        int streamType = Integer.parseInt(parameters[1]);
        int id = Integer.parseInt(parameters[2]);
        int streamGenre = Integer.parseInt(parameters[3]);
        long length = Long.parseLong(parameters[4]);
        String name = parameters[5];

        /* a new stream was obviously never watched/seen */
        long noOfStreams = 0;

        /* the stream was added now */
        Instant now = Instant.now();
        long dateAdded = now.getEpochSecond();

        return new Stream(streamType, id, streamGenre, noOfStreams,
                streamerId, length, dateAdded, name);
    }

    /* puts the name of a stream from multiple strings, each representing */
    /* the words of the title, in to a single one  */
    private void addNameToParameterList(List<String> words) {

        int index = 0;
        int ok = 0;
        String name = null;
        for(String word : words) {

            /* we know that until the first word of the name there are only numbers, */
            /* so we increase the index until the first word is found */
            if ((!Character.isLetter(word.charAt(0)))) {
                index++;
            }
            /* from here we start to concatenate */
            else if (ok == 0){
                name = word.concat(" ");
                ok = 1;
            }
            else {
                word = word.trim();
                name = name.concat(word).concat(" ");
            }
        }

        if(name != null)
            name = name.trim();

        /* removing all the words after the index*/
        while(words.size() - 1 != index - 1) {
            words.remove(words.size() - 1);
        }

        /* adding the name, stored in a single string*/
        words.add(name);
    }
}
