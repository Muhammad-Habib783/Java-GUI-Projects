import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LoginForm
{
    public static void main(String[] args)
    {
        JFrame loginFrame = new JFrame("Riphah Lootlo - Login");  // frame name
        loginFrame.setSize(400,300);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLocationRelativeTo(null);   // function to bring interface in centre

        // brackground color gray
        loginFrame.getContentPane().setBackground(new Color(240,240,240));

        
        JPanel loginPanel = new JPanel();   // overall interface controller
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setBackground(new Color(240,240,240));

        
        GridBagConstraints constraints = new GridBagConstraints();    // layout type
        constraints.insets = new Insets(10,10,10,10); // top left bottom right

        JLabel username = new JLabel("USERNAME:");
        JTextField txtusername = new JTextField(15);
        JLabel password = new JLabel("PASSWORD:");
        JPasswordField txtpassword = new JPasswordField(15);
        JButton btnLogin = new JButton("ENTER");

        
        btnLogin.setBackground(new Color(70,130,180)); // blue color
        btnLogin.setForeground(Color.WHITE); // text white

        // positioning of elements and sapcing concept
        constraints.gridx = 0;
        constraints.gridy = 0;
        loginPanel.add(username,constraints);

        constraints.gridx = 1;
        loginPanel.add(txtusername,constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        loginPanel.add(password,constraints);

        constraints.gridx = 1;
        loginPanel.add(txtpassword,constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2; 
        loginPanel.add(btnLogin,constraints);

        btnLogin.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                String usernameInput = txtusername.getText();
                String passwordInput = new String(txtpassword.getPassword());

                if (usernameInput.equals("Riphah") && passwordInput.equals("4456")) 
                {
                    loginFrame.dispose(); // this function wuill close the current frame
                    JOptionPane.showMessageDialog(loginFrame,"WELCOME TO RIPHAH LOOTLO!");
                    new ShoppingSystem(); // constructor call of this class
                } 
                else 
                {
                    JOptionPane.showMessageDialog(loginFrame,"Invalidlogin!Pleasecheckyourusernameorpassword.");
                }
            }
        });
        loginFrame.add(loginPanel);
        loginFrame.setVisible(true);
    }
}




