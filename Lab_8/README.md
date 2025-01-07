# Attendee Management System for International Conference

A Java application using JDBC to manage attendee information for the international conference organized by CHRIST (Deemed to be University).

---

## Features

1. **Add Attendee**  
   Register attendees with auto-generated unique IDs using `PreparedStatement`.

2. **Edit Attendee**  
   Update attendee details (e.g., email, contact) securely.

3. **Delete Attendee**  
   Remove attendees upon cancellation.

4. **Search Attendee**  
   Search by ID, Full Name, or Country.

5. **Generate Statistics**  
   Use a stored procedure (via `CallableStatement`) to get attendee counts by country.

---

## Database Setup

### Table Creation
```sql
CREATE TABLE Attendance (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    FullName VARCHAR(50) NOT NULL,
    Email VARCHAR(50) NOT NULL,
    ContactNumber VARCHAR(10) NOT NULL,
    Country VARCHAR(25) NOT NULL
);

Stored Procedure
sql
Copy code
DELIMITER //
CREATE PROCEDURE GetAttendeeStats()
BEGIN
    SELECT Country, COUNT(*) AS AttendeeCount 
    FROM Attendance 
    GROUP BY Country;
END //
DELIMITER ;
How to Run
Set up the database with the above structure and procedure.
Update database credentials in the Java code.
Compile and run the application:
bash
Copy code
javac AttendeeManagementSystem.java
java AttendeeManagementSystem
Example Outputs
Add Attendee:
Attendee added successfully! Assigned ID: 1

Statistics:

markdown
Copy code
Country     | AttendeeCount
---------------------------
USA         | 5
India       | 10
Technologies Used
JDBC: Database interaction
PreparedStatement: Secure queries
CallableStatement: Stored procedure execution
MySQL: Database