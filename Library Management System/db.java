//Program connects java application to the sql database
package student.information.system;
import java.sql.*;
import javax.swing.*;
public class db {
 
Connection conn=null;
public static Connection java_db(){
try{
Class.forName("org.sqlite.JDBC");
Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\rpoot\\Desktop\\abc\\Student Information System\\studentInfo.sqlite");
 
return conn;
}
catch (Exception e){
JOptionPane.showMessageDialog(null, e);
return null;
} 
}
}
