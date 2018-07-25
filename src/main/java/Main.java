import forms.main.Login;
import javax.swing.*;

public class Main {
    public static void main(String[] args){
        Login loginForm = new Login("LoginForm");
        loginForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //   loginForm.setLocation();
        loginForm.pack();
        loginForm.setLocationRelativeTo(null);
        loginForm.setVisible(true);
    }
}