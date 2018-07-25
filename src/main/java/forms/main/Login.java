package forms.main;


import DBpodcl.DBProcessor;
import entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Login extends JFrame{

    public JPanel panelLogin;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton Button1;
    private Login thisForm;
    int idsotrudnik = 0;
    int idtip = 0;


    public Login(String title) {
        super(title);
        this.setContentPane(panelLogin);
        thisForm = this;
        final DBProcessor c1 = new DBProcessor();
        Button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String login = textField1.getText();
                final char[] pass = passwordField1.getPassword();
                String passString = new String(pass);
                ArrayList<User> user = c1.findUser(login, passString);
                if (user.size()!=0) {
                    idtip = user.get(0).getId_tip();
                    idsotrudnik = c1.findIdSotrudnik(login, passString);
                    Projects projects = new Projects("Главная форма", idsotrudnik, idtip);
                    projects.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    projects.pack();
                    projects.setSize(800, 300);
                    projects.setLocationRelativeTo(null);
                    projects.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Неверный логин или пароль!");
                }
            }
        });
}
}