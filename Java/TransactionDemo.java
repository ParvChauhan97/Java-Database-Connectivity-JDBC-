import java.sql.*;

public class TransactionDemo {
    private static final String URL = "jdbc:mysql://localhost:3306/demo_db";
    private static final String USER = "root";
    private static final String PASSWORD = "secrat";

    public static void main(String[] args) {
        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connected to Database");

            //Turned off Auto Save.
            conn.setAutoCommit(false);

            try {
                //Orders and Orders Items.
                //INSERT INTO ORDER
                int ordersId = insertorders(conn, 101, "Rahul1", 5000.0);

                //INSERT INTO ORDER ITEMS
                insertordersitems(conn, ordersId, "Laptop1", 1, 16000.0);
                conn.commit();
            } catch (Exception e) {
                e.printStackTrace();
                conn.rollback();
                System.out.println("OPERATION ROLLBACK");
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
    }
}

    private static void insertordersitems(Connection conn, int orders_id, String productName, int qty, double price) {
        String sql = "INSERT INTO orders_items (orders_id, product_name, qty, price) VALUES (?, ?, ?, ?)";
        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, orders_id);
            pstmt.setString(2, productName);
            pstmt.setInt(3, qty);
            pstmt.setDouble(4, price);
            int row = pstmt.executeUpdate();
            System.out.println("INSERTED into order items: " + row);

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private static int insertorders(Connection conn, int customerId, String customerName, double price) {
        String sql = "INSERT INTO orders (user_id, costumer_name, total_ammount) VALUES (?, ?, ?)";
        try(PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, customerId);
            pstmt.setString(2, customerName);
            pstmt.setDouble(3, price);
            int row = pstmt.executeUpdate();
            System.out.println("INSERTED into orders: " + row);
            
            } 
            catch (SQLException e) {
              throw new RuntimeException();
            }
    }
