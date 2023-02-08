/* used by CommandCaller*/
public interface Command {

    /* the classes that implement this interface, to keep the signature of "doCommand", */
    /* will store as attributes the parameters that they need to do their commands */

    /* every class that implements a command will overwrite this method*/
    void doCommand ();
}
