import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * Created by Kushagra on 30-06-2015.
 */
public class todo extends JFrame implements ActionListener {
    int check;
    private JPanel panel1 = new JPanel(new CardLayout());
    public JPanel success;
    public JPanel main;
    TextField title = new TextField();
    TextField content = new TextField();
    JButton create = new JButton("Create");

    public todo() {
        super();
        setContentPane(panel1);

        setMainScreen();
        panel1.add(main, "main");


        setSuccessScreen();
        panel1.add(success, "success");


        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e1) {
            System.out.println("Mysql driver not found");
            return;
        }
        String url = "jdbc:mysql://localhost/todo_list";
        String username = "kushagra";
        String password = "123";

        String title1, content1;
        if (e.getSource() == create) {
            CardLayout layout = (CardLayout) panel1.getLayout();
            title1 = title.getText();
            content1 = content.getText();
            Connection conn;
            Statement stmnt;
            try {
                conn = DriverManager.getConnection(url, username, password);
                stmnt = conn.createStatement();
                check = stmnt.executeUpdate("INSERT INTO `list`(`title`, `description`) VALUES ('" + title1 + "','" + content1 + "');");
            } catch (SQLException e2) {
                System.out.println(e2.getMessage());
                return;
            }
            layout.show(panel1, "success");
        }

    }

    public void setMainScreen() {

        main.add(title);
        main.add(content);
        title.addActionListener(this);
        content.addActionListener(this);
        create.addActionListener(this);
    }

    public void setSuccessScreen() {
        JLabel l = new JLabel("Success!");
        success.add(l);
    }
}
