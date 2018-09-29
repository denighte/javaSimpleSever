package main;

import com.mysql.jdbc.Driver;
import org.apache.commons.dbutils.DbUtils;

import java.sql.*;

/**
 * Created by dmitr on 9/20/2018.
 */
public class Test {
    public static void main(String[] args) {
        Connection c = null;

        Statement s = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            DriverManager.registerDriver(new Driver());

            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/dmitry", "root", "password");

            s = c.createStatement();
            rs = s.executeQuery("select * from users");

            while (rs.next()) {
                System.out.println(rs.getString("id") + " : " + rs.getString("first_name") + " : " + rs.getString("last_name"));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(s);
            DbUtils.closeQuietly(c);
        }
    }
}


