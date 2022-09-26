public class Customer {
    String lastName;
    String firstName;
    String id;

    public Customer(String lastName, String firstName, String id) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.id = id;
    }

    // Override to remove Java built in hash for the object.
    // Use `multiple` try to make return a large integer.
    public int hashCode() {
        int ret = 0;
        int multiple = 1;
        for (int i = 0; i < lastName.length(); ++i) {
            ret += lastName.charAt(i) * multiple;
            multiple *= 100;
        }
        for (int i = 0; i < firstName.length(); ++i) {
            ret += firstName.charAt(i) * multiple;
            multiple *= 100;
        }
        for (int i = 0; i < id.length(); ++i) {
            ret += id.charAt(i) * multiple;
            multiple *= 100;
        }
        return ret;
    }
}
