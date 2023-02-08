import java.util.ArrayList;
import java.util.List;

public class ListenCommand implements Command{

    private final List<Stream> streams;
    private final List<User> users;
    private final String line;

     public ListenCommand (List<Stream> streams, List<User> users, String line) {
         this.streams = streams;
         this.users = users;
         this.line = line;
     }

    @Override
    public void doCommand() {

        List<String> words = new ArrayList<>(List.of(line.split(" ")));
        words.remove(1);

        int id = Integer.parseInt(words.get(0));
        int streamId = Integer.parseInt(words.get(1).trim());

        AccountManager accountManager = AccountManager.getInstance();
        accountManager.updateLists(users, null);

        Account account = accountManager.getAccount(id);
        User user = (User) account;

        user.updateStreams(Integer.parseInt(words.get(1).trim()));

        for(Stream stream : streams) {
            if (stream.getId() == streamId) {
                stream.setNoOfStreams(stream.getNoOfStreams() + 1);
                break;
            }
        }
    }
}
