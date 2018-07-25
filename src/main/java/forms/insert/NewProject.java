package forms.insert;

import DBpodcl.DBProcessor;
import entity.Status;
import entity.Zakazchik;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class NewProject extends JFrame{
    private JPanel newProject;
    private JCheckBox CheckBox1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JComboBox<Status> comboBox1;
    private JButton insertButton;
    private JPanel checkPanel;
    private JComboBox<Zakazchik> comboBox2;
    private JTextArea textArea1;

    public NewProject(String title, int idsotrudnik) throws HeadlessException {
        super(title);
        this.setContentPane(newProject);
        checkPanel.setVisible(false);
        final String ryad = null;
        final String stolb = null;
        final String polka = null;
        DBProcessor c1 = new DBProcessor();
        for (Status status :c1.findAllStatus()){
            comboBox1.addItem(status);
        }
        for (Zakazchik zakazchik :c1.findZakazchik()){
            comboBox2.addItem(zakazchik);
        }

        CheckBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (CheckBox1.isSelected()) {
                    checkPanel.setVisible(true);
                    final String ryad = textField1.getText();
                    final String stolb = textField2.getText();
                    final String polka = textField3.getText();
                } else {
                    checkPanel.setVisible(false);
                }
            }
        });


        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final int idstatus = comboBox1.getItemAt(comboBox1.getSelectedIndex()).getIdstatus();
                final int idzakazchik = comboBox2.getItemAt(comboBox2.getSelectedIndex()).getIdzakazchik();
                final String nazvanie = textField4.getText();
                final String kod = textField5.getText();
                final String put = "D:\\Проекты тест\\" + kod;
                final String comment = textArea1.getText();
                File folder = new File(put);
                folder.mkdir();
                c1.insertNewProject(nazvanie, kod, put, ryad, stolb, polka, comment, idstatus, idzakazchik, idsotrudnik);
                JOptionPane.showMessageDialog(null,"Вы внесли новый проект");
                dispose();
            }
        });

    }

    private void createUIComponents() {
        comboBox1 = new JComboBox <Status>();
        comboBox2 = new JComboBox<Zakazchik>();
    }
}
