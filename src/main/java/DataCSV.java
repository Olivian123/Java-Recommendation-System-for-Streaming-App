import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/* extracts the data from CSV files */
public abstract class DataCSV<T> {

    protected ArrayList<T> list;

    public DataCSV (ArrayList<T> list) {
        this.list = list;
    }

    /* extracts the data from the csv file, process it and stores it in the list */
    public final void extractFromCSV(String filePath) {

        File file = new File(filePath);
        /* checking if the file is empty */
        if(file.length() == 0) {
            return;
        }

        Scanner input;
        try {
            input = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        input.useDelimiter("\n");
        input.next(); /* go over header of the csv file */

        while(input.hasNext()) {
            /* making the delimiter */
            String delimiter =  String.valueOf(new char[]{'"', ',', '"'});
            String[] words = input.next().split(delimiter);

            /* removing '"' from each word of the line */
            for(int i = 0; i < words.length; i++)
                words[i] = words[i].replace('"', ' ').trim();

            /* actual processing of the data, specific to each type */
            processData(list, words);
        }

        input.close();
    }

    protected abstract void processData(ArrayList<T> list, String[] parameters);
    /* every subclass processes its data in a different way  */
}
