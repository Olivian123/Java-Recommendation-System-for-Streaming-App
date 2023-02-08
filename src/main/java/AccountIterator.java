import java.util.Iterator;

/* interface used in the implementation of Iterator */
public interface AccountIterator {
    Iterator<? extends Account> getAccountIterator();

}
