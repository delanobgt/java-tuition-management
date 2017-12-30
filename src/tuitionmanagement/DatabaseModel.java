package tuitionmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseModel {
    
    private final String url;
    private final String user;
    private final String pass;
    
    public DatabaseModel(String url, String user, String pass) {
        this.url = url;
        this.user = user;
        this.pass = pass;
    }
    
    private Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("db connected");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return conn;
    }
    
    public boolean isLoginSuccessful() {
        return getConnection() != null;
    }
    
    public void add(Student student) {
        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(
                    "INSERT INTO student(name, grade, fee, payment_date) VALUES(?,?,?,?)")
                ;) {
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getGrade());
            pstmt.setInt(3, student.getFee());
            pstmt.setString(4, student.getPaymentDate());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void update(Student student) {
        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(
                    "UPDATE student "
                        + "SET "
                            + "name = ?, "
                            + "grade = ?, "
                            + "fee = ?, "
                            + "payment_date = ? "
                        + "WHERE id = ?"
                );) {
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getGrade());
            pstmt.setInt(3, student.getFee());
            pstmt.setString(4, student.getPaymentDate());
            pstmt.setInt(5, student.getId());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void delete(int id) {
        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(
                    "DELETE FROM student WHERE id = ?")
                ;) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public ArrayList<Student> getProductList() {
        ArrayList<Student> lst = new ArrayList<>();
        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM student");) {
            while (rs.next()) {
                lst.add(new Student(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5)
                ));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return lst;
    }
    
}
