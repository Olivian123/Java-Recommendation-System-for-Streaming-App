import java.util.Iterator;
import java.util.List;

public class AccountManager {

    private static AccountManager instance = null;
    private UserIterator userIterator;
    private StreamerIterator streamerIterator;

    private AccountManager () { }

    public static AccountManager getInstance() {

        if (instance == null)
            instance = new AccountManager();

        return instance;
    }

    public void updateLists (List<User> users, List<Streamer> streamers) {

        userIterator = new UserIterator(users);
        streamerIterator = new StreamerIterator(streamers);

    }

    public Account getAccount (int id) {

        Iterator<User> userAccountIterator = userIterator.getAccountIterator();
        Account userAccount = getAccountById(userAccountIterator, id);
        if(userAccount != null)
            return userAccount;

        Iterator<Streamer> streamerAccountIterator = streamerIterator.getAccountIterator();
        return getAccountById(streamerAccountIterator, id);
    }

    private Account getAccountById (Iterator<? extends Account> iterator, int id) {

        while(iterator.hasNext()) {

            Account account = iterator.next();
            if(account.getId() == id)
                return account;
        }

        return null;
    }
}