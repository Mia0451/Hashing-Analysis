import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

/**
 * Square the value of the key k i.e. k^2
 * Extract the middle r digits as the hash value.
 */
public class MidSquareMethodHashTable {

    private static int M = 400;

    private List<DLinkedList<Customer>> data = new ArrayList<>(M);

    MidSquareMethodHashTable() {
        for (int i = 0; i < M; ++i) {
            data.add(new DLinkedList<>());
        }
    }

    /**
     * Add {@param customer} into hash table.
     *
     * @param customer The entry to be added
     * @return true when add successfully (no same content entry in this hash table yet)
     */
    public boolean put(Customer customer) {
        int bucket = hashCustomer(customer);
        DLinkedList list = data.get(bucket);
        if (list == null) {
            list = new DLinkedList<Customer>();
            list.Append(customer);
            data.set(bucket, list);
        } else {
            if (list.Search(customer) != null) {
                return false;
            }
            list.Append(customer);
        }
        return true;
    }

    /**
     * Whether the hash table contains the entry.
     *
     * @param customer The entry we want to check the existence
     * @return true if contains, false otherwise
     */
    public boolean contains(Customer customer) {
        int bucket = hashCustomer(customer);
        DLinkedList list = data.get(bucket);
        return list != null && list.Search(customer) != null;
    }

    /**
     * Remove the entry from the hash table.
     *
     * @param customer The entry we want to remove
     * @return true if remove successfully, false otherwise
     */
    public boolean remove(Customer customer) {
        int bucket = hashCustomer(customer);
        DLinkedList list = data.get(bucket);
        if (list == null) {
            return false;
        }
        DLinkedList.Node node = list.Search(customer);
        if (node == null) {
            return false;
        }
        list.Delete(customer);
        return true;
    }

    /**
     * Get the number of collisions in this hash table.
     *
     * @return
     */
    public int getNumberOfCollision() {
        int ret = 0;
        for (int i = 0; i < data.size(); ++i) {
            int bucketItemCount = data.get(i).Count();
            if (bucketItemCount > 1) {
                ret += data.get(i).Count() - 1;
            }
        }
        return ret;
    }

    public void printEachBucketDetails() {
        for (int i = 0; i < data.size(); ++i) {
            System.out.print(data.get(i).Count() + " ");
        }
        System.out.println();
    }

    private int hashCustomer(Customer customer) {
        int square = customer.hashCode() * customer.hashCode();
        int digitsCount = digitCount(square);

        // We will get mid 3 digits
        int divider = 1;
        for (int i = 0; i < (digitsCount - 3)/2; ++i) {
            divider *= 10;
        }

        square /= divider;
        square %= 1000;

        return abs(square) % M;
    }

    /**
     * Return the number of digits in {@param a}.
     *
     * Say when {@param a} is 12, return 2; when {@param a} is 0, return 1, when {@param a} is 12356, return 5;
     * when {@param a} is 7463460, return 7 etc
     *
     * @param a
     * @return
     */
    private static int digitCount(int a) {
        if (a == 0) {
            return 1;
        }

        int count = 0;
        while (a != 0) {
            ++count;
            a /= 10;
        }

        return count;
    }
}
