import java.util.*;

//-------------------------------------
// Customer Class
//-------------------------------------
class Customer {
    private String customerId;
    private String name;
    private int loyaltyPoints;

    public Customer(String customerId, String name, int loyaltyPoints) {
        this.customerId = customerId;
        this.name = name;
        this.loyaltyPoints = loyaltyPoints;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addLoyaltyPoints(int points) {
        this.loyaltyPoints += points;
    }

    @Override
    public String toString() {
        return "Customer[ID=" + customerId + ", Name=" + name + ", LoyaltyPoints=" + loyaltyPoints + "]";
    }

    @Override
    public int hashCode() {
        return customerId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Customer)) return false;
        Customer c = (Customer) obj;
        return this.customerId.equals(c.customerId);
    }
}

//-------------------------------------
// Product Class
//-------------------------------------
class Product {
    private String productId;
    private String name;
    private double price;

    public Product(String productId, String name, double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setPrice(double newPrice) {
        this.price = newPrice;
    }

    @Override
    public String toString() {
        return "Product[ID=" + productId + ", Name=" + name + ", Price=" + price + "]";
    }

    @Override
    public int hashCode() {
        return productId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Product)) return false;
        Product p = (Product) obj;
        return this.productId.equals(p.productId);
    }
}

//-------------------------------------
// Order Class
//-------------------------------------
class Order {
    private String orderId;
    private Date orderDate;
    private Customer customer;
    private Set<Product> products; // Use HashSet to ensure unique products

    public Order(String orderId, Customer customer) {
        this.orderId = orderId;
        this.orderDate = new Date();
        this.customer = customer;
        this.products = new HashSet<>();
    }

    public String getOrderId() {
        return orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public boolean addProduct(Product p) {
        return products.add(p);
    }

    public Set<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Order[ID=" + orderId + ", Date=" + orderDate + ", Customer=" + customer.getName() + ", Products=" + products.size() + "]";
    }
}

//-------------------------------------
// Main Class (Menu-driven Approach)
//-------------------------------------
public class Amazon {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Customer> customerList = new ArrayList<>();
        ArrayList<Product> productList = new ArrayList<>();
        ArrayList<Order> orderList = new ArrayList<>();

        HashMap<String, Customer> customerMap = new HashMap<>();
        HashMap<String, Product> productMap = new HashMap<>();

        while (true) {
            System.out.println("\n==== MENU ====");
            System.out.println("1. Add Customer");
            System.out.println("2. Add Product");
            System.out.println("3. Place Order");
            System.out.println("4. View All Customers");
            System.out.println("5. View All Products");
            System.out.println("6. View All Orders");
            System.out.println("7. View Customers Sorted by Loyalty Points");
            System.out.println("8. View Products Sorted by Price");
            System.out.println("9. View Products Sorted by Name");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            int choice = -1;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    // Add Customer
                    System.out.print("Enter Customer ID: ");
                    String cid = sc.nextLine();
                    if (customerMap.containsKey(cid)) {
                        System.out.println("Customer with this ID already exists.");
                        break;
                    }
                    System.out.print("Enter Customer Name: ");
                    String cname = sc.nextLine();
                    System.out.print("Enter Loyalty Points (int): ");
                    int cpoints = Integer.parseInt(sc.nextLine());
                    Customer c = new Customer(cid, cname, cpoints);
                    customerList.add(c);
                    customerMap.put(cid, c);
                    System.out.println("Customer added successfully!");
                    break;

                case 2:
                    // Add Product
                    System.out.print("Enter Product ID: ");
                    String pid = sc.nextLine();
                    if (productMap.containsKey(pid)) {
                        System.out.println("Product with this ID already exists.");
                        break;
                    }
                    System.out.print("Enter Product Name: ");
                    String pname = sc.nextLine();
                    System.out.print("Enter Product Price (double): ");
                    double pprice = Double.parseDouble(sc.nextLine());
                    Product p = new Product(pid, pname, pprice);
                    productList.add(p);
                    productMap.put(pid, p);
                    System.out.println("Product added successfully!");
                    break;

                case 3:
                    // Place Order
                    System.out.print("Enter Order ID: ");
                    String oid = sc.nextLine();
                    // Just a simple check if order ID already exists (not stored in a map but can check in the order list)
                    boolean orderExists = false;
                    for (Order o : orderList) {
                        if (o.getOrderId().equals(oid)) {
                            orderExists = true;
                            break;
                        }
                    }
                    if (orderExists) {
                        System.out.println("Order with this ID already exists.");
                        break;
                    }

                    System.out.print("Enter Customer ID for this order: ");
                    String coid = sc.nextLine();
                    Customer custForOrder = customerMap.get(coid);
                    if (custForOrder == null) {
                        System.out.println("No such customer found.");
                        break;
                    }

                    Order newOrder = new Order(oid, custForOrder);
                    // Add products until user decides to stop
                    while (true) {
                        System.out.print("Enter Product ID to add to order (or type 'done' to finish): ");
                        String pToAdd = sc.nextLine();
                        if (pToAdd.equalsIgnoreCase("done")) {
                            break;
                        }
                        Product prodToAdd = productMap.get(pToAdd);
                        if (prodToAdd == null) {
                            System.out.println("No such product found.");
                        } else {
                            newOrder.addProduct(prodToAdd);
                            System.out.println("Product added to the order.");
                        }
                    }
                    orderList.add(newOrder);
                    System.out.println("Order placed successfully!");
                    break;

                case 4:
                    // View All Customers
                    System.out.println("All Customers:");
                    for (Customer cust : customerList) {
                        System.out.println(cust);
                    }
                    break;

                case 5:
                    // View All Products
                    System.out.println("All Products:");
                    for (Product prod : productList) {
                        System.out.println(prod);
                    }
                    break;

                case 6:
                    // View All Orders
                    System.out.println("All Orders:");
                    for (Order ord : orderList) {
                        System.out.println(ord);
                    }
                    break;

                case 7:
                    // View Customers Sorted by Loyalty Points
                    TreeSet<Customer> customersByLoyalty = new TreeSet<>(new CustomerLoyaltyComparator());
                    customersByLoyalty.addAll(customerList);
                    System.out.println("Customers Sorted by Loyalty (High to Low):");
                    for (Customer sortedCust : customersByLoyalty) {
                        System.out.println(sortedCust);
                    }
                    break;

                case 8:
                    // View Products Sorted by Price
                    TreeSet<Product> productsByPrice = new TreeSet<>(new ProductPriceComparator());
                    productsByPrice.addAll(productList);
                    System.out.println("Products Sorted by Price:");
                    for (Product sortedProd : productsByPrice) {
                        System.out.println(sortedProd);
                    }
                    break;

                case 9:
                    // View Products Sorted by Name
                    TreeSet<Product> productsByName = new TreeSet<>(new ProductNameComparator());
                    productsByName.addAll(productList);
                    System.out.println("Products Sorted by Name:");
                    for (Product sortedProd : productsByName) {
                        System.out.println(sortedProd);
                    }
                    break;

                case 10:
                    // Exit
                    System.out.println("Exiting the program. Goodbye!");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}