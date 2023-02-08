import java.util.ArrayList;
import java.util.List;

public class SurpriseCommand implements Command{

    private final List<User> users;
    private final List<Streamer> streamers;
    private final List<Stream> streams;
    String line;

    public SurpriseCommand(List<User> users, List<Streamer> streamers, List<Stream> streams, String line){

        this.users = users;
        this.streamers = streamers;
        this.streams = streams;
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

        int type;
        if(words.get(1).trim().equals("SONG"))
            type = 1;
        else if(words.get(1).trim().equals("PODCAST"))
            type = 2;
        else
            type = 3;

        List<Stream> streamsOfUnwatchedStreamers = new ArrayList<>();
        for(Stream stream : streams) {

            if(stream.getStreamType() != type) {
                continue;
            }

            int ok = 0;
            for(Stream watchedStream : accountStreams)
                if(watchedStream.getStreamerId() == stream.getStreamerId()) {
                    ok = 1;
                    break;
                }

            if (ok == 0) {
                streamsOfUnwatchedStreamers.add(stream);
            }

        }

        /* truncating the sorted list of unwatched streams */
        streamsOfUnwatchedStreamers.sort((o1, o2) -> {

            long difference = o1.getDateAdded() - o2.getDateAdded();
            if(difference < 24 * 3600 && difference > - 24 * 3600){

                if(o1.getNoOfStreams() > o2.getNoOfStreams())
                    return -1;
                else if(o1.getNoOfStreams() == o2.getNoOfStreams())
                    return 0;
                else
                    return -1;
            }
            else if(o1.getDateAdded() > o2.getDateAdded())
                return -1;
            else
                return 0;
        });

        if(streamsOfUnwatchedStreamers.size() > 3) {
            streamsOfUnwatchedStreamers = streamsOfUnwatchedStreamers.subList(0, 3);
        }

        System.out.print("[");
        for(Stream stream : streamsOfUnwatchedStreamers) {
            System.out.print(stream.toString(users, streamers));

            if(stream !=streamsOfUnwatchedStreamers.get(streamsOfUnwatchedStreamers.size() - 1))
                System.out.print(",");
        }
        System.out.println("]");
    }
}
