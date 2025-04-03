import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;
import java.util.Vector;

public class usermanagment extends JFrame{
    private JTable table1;
    private JPanel mainpanel;
    private JPanel tablepanel;
    private JPanel btnpanel;
    private JButton add;
    private JButton backButton;
    private JTextField usename;
    private JComboBox selectbox;
    private JPasswordField password;
    private JTextField firstname;
    private JTextField lastname;
    private JTextField email;
    private JTextField phone;
    private JButton uploadButton;
    private JButton deleteButton;
    private JLabel photolabel;


    usermanagment() {

        //linking step


        createtable();
        tableload();
        setborder();
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Adduser();

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new admindash();

            }
        });


        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                image_upload();

            }
        });
    }

    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    String path2; //to get the path


    public void tableload() {
        int count;

        try {
            Connection con = dbconnection.getConnection();
            pst = con.prepareStatement("select *from users");
            rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            count = rsmd.getColumnCount();
            DefaultTableModel dtm = (DefaultTableModel) table1.getModel();
            dtm.setRowCount(0);
            while (rs.next()) {
                Vector v2 = new Vector();
                for (int i = 1; i <= count; i++) {
                    v2.add(rs.getString("user_id"));
                    v2.add(rs.getString("username"));
                    v2.add(rs.getString("password"));
                    v2.add(rs.getString("first_name"));
                    v2.add(rs.getString("last_name"));
                    v2.add(rs.getString("email"));
                    v2.add(rs.getString("phone"));
                    v2.add(rs.getString("user_type"));

                }
                dtm.addRow(v2);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void Adduser() {
        String username = usename.getText();
        String pass = new String(password.getPassword());
        String dropmenu = selectbox.getSelectedItem().toString();
        String firstn = firstname.getText();
        String lastn = lastname.getText();
        String mail = email.getText();
        String phnum = phone.getText();

        if (username.isEmpty() || pass.isEmpty() || dropmenu.isEmpty() || firstn.isEmpty() || lastn.isEmpty() || mail.isEmpty()) {
            showMessage("Fill all the fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;

        }
        try {
            con = dbconnection.getConnection();

            pst = con.prepareStatement("select * from users where username = ? ");
            pst.setString(1, username);
            rs = pst.executeQuery();
            if (rs.next()) {
                showMessage("Username already exists", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else {

                con = dbconnection.getConnection();
                pst = con.prepareStatement("insert into users(username,password,user_type,first_name,last_name,email,phone,profile_picture) values(?,?,?,?,?,?,?,?)");
                pst.setString(1, username);
                pst.setString(2, pass);
                pst.setString(3, dropmenu);
                pst.setString(4, firstn);
                pst.setString(5, lastn);
                pst.setString(6, mail);
                pst.setString(7, phnum);





                // FIXED IMAGE HANDLING FOR XAMPP MYSQL:
                if (path2 != null) {
                    File imageFile = new File(path2);
                    try (InputStream is = new FileInputStream(imageFile)) {
                        // For XAMPP MySQL, use this approach:
                        pst.setBinaryStream(8, is, (int)imageFile.length());
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    pst.setNull(8, Types.BLOB);
                }
















                int rs = pst.executeUpdate();
                if (rs == 1) {
                    showMessage("succefully added", "Successful", JOptionPane.INFORMATION_MESSAGE);
                    usename.setText("");
                    password.setText("");
                    firstname.setText("");
                    lastname.setText("");
                    email.setText("");


                    tableload();
                    return;

                } else {
                    showMessage("Error adding user", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public void image_upload() {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        path2 = f.getAbsolutePath();

        try {
            BufferedImage bi = ImageIO.read(f);
            Image img = bi.getScaledInstance(134, 172, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(img);
            photolabel.setIcon(icon);


        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }




    public void createtable() {
        table1.setModel(new javax.swing.table.DefaultTableModel(null, new String[]{"user_id", "username", "password", "firstname", "lastname", "email", "phone", "usertype"}));
    }

    private void showMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }
    private void setborder(){
        // Set Border
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        photolabel.setBorder(border);
    }
public static void main(String[] args) {
        usermanagment um = new usermanagment();

    // Make the UI look like the native system
    try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
        e.printStackTrace();
    }


    um.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    um.setContentPane(um.mainpanel);
    um.setTitle("bmical");
    um.setSize(650, 650);
    um.setVisible(true);


}

}










