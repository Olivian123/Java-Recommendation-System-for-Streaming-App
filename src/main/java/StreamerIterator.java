import java.util.Iterator;
import java.util.List;

/* used to iterate through a collection of streamers  */
public class StreamerIterator implements AccountIterator{

    private final List<Streamer> streamers;

    public StreamerIterator (List<Streamer> streamers){
        this.streamers = streamers;
    }

    @Override
    public Iterator<Streamer> getAccountIterator() {

        return streamers.iterator();
    }
}
