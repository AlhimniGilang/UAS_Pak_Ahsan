/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siakad;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author adib
 */
public class Siakad {

    /**
     * @param args the command line arguments
     */
      private static java.sql.Connection koneksi = null;
    
    
    public static java.sql.Connection koneksinya(){
        try {
            MysqlDataSource kon = new MysqlDataSource();
            kon.setDatabaseName("siakad");
            kon.setUser("root");
            kon.setPassword("");
            koneksi = kon.getConnection();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println(e);
        }
        return koneksi;
    }
    public static void main(String[] args) {
        // TODO code application logic here
        menuLogin a = new menuLogin();
        a.setVisible(true);
    }
    
}
