import java.util.Comparator;

public class CustomerLoyaltyComparator implements Comparator<Customer> {
    @Override
    public int compare(Customer c1, Customer c2) {
        return Integer.compare(c1.getLoyaltyPoints(), c2.getLoyaltyPoints());
    }
}