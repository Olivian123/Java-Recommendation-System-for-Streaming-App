import java.util.Iterator;
import java.util.List;

/* used to iterate through a collection of users */
public class UserIterator implements AccountIterator{

    private final List<User> users;

    public UserIterator(List<User> users){
        this.users = users;
    }

    @Override
    public Iterator<User> getAccountIterator() {

        return users.iterator();
    }
}
