import java.util.List;

public class ExerciseHashing {

    public static void main(String[] args) {
        List<Customer> customers = FileIO.readCustomers("Customer.csv");

        DivisionMethodHashTable divisionMethodHashTable = new DivisionMethodHashTable();
        MidSquareMethodHashTable midSquareMethodHashTable = new MidSquareMethodHashTable();
        DigitFoldingMethodHashTable digitFoldingMethodHashTable = new DigitFoldingMethodHashTable();
        MultiplicationMethodHashTable multiplicationMethodHashTable = new MultiplicationMethodHashTable();

        for (int i = 0; i < customers.size(); ++i) {
            divisionMethodHashTable.put(customers.get(i));
            midSquareMethodHashTable.put(customers.get(i));
            digitFoldingMethodHashTable.put(customers.get(i));
            multiplicationMethodHashTable.put(customers.get(i));
        }

        divisionMethodHashTable.printEachBucketDetails();
        midSquareMethodHashTable.printEachBucketDetails();
        digitFoldingMethodHashTable.printEachBucketDetails();
        multiplicationMethodHashTable.printEachBucketDetails();

        System.out.println("Division Method Hash Table collision is " +
                divisionMethodHashTable.getNumberOfCollision());
        System.out.println("Mid Square Method Hash Table collision is " +
                midSquareMethodHashTable.getNumberOfCollision());
        System.out.println("Digit Folding Method Hash Table collision is " +
                digitFoldingMethodHashTable.getNumberOfCollision());
        System.out.println("Multiplication Method Hash Table collision is " +
                multiplicationMethodHashTable.getNumberOfCollision());
    }
}
