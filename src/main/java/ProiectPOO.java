import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProiectPOO {

    public static void main(String[] args) {

        if (args == null) {
            System.out.println("Nothing to read here");
            return;
        }

        /* storing the data from the CSV files in to their respective classes */
        ArrayList<Streamer> streamers = new ArrayList<>();
        StreamerReader streamerReader = new StreamerReader(streamers);
        streamerReader.extractFromCSV("src/main/resources/".concat(args[0]));

        ArrayList<Stream> streams = new ArrayList<>();
        StreamReader streamReader = new StreamReader(streams);
        streamReader.extractFromCSV("src/main/resources/".concat(args[1]));

        ArrayList<User> users = new ArrayList<>();
        UserReader userReader = new UserReader(users);
        userReader.extractFromCSV("src/main/resources/".concat(args[2]));

        Scanner input;
        try {
            input = new Scanner(new File("src/main/resources/".concat(args[3])));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        /* using a command caller to mediate the command calls */
        CommandCaller commandCaller = new CommandCaller();

        /* initializing the account manager and setting the lists for later use */
        AccountManager accountManager = AccountManager.getInstance();
        /* gives the objects a place where they can find the accounts reducing dependencies */
        accountManager.setAccountsLists(users, streamers);

        input.useDelimiter("\n");

        while (input.hasNext()) {

            String line = input.next();

            if (line.contains("ADD")) {
                commandCaller.addCommand(new AddStreamCommand(streams, line));
            }

            if (line.contains("DELETE")) {
                commandCaller.addCommand(new DeleteStreamCommand(streams, line));
            }

            if (line.contains("LIST") && !line.contains("LISTEN")) {
                commandCaller.addCommand(new PrintListCommand(streams, line));
            }

            if (line.contains("LISTEN")) {
                commandCaller.addCommand(new ListenCommand(streams, line));
            }

            if (line.contains("RECOMMEND")) {
                commandCaller.addCommand(new RecommendationCommand(streams, line));
            }

            if (line.contains("SURPRISE")) {
                commandCaller.addCommand(new SurpriseCommand(streams, line));
            }
        }

        /* the actual execution of the commands */
        commandCaller.executeCommands();
    }
}
