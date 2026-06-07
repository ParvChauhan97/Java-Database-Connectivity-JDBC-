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

  private static void selectmamber(Connection conn) {
      String sql = "SELECT * FROM infor";
     try(Statement stmt = conn.createStatement()) {
            ResultSet resultset = stmt.executeQuery(sql);
            System.out.println("List:- ");
            while(resultset.next()) {
                int id = resultset.getInt("Id");
                String name = resultset.getString("name");
                String email = resultset.getString("email");
                System.out.println(id + " : " + name + " : " + email);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
   }
  
    private static void update(Connection conn, int id, String name, String email) {
       String sql = "UPDATE infor SET name = ? , email = ? WHERE id = ? ";
      try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setInt(3, id);
            int row = pstmt.executeUpdate();
            System.out.println("UPDATED" + row);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


  private static  void delete(Connection conn, int id) {
     String sql = "DELETE FROM infor WHERE id = " +id;
  }
}
