import java.util.ArrayList;
import java.util.List;

public class SurpriseCommand implements Command{

    private final List<Stream> streams;
    String line;

    public SurpriseCommand(List<Stream> streams, String line) {
        this.streams = streams;
        this.line = line;
    }

    @Override
    public void doCommand() {

        List<String> words = new ArrayList<>(List.of(line.split(" ")));
        words.remove(1);

        int id = Integer.parseInt(words.get(0));

        AccountManager accountManager = AccountManager.getInstance();

        Account account = accountManager.getAccount(id);
        List<Stream> accountStreams = account.getAccountStreams(streams);

        /* setting the type */
        int type;
        if(words.get(1).trim().equals("SONG"))
            type = 1;
        else if(words.get(1).trim().equals("PODCAST"))
            type = 2;
        else
            type = 3;

        /* getting the list of unwatched streams of the type */
        List<Stream> strOfUnStreamers = getStrOfUnStreamers(accountStreams, type);

        /* sorting and truncating the list of unwatched streams */
        strOfUnStreamers = sortStreams(strOfUnStreamers);

        /* printing */
        System.out.print("[");
        for(Stream stream : strOfUnStreamers) {
            System.out.print(stream);

            if(stream !=strOfUnStreamers.get(strOfUnStreamers.size() - 1))
                System.out.print(",");
        }
        System.out.println("]");
    }

    /* returns a list of unwatched streams of a certain type  */
    private List<Stream> getStrOfUnStreamers(List<Stream> accountStreams, int type) {
        List<Stream> streamsOfUnwatchedStreamers = new ArrayList<>();

        for(Stream stream : streams) {

            /* going over the streams that are not of the required type */
            if(stream.getStreamType() != type) {
                continue;
            }

            int ok = 0;
            /* checking if the stream belongs to a fallowed streamer */
            for(Stream watchedStream : accountStreams)
                if(watchedStream.getStreamerId() == stream.getStreamerId()) {
                    ok = 1;
                    break;
                }

            if (ok == 0) {
                streamsOfUnwatchedStreamers.add(stream);
            }
        }

        return streamsOfUnwatchedStreamers;
    }

    /* sorts and truncates the list */
    private List<Stream> sortStreams(List<Stream> strOfUnStreamers) {

        /* using lambda notation to sort first based on date than on number of listens */
        strOfUnStreamers.sort((o1, o2) -> {

            long difference = o1.getDateAdded() - o2.getDateAdded();

            /* checking if the dates are in the same day */
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

        /* truncating to length 3 */
        if(strOfUnStreamers.size() > 3) {
            strOfUnStreamers = strOfUnStreamers.subList(0, 3);
        }

        return strOfUnStreamers;
    }
}
