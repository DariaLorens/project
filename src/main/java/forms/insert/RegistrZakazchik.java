package forms.insert;

import DBpodcl.DBProcessor;
import entity.Zakazchik;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RegistrZakazchik extends JFrame {
    private JPanel registrZakazchik;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton insertButton;

    public RegistrZakazchik(String title) throws HeadlessException {
        super(title);
        this.setContentPane(registrZakazchik);
        DBProcessor c1 = new DBProcessor();

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String nazvanie = textField1.getText();
                final String yr_adres = textField2.getText();
                final String inn = textField3.getText();
                int n = 0;
                ArrayList<Zakazchik> zakazchik = c1.findZakazchik();
                for (int i = 0; i < zakazchik.size(); i++) {
                    Zakazchik temp = zakazchik.get(i);
                    String innTemp = temp.getInn();
                    if (inn.equals(innTemp)) {
                        JOptionPane.showMessageDialog(null, "Заказчик с таким ИНН уже существует");
                        n = 1;
                        break;
                    }
                }
                if (n == 0) {
                    c1.insertNewZakazchik(nazvanie, yr_adres, inn);
                    JOptionPane.showMessageDialog(null, "Вы внесли заказчика с такими параметрами: [" + nazvanie + "] [" + yr_adres + "] [" + inn + "].");
                    setVisible(false);
                    dispose();
                }
            }
        });
    }
}
