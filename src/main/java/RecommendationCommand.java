import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RecommendationCommand implements Command{
    
    private final List<User> users;
    private final List<Streamer> streamers;
    private final List<Stream> streams;
    String line;
    
    public RecommendationCommand(List<User> users, List<Streamer> streamers, List<Stream> streams, String line){

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
        if(words.get(1).equals("SONG"))
            type = 1;
        else if(words.get(1).equals("PODCAST"))
            type = 2;
        else
            type = 3;

        /* getting a list of ids of the streamers, that the user follows  */
        List<Integer> userStreamerIds = getUserStreamersIds(accountStreams, type);

        /* getting the list of all the unwatched streams  */
        List<Stream> unwatched = getUnwatchedStreams(accountStreams, userStreamerIds);

        /* truncating the sorted list of unwatched streams */
        unwatched.sort(Comparator.comparingLong(o -> -o.getNoOfStreams()));
        if(unwatched.size() > 5)
            unwatched = unwatched.subList(0, 4);

        System.out.print("[");
        for(Stream stream : unwatched) {
            System.out.print(stream.toString(users, streamers));

            if(stream != unwatched.get(unwatched.size() - 1))
                System.out.print(",");
        }
        System.out.println("]");
    }

    private List<Stream> getUnwatchedStreams(List<Stream> accountStreams, List<Integer> userStreamerIds) {
        List<Stream> unwatched = new ArrayList<>();

        for(Stream stream : streams) {

            int ok1 = 0;

            for(int streamerId : userStreamerIds)
                if (stream.getStreamerId() == streamerId) {
                    ok1 = 1;
                    break;
                }

            if(ok1 == 1) {
                int ok2 = 0;

                for (Stream userStream : accountStreams)
                    if(stream == userStream) {
                        ok2 = 1;
                        break;
                    }

                if(ok2 == 0)
                    unwatched.add(stream);
            }
        }

        return unwatched;
    }

    private static List<Integer> getUserStreamersIds(List<Stream> accountStreams, int type) {
        List<Integer> userStreamerIds = new ArrayList<>();

        for(Stream stream : accountStreams) {

            int ok = 0;

            if(stream.getStreamType() != type)
                ok = 1;

            for(int streamerId : userStreamerIds)
                if(stream.getStreamerId() == streamerId) {
                    ok = 1;
                    break;
                }

            if(ok == 0)
                userStreamerIds.add(stream.getStreamerId());
        }
        return userStreamerIds;
    }
}
