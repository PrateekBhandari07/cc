import java.sql.*;
 class RDSCRUDExample {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://dbs.ca72musu828d.us-east-1.rds.amazonaws.com/dbs?useSSL=false";
    static final String USER = "admin";
    static final String PASS = "PeTT5Ulaew7w62zAA5Rc";

    public static void main(String[] args) {
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
                Statement stmt = conn.createStatement();

                // CREATE TABLE (if not exists)
                String createTableSQL = "CREATE TABLE IF NOT EXISTS Students (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100), age INT)";
                stmt.executeUpdate(createTableSQL);
                System.out.println("Table ensured.");

                // INSERT
                String insertSQL = "INSERT INTO Students (name, age) VALUES ('John Doe', 22)";
                stmt.executeUpdate(insertSQL);
                System.out.println(" Record inserted.");

                // READ
                String selectSQL = "SELECT * FROM Students";
                ResultSet rs = stmt.executeQuery(selectSQL);
                System.out.println(" Records:");
                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id") + " Name: " + rs.getString("name") + " Age: " + rs.getInt("age"));
                }

                // UPDATE
                String updateSQL = "UPDATE Students SET age = 23 WHERE name = 'John Doe'";
                stmt.executeUpdate(updateSQL);
                System.out.println(" Record updated.");

                // DELETE
                String deleteSQL = "DELETE FROM Students WHERE name = 'John Doe'";
                stmt.executeUpdate(deleteSQL);
                System.out.println(" Record deleted.");

                rs.close();
                stmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

