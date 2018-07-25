package forms.insert;

import DBpodcl.DBProcessor;
import entity.TipUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrPolz extends JFrame{
    private JPanel registrPolz;
    private JButton insertButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JComboBox<TipUser> comboBox1;

    public RegistrPolz(String title) {
        super(title);
        this.setContentPane(registrPolz);
        DBProcessor c1 = new DBProcessor();
        for (TipUser tipUser : c1.findAllTipUser()) {
            comboBox1.addItem(tipUser);
        }
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String name = textField2.getText();
                final String fam = textField1.getText();
                final String otch = textField3.getText();
                final String login = textField4.getText();
                final String pass = textField5.getText();
                final int id_tip = comboBox1.getItemAt(comboBox1.getSelectedIndex()).getId();

                if (c1.findUser(login) > 0) {
                    JOptionPane.showMessageDialog(null, "Такой логин уже занят");
                } else {
                        if (c1.findSotrudnik(fam,name,otch) > 0) {
                            JOptionPane.showMessageDialog(null, "Такой сотрудник уже зарегестрирован");
                        } else {
                            c1.insertNewUser(name, fam, otch, login, pass, id_tip);
                            JOptionPane.showMessageDialog(null, "Вы внесли пользователя с такими параметрами: [" + fam + "] [" + name + "] [" + otch + "].");
                        }
                    }
                }
        });
    }

    private void createUIComponents() {
        comboBox1 = new JComboBox<TipUser>();
    }
}