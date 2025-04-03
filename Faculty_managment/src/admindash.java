import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class admindash extends JFrame {
    private JButton userButton;
    private JButton timetableButton;
    private JButton attendenceButton;
    private JButton noticeButton;
    private JButton courseButton;
    private JButton marksButton;
    private JButton logoutButton;
  private JPanel adminpanel;


  public admindash(){
      // Make the UI look like the native system
      try {
          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      } catch (Exception e) {
          e.printStackTrace();
      }

      setTitle("Admin Dashboard");
      setContentPane(adminpanel);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(900, 600);
      setVisible(true);


      userButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              //dispose();
              //new usermanagment();

          }
      });

  }





























}



