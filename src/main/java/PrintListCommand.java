import java.util.ArrayList;
import java.util.List;

/* prints lists the user or streamer strems  */
public class PrintListCommand implements Command{

    private final List<Stream> streams;
    private final String line;

    public PrintListCommand(List<Stream> streams, String line) {
        this.streams = streams;
        this.line = line;
    }

    @Override
    public void doCommand() {

        List<String> words = new ArrayList<>(List.of(line.split(" ")));
        words.remove(1);

        /* getting the account id*/
        int id = Integer.parseInt(words.get(0));

        /* getting the account based on the id */
        AccountManager accountManager = AccountManager.getInstance();
        Account account = accountManager.getAccount(id);

        /* getting the accounts streams */
        List<Stream> accountStreams = account.getAccountStreams(streams);

        /* I will let this here as reminder */
        /* (I don't know why , but) if the id is of a streamer, */
        /* the streams need to be listed in reversed order */
        /* if(account instanceof Streamer)
            Collections.reverse(accountStreams); */

        /* actual printing , in json form */
        System.out.print("[");
        for (Stream stream : accountStreams) {

            System.out.print(stream);

            if(stream != accountStreams.get(accountStreams.size() - 1))
                System.out.print(",");
        }
        System.out.println("]");
    }

}
