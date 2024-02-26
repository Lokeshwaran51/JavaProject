package ConnectionPool_hikaricp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariDemo {
    private static HikariDataSource datasource = null;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/demo");
        config.setUsername("root");
        config.setPassword("SULokesh282001");
        config.addDataSourceProperty("minimumIdle", "5");
        config.addDataSourceProperty("maximumPoolSize", "20");

        
         datasource = new HikariDataSource(config);
    }

    public static void main(String[] args) throws SQLException {
        try {
            Connection conn = datasource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user");
            
            while (rs.next()) {
                System.out.println("id: " + rs.getInt("id"));
                System.out.println("name: " + rs.getString("name"));
                System.out.println("email: " + rs.getString("email"));
                System.out.println("country: " + rs.getString("country"));
                System.out.println("contact: " + rs.getString("contact"));
                System.out.println("gender: " + rs.getString("gender"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Connection conn= datasource.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM student");
        while(rs.next()) {
        	System.out.println("name:"+rs.getString("name"));
        }
    }
}
