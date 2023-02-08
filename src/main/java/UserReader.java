import java.util.ArrayList;

/* used to read an object of type User */
public class UserReader extends DataCSV<User> {

    public UserReader(ArrayList<User> list) {
        super(list);
    }

    /* it uses "extractFromCSV" method from DataCSV only changing the processing step */
    @Override
    protected void processData(ArrayList<User> list, String[] parameters) {

        int id = Integer.parseInt(parameters[0]);
        String name = parameters[1];

        String[] streamIDs = parameters[2].split(" ");
        ArrayList<Integer> listOfIds = new ArrayList<>();

        for(String words : streamIDs)
            listOfIds.add(Integer.parseInt(words));

        User user = new User(id, name, listOfIds);
        list.add(user);
    }
}
