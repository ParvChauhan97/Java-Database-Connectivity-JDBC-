import java.sql.*;

public class JDBCdemo {
  private static final String URL = "jdbc:mysql://localhost:3306/demo_db";
    private static final String USER = "root";
    private static final String PASSWORD = "secrat";

  public static void main(String[] args) {
    try(Connection conn = DriverManager.getConnection(URL , USER, PASSWORD);) {
            System.out.println("Connected to Database.");
           InsertVal(conn, "Ram", "ram@gmail.com");
           update(conn, 3, "Luckey", "luckey@gmail.com");
        } catch (SQLException e) {
            e.printStackTrace();
        }
  }

  private static void InsertVal(Connection conn, String name, String email) {
    String sql = "INSERT INTO infor(name, email) VALUES (' " +  name + " ' , ' " + email + " ')";
    try(Statement stmt = conn.createStatement()) {
            int rows = stmt.executeUpdate(sql);
            System.out.println("INSERTED" + rows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
  }
}
