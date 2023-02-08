import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PrintListCommand implements Command{

    List<Stream> streams;
    List<Streamer> streamers;
    List<User> users;
    String line;

    public PrintListCommand(List<Stream> streams,List<Streamer> streamers, List<User> users, String line) {
        this.streams = streams;
        this.streamers =streamers;
        this.users = users;
        this.line = line;
    }

    @Override
    public void doCommand() {
        List<String> words = new ArrayList<>(List.of(line.split(" ")));
        words.remove(1);

        int id = Integer.parseInt(words.get(0));

        AccountManager accountManager = AccountManager.getInstance();
        accountManager.updateLists(users, streamers);

        Account account = accountManager.getAccount(id);
        List<Stream> accountStreams = account.getAccountStreams(streams);

        if(account instanceof Streamer)
            Collections.reverse(accountStreams);

        System.out.print("[");
        for (Stream stream : accountStreams) {

            System.out.print(stream.toString(users, streamers));

            if(stream != accountStreams.get(accountStreams.size() - 1))
                System.out.print(",");
        }
        System.out.println("]");
    }

}
