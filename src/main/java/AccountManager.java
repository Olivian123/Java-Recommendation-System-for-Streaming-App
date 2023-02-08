import java.util.Iterator;
import java.util.List;

/*  manages the accounts */
public class AccountManager {

    /* only one instance is needed */
    private static AccountManager instance = null;

    /* using Iterator to easily go through separate collections of data  */
    private UserIterator userIterator;
    private StreamerIterator streamerIterator;

    private AccountManager () { }

    public static AccountManager getInstance() {

        if (instance == null)
            instance = new AccountManager();

        return instance;
    }

    /* it is used only once, but it could've been used in case there were commands */
    /* that altered the lists of users and streamers */
    public void setAccountsLists(List<User> users, List<Streamer> streamers) {

        userIterator = new UserIterator(users);
        streamerIterator = new StreamerIterator(streamers);

    }

    /* retrieves the account based on the given id */
    public Account getAccount (int id) {

        /* using user iterator to search in users list */
        Iterator<User> userAccountIterator = userIterator.getAccountIterator();
        Account userAccount = getAccountById(userAccountIterator, id);

        /* if the id corresponded to a users, it is returned*/
        if(userAccount != null)
            return userAccount;

        /* it goes the same for the streamers list*/
        Iterator<Streamer> streamerAccountIterator = streamerIterator.getAccountIterator();
        return getAccountById(streamerAccountIterator, id);
    }

    /* returns an account based on the id and a list */
    /* it those not matter whether the list is of users of streamers */
    private Account getAccountById (Iterator<? extends Account> iterator, int id) {

        while(iterator.hasNext()) {

            Account account = iterator.next();
            if(account.getId() == id)
                return account;
        }

        return null;
    }
}