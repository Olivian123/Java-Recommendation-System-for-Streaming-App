import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProiectPOO {

    public static void main(String[] args) {

        if (args == null) {
            System.out.println("Nothing to read here");
        } else {
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

            CommandCaller commandCaller = new CommandCaller();
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
                    commandCaller.addCommand(new PrintListCommand(streams, streamers, users, line));
                }

                if (line.contains("LISTEN")) {
                    commandCaller.addCommand(new ListenCommand(streams, users, line));
                }

                if (line.contains("RECOMMEND")) {
                    commandCaller.addCommand(new RecommendationCommand(users, streamers, streams, line));
                }

                if (line.contains("SURPRISE")) {
                    commandCaller.addCommand(new SurpriseCommand(users, streamers, streams, line));
                }
            }

            commandCaller.executeCommands();
        }
    }
}
