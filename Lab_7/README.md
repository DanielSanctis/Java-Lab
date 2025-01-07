# Amazon Customer and Order Management System

A Java-based application for managing customers, products, and orders efficiently using advanced data structures and custom sorting.

---

## Features

1. **Customer Management:**
   - Add, update, and display customer details.
   - Sort customers by loyalty points.

2. **Product Management:**
   - Add and update product catalog.
   - Sort products by price or name.

3. **Order Management:**
   - Place, modify, and retrieve orders.
   - Sort orders by delivery date.

---

## Data Structures Used

- **`ArrayList`**: Store dynamic lists of customers, products, and orders.
- **`HashMap`**: Fast retrieval of customers/products using unique IDs.
- **`HashSet`**: Ensure unique products per customer.
- **`TreeSet`**: Maintain sorted customers/products with custom comparators.

---

## Custom Sorting with Comparator

Examples:
- **Products by Price:**
  ```java
  class ProductPriceComparator implements Comparator<Product> {
      public int compare(Product p1, Product p2) {
          return Double.compare(p1.getPrice(), p2.getPrice());
      }
  }
