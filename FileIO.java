import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class FileIO {
    public static List<Customer> readCustomers(String fileName) {
        List<Customer> customers = new ArrayList<>();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while (line != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, ",");
                customers.add(new Customer(tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken()));
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }

        return customers;
    }
}
