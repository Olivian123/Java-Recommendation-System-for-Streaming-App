import java.util.ArrayList;
import java.util.List;

/* adds the id of the listened stream in to the list of stream ids of the user */
public class ListenCommand implements Command{

    private final List<Stream> streams;
    private final String line;

     public ListenCommand (List<Stream> streams, String line) {
         this.streams = streams;
         this.line = line;
     }

    @Override
    public void doCommand() {

        List<String> words = new ArrayList<>(List.of(line.split(" ")));
        words.remove(1);

        /* getting the id of the user */
        int id = Integer.parseInt(words.get(0));
        /* getting the stremId */
        int streamId = Integer.parseInt(words.get(1).trim());

        AccountManager accountManager = AccountManager.getInstance();

        /* getting the user account */
        Account account = accountManager.getAccount(id);
        User user = (User) account;

        /* updating the user list of listened streams */
        user.updateStreams(streamId);

        /* increasing the number of listening of the stream */
        for(Stream stream : streams) {
            if (stream.getId() == streamId) {
                stream.setNoOfStreams(stream.getNoOfStreams() + 1);
                break;
            }
        }
    }
}
