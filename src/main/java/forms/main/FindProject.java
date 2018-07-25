package forms.main;

import DBpodcl.DBProcessor;
import entity.TipHaracteristica;
import tableModels.ProjectTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FindProject extends JFrame{
    private JComboBox<TipHaracteristica> comboBox3;
    private JTextField textField1;
    private JButton button1;
    private JLabel zaprosLabel;
    private JPanel findProject;
    private JLabel rezLabel;
    public static ArrayList<String> masProjectKod;
    public static ArrayList<String> masProjectKod2;
    public static ArrayList<String> masProjectKodRez;
    final DBProcessor c1 = new DBProcessor();

    public FindProject(String title) throws HeadlessException {
        super(title);
        this.setContentPane(findProject);
        zaprosLabel.setText("Найти проект где: ");
        for (TipHaracteristica tipHaracteristica : c1.findTipHaracteristica()) {
            comboBox3.addItem(tipHaracteristica);
        }

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!zaprosLabel.getText().equals("Найти проект где: ")){
                    zaprosLabel.setText(zaprosLabel.getText() + " и ");
                    masProjectKod2 = c1.findProjectHar(comboBox3.getItemAt(comboBox3.getSelectedIndex()).getIdharacteristica(), textField1.getText());
                for (int i = 0; i<masProjectKod2.size(); i++){
                    if (masProjectKod.indexOf(masProjectKod2.get(i))<0){
                        masProjectKod.remove(masProjectKod2.get(i));
                    }
                }
                }
                zaprosLabel.setText(zaprosLabel.getText() + comboBox3.getItemAt(comboBox3.getSelectedIndex()).getNazvanie() + " = " + textField1.getText());
                masProjectKod = c1.findProjectHar(comboBox3.getItemAt(comboBox3.getSelectedIndex()).getIdharacteristica(), textField1.getText());
                rezLabel.setText(String.valueOf(masProjectKod));
            }
        });
    }

    private void createUIComponents() {
        comboBox3 = new JComboBox <TipHaracteristica>();
    }
}
