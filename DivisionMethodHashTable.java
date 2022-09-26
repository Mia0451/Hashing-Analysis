import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

/**
 * h(K) = k mod M and M = 400.
 */
public class DivisionMethodHashTable {

    private static int M = 400;

    private List<DLinkedList<Customer>> data = new ArrayList<>(M);

    DivisionMethodHashTable() {
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
        return abs(customer.hashCode() % M);
    }
}
