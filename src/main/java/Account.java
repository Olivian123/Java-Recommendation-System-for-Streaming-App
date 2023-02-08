import java.util.List;

public abstract class Account {

    public abstract List<Stream> getAccountStreams(List<Stream> streams);

    public abstract int getId ();

    public abstract String getName ();
}
