package student.information.system;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import com.sun.glass.events.KeyEvent;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Login extends javax.swing.JFrame {
Connection conn=null;
ResultSet rs=null;
PreparedStatement pst=null;

public Login() {						//Creates a login form
initComponents();
conn=db.java_db();
Toolkit toolkit = getToolkit();
Dimension size = toolkit.getScreenSize();
setLocation(size.width/2 - getWidth()/2,
size.height/2 - getHeight()/2);
currentDate();
}
public void currentDate (){
 Calendar cal =new GregorianCalendar();
int month = cal.get(Calendar.MONTH);
int year = cal.get(Calendar.YEAR);
int day = cal.get(Calendar.DAY_OF_MONTH);
 
txt_date.setText((month+1)+"/"+day+"/"+year);
}
@SuppressWarnings("unchecked")

private void initComponents() {
jPanel1 = new javax.swing.JPanel();
jLabel2 = new javax.swing.JLabel();
jLabel3 = new javax.swing.JLabel();
jButton1 = new javax.swing.JButton();
txt_username = new javax.swing.JTextField();
txt_password = new javax.swing.JPasswordField();
txt_combo = new javax.swing.JComboBox<>();
jLabel4 = new javax.swing.JLabel();
jLabel1 = new javax.swing.JLabel();
jMenuBar1 = new javax.swing.JMenuBar();
txt_date = new javax.swing.JMenu();
txt_time = new javax.swing.JMenu();
setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
setResizable(false);
jPanel1.setLayout(null);
jLabel2.setFont(new java.awt.Font("Viner Hand ITC", 1, 12)); 
jLabel2.setText("Username :");
jPanel1.add(jLabel2);
jLabel2.setBounds(20, 230, 110, 20); 
jLabel3.setFont(new java.awt.Font("Viner Hand ITC", 1, 12)); 
jLabel3.setText("Password :");
jPanel1.add(jLabel3);
jLabel3.setBounds(20, 270, 70, 14); 
jButton1.setFont(new java.awt.Font("Viner Hand ITC", 1, 12));
jButton1.setText("Login");
jButton1.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
jButton1ActionPerformed(evt);
}
});
jPanel1.add(jButton1);
jButton1.setBounds(140, 320, 70, 30); 
txt_username.setText("Admin");
txt_username.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
txt_usernameActionPerformed(evt);
}
});
jPanel1.add(txt_username);
txt_username.setBounds(100, 220, 160, 30); 
txt_password.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
txt_passwordActionPerformed(evt);
}
});
jPanel1.add(txt_password);
txt_password.setBounds(100, 260, 160, 30); 
txt_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin" }));
txt_combo.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
txt_comboActionPerformed(evt);
}
});
jPanel1.add(txt_combo);
txt_combo.setBounds(100, 170, 160, 30); 
jLabel4.setFont(new java.awt.Font("Viner Hand ITC", 1, 12)); // NOI18N
jLabel4.setText("Division :");
jPanel1.add(jLabel4);
jLabel4.setBounds(30, 180, 70, 20); 
jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/student/information/system/images/xyz.jpg"))); 				//Path of the image stored
jPanel1.add(jLabel1);
jLabel1.setBounds(0, -60, 790, 580); 
txt_date.setText("Date");
jMenuBar1.add(txt_date);
txt_time.setText("Time");
jMenuBar1.add(txt_time);
setJMenuBar(jMenuBar1); 
javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
getContentPane().setLayout(layout);
layout.setHorizontalGroup(
layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
);
layout.setVerticalGroup(
layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
); 
pack();
}
 
private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {

String sql = "select id,username,password,division from Users Where (username =? and password =? and division =?)"; 
try{
int count =0; 
pst=conn.prepareStatement(sql); 
pst.setString(1,txt_username.getText());
pst.setString(2,txt_password.getText());
pst.setString(3, txt_combo.getSelectedItem().toString()); 
rs=pst.executeQuery();{
}
while(rs.next()){
int id = rs.getInt(1);
Emp.empId = id;
count =count+1;
}
String access=(txt_combo.getSelectedItem().toString());
 
if(access=="Admin") {				//Validating the user_ID 
if(count==1){
JOptionPane.showMessageDialog(null,"Success" );
MainMenu j = new MainMenu();  //As a result of correctly logging in,
j.setVisible(true);	      //the	main menu window is shown this.dispose(); 			
 
Date currentDate = GregorianCalendar.getInstance().getTime();
DateFormat df = DateFormat.getDateInstance();
String dateString = df.format(currentDate); 
Date d = new Date();
SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
String timeString = sdf.format(d); 
String value0 = timeString;
String values = dateString; 
int value = Emp.empId;
String reg = "insert into Audit (emp_id,date,status) values ('"+value+"','"+value0+" / "+values+"','Logged in')";
pst=conn.prepareStatement(reg);
pst.execute();
this.dispose(); 
} 
else if(count>1){		//Access is to be denied
JOptionPane.showMessageDialog(null,"Duplicate Username or Password Access denied");
}
else{
JOptionPane.showMessageDialog(null,"Username and Password is not correct");
}
}
} catch(Exception e)
{
JOptionPane.showMessageDialog(null, e);
}
finally { 
try{
rs.close();
pst.close(); 
}
catch(Exception e){ 
}}} 
private void txt_usernameActionPerformed(java.awt.event.ActionEvent evt) {	//For the username textbox, necessary code can be added
} 
private void txt_passwordActionPerformed(java.awt.event.ActionEvent evt) {	//For the password textbox, necessary code can be added
} 
private void txt_comboActionPerformed(java.awt.event.ActionEvent evt) {		//For the division pop-up, necessary code can be added
}
public static void main(String args[]) {
try {
for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
if ("Nimbus".equals(info.getName())) {
javax.swing.UIManager.setLookAndFeel(info.getClassName());
break;
}}
} catch (ClassNotFoundException ex) {
java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
} catch (InstantiationException ex) {
java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
} catch (IllegalAccessException ex) {
java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
} catch (javax.swing.UnsupportedLookAndFeelException ex) {
java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
}
java.awt.EventQueue.invokeLater(new Runnable() {
public void run() {
new Login().setVisible(true);		//Displays the Login form
}
});
}
//All variable declarations 
private javax.swing.JButton jButton1;	
private javax.swing.JLabel jLabel1;
private javax.swing.JLabel jLabel2;
private javax.swing.JLabel jLabel3;
private javax.swing.JLabel jLabel4;
private javax.swing.JMenuBar jMenuBar1;
private javax.swing.JPanel jPanel1;
private javax.swing.JComboBox<String> txt_combo;
private javax.swing.JMenu txt_date;
private javax.swing.JPasswordField txt_password;
private javax.swing.JMenu txt_time;
private javax.swing.JTextField txt_username;
}

