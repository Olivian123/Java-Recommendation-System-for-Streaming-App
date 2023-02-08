import java.util.List;

/* used as a "parent" for User and Streamer class */
/* helps with iterating through collections of those objects and abstraction */
public abstract class Account {

    public abstract List<Stream> getAccountStreams(List<Stream> streams);

    public abstract int getId ();

    public abstract String getName ();
}
