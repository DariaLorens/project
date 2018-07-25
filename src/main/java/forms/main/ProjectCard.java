package forms.main;

import DBpodcl.DBProcessor;
import entity.Sotrudnik;
import entity.TipDoc;
import entity.TipFin;
import entity.TipHaracteristica;
import forms.insert.NewDoc;
import tableModels.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

public class ProjectCard extends JFrame{
    private JTabbedPane tabbedPane1;
    private JPanel projectCard;
    private JLabel labelNazvanie;
    private JLabel labelStatus;
    private JLabel labelZakazchik;
    private JTextArea textArea1;
    private JComboBox<TipDoc> comboBox1;
    private JTable table1;
    private JButton openFolderProjectButton;
    private JPanel archivPanel;
    private JLabel labelRyadStolbPolka;
    private JTable table2;
    private JButton insertDocButton;
    private JTable table3;
    private JTable table4;
    private JButton insertNewZadButton;
    private JButton buttonDownZad;
    private JButton buttonUpZad;
    private JButton delZadButton;
    private JButton dellDocButton;
    private JButton updateComment;
    private JTextField textField1;
    private JButton buttonDownPodzad;
    private JButton buttonUpPodzad;
    private JButton newPodzadachaButton;
    private JButton buttonDelPodzadacha;
    private JPanel newPodzadacha;
    private JTextField textField2;
    private JComboBox<Sotrudnik> comboBox2;
    private JTextArea textArea2;
    private JButton insertNewPodzadButton;
    private JTable table5;
    private JButton buttonAddHar;
    private JButton buttonDelHar;
    private JComboBox<TipHaracteristica> comboBox3;
    private JTextArea textArea3;
    private JRadioButton insertNewHarRadioButton;
    private JTextField textField3;
    private JTable table6;
    private JButton buttonInsertFin;
    private JButton delFinanceButton;
    private JComboBox<TipFin> comboBox4;
    private JFormattedTextField formattedTextField1;
    private JTextArea textArea4;
    private JLabel summaLabel;
    private JPanel updateArchiv;
    private JCheckBox checkBox1;
    private JButton окButton;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JButton buttonChange;
    private JLabel procentLabel;
    private JLabel statusLabel;
    private JLabel sumLabel;
    private JLabel finLabel;
    private JLabel dataLabel;
    private JPanel ciklePanel;
    private JButton экспортироватьВExelButton;
    private JButton экспортироватьВExcelButton;
    private JButton завершитьПроектButton;
    private DocTableModel docTableModel;
    private FinanceTableModel financeTableModel;
    private HaracteristicaTableModel haracteristicaTableModel;
    private ZadachaTableModel zadachaTableModel;
    private PodzadachaTableModel podzadachaTableModel;
    private SotrVProjectTableModel sotrVProjectTableModel;
    private String selectedDoc = "";
    private String lastZadacha = "";
    private int idSelectedZad;
    private int nomerSelectedZad;
    private int idSelectedPodzad;
    private int nomerSelectedPodzadad;
    private int idSelectedHar;
    private int idSelectedFinance;
    final DBProcessor c1 = new DBProcessor();


    public ProjectCard(String title, String nazvanie, String kod, String put, String ryad, String stolb, String polka,
                       String comment, String status, String zakazchik) throws HeadlessException {
        super(title);
        this.setContentPane(projectCard);
        newPodzadacha.setVisible(false);
        updateArchiv.setVisible(false);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        textArea1.setBorder(border);
        textArea2.setBorder(border);
        textArea3.setBorder(border);
        textArea4.setBorder(border);
        if (ryad == null){
            checkBox1.setVisible(true);
            archivPanel.setVisible(false);
        }
        else {
            checkBox1.setVisible(false);
            archivPanel.setVisible(true);
        }
        int idProject = c1.findIdProject(kod);
        sumLabel.setFont(new Font("Times Roman", Font.BOLD, 30));
        sumLabel.setText(String.valueOf(c1.findSumma(idProject, 1) - c1.findSumma(idProject, 2)));
        float zaverheno = 0;
        for (TipDoc tipDoc :c1.findAllTipDoc()){
            comboBox1.addItem(tipDoc);
        }
        for (Sotrudnik sotrudnik : c1.findSotrudnik()) {
            comboBox2.addItem(sotrudnik);
        }
        for (TipHaracteristica tipHaracteristica : c1.findTipHaracteristica()) {
            comboBox3.addItem(tipHaracteristica);
        }
        for (TipFin tipFin :c1.findAllTipFin()){
            comboBox4.addItem(tipFin);
        }

        labelNazvanie.setText(kod + "\\" + nazvanie);
        labelZakazchik.setText("Заказчик: " + zakazchik);
        labelStatus.setText("Статус проекта: " + status);
        labelRyadStolbPolka.setText(ryad + "-" + stolb + "-" + polka);
        textArea1.setText(comment);
        docTableModel = new DocTableModel(c1.findDoc(kod, 1));
        table2.setModel(docTableModel);
        docTableModel.fireTableDataChanged();

        financeTableModel = new FinanceTableModel(c1.findFinance(idProject, 1));
        table6.setModel(financeTableModel);
        financeTableModel.fireTableDataChanged();
        table6.getColumnModel().getColumn(0).setWidth(0);
        table6.getColumnModel().getColumn(0).setMinWidth(0);
        table6.getColumnModel().getColumn(0).setMaxWidth(0);
        table6.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        summaLabel.setText("Итого: " + c1.findSumma(idProject, 1));
        statusLabel.setText(status);

        sotrVProjectTableModel = new SotrVProjectTableModel(c1.findSotrudnikPoKod(kod));
        table1.setModel(sotrVProjectTableModel);
        sotrVProjectTableModel.fireTableDataChanged();

        zadachaTableModel = new ZadachaTableModel(c1.findZadacha(kod));
        table3.setModel(zadachaTableModel);
        zadachaTableModel.fireTableDataChanged();
        table3.getColumnModel().getColumn(0).setMaxWidth(20);
        table3.getColumnModel().getColumn(2).setMaxWidth(80);
        table3.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        table3.setVisible(true);

        haracteristicaTableModel = new HaracteristicaTableModel(c1.findHaracteristica(kod));
        table5.setModel(haracteristicaTableModel);
        haracteristicaTableModel.fireTableDataChanged();
        table5.getColumnModel().getColumn(0).setMaxWidth(300);
        table5.getColumnModel().getColumn(0).setWidth(0);
        table5.getColumnModel().getColumn(0).setMinWidth(0);
        table5.getColumnModel().getColumn(0).setMaxWidth(0);
        table5.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        table5.setVisible(true);

        for (int i =0; i<table3.getModel().getRowCount(); i++){
            if (table3.getModel().getValueAt(i, 2).equals("100%")){
             lastZadacha = (String) table3.getModel().getValueAt(i+1, 1);
             zaverheno = zaverheno+1;
            }
        }

        procentLabel.setFont(new Font("Times Roman", Font.BOLD, 30));
        procentLabel.setText(String.valueOf(Math.round(zaverheno/table3.getModel().getRowCount()*100)) + "%");
        finLabel.setText(String.valueOf("Приход: " + c1.findSumma(idProject, 1) + " Расход: -" + c1.findSumma(idProject, 2)));
        dataLabel.setFont(new Font("Times Roman", Font.BOLD, 30));
        dataLabel.setText(String.valueOf(c1.findDataPostan(idProject)));
        System.out.println(lastZadacha);

        openFolderProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().open(new File(put));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBox1.getSelectedIndex()==0){
                    docTableModel = new DocTableModel(c1.findDoc(kod, 1));
                    table2.setModel(docTableModel);
                    docTableModel.fireTableDataChanged();
                }
                if (comboBox1.getSelectedIndex()==1){
                    docTableModel = new DocTableModel(c1.findDoc(kod, 2));
                    table2.setModel(docTableModel);
                    docTableModel.fireTableDataChanged();
                }
            }
        });
        insertDocButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewDoc newDoc = new NewDoc("Добавление нового документа в проект", 2, kod);
                newDoc.pack();
                newDoc.setSize(500, 200);
                newDoc.setLocationRelativeTo(null);
                newDoc.setVisible(true);
            }
        });

        table2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                selectedDoc = String.valueOf(table2.getModel().getValueAt(table2.getSelectedRow(), 0));
            }
        });

        dellDocButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (selectedDoc.equals("")){
                    JOptionPane.showMessageDialog(null, "Выберите документ");
                }
                else {
// TODO: сделать удаление
                }
            }
        });

        updateComment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.updateComment(kod, textArea1.getText());
                JOptionPane.showMessageDialog(null, "Коментарий обновлен");
            }
        });

        table3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                idSelectedZad = c1.findIdZadacha(kod, (Integer) table3.getModel().getValueAt(table3.getSelectedRow(), 0));
                podzadachaTableModel = new PodzadachaTableModel(c1.findPodzadacha(idSelectedZad));
                nomerSelectedZad = table3.getSelectedRow()+1;
                if ((int) podzadachaTableModel.getValueAt(0, 0)==0){
                    table4.setVisible(false);
                }
                else {
                    podzadachaTableModel = new PodzadachaTableModel(c1.findPodzadacha(idSelectedZad));
                    table4.setModel(podzadachaTableModel);
                    table4.getColumnModel().getColumn(0).setMaxWidth(20);
                    table4.getColumnModel().getColumn(2).setMaxWidth(100);
                    table4.getColumnModel().getColumn(3).setMaxWidth(100);
                    table4.getColumnModel().getColumn(4).setMinWidth(10);
                    table4.getColumnModel().getColumn(5).setMinWidth(10);
                    table4.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
                    table4.setVisible(true);
                    podzadachaTableModel.fireTableDataChanged();
                }
            }
        });
        insertNewZadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.insertNewZadacha(idProject, textField1.getText());
                zadachaTableModel = new ZadachaTableModel(c1.findZadacha(kod));
                table3.setModel(zadachaTableModel);
                zadachaTableModel.fireTableDataChanged();
                table3.getColumnModel().getColumn(0).setMaxWidth(20);
                table3.getColumnModel().getColumn(2).setMaxWidth(80);
                table3.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
                textField1.setText("");
            }
        });

        delZadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idSelectedZad == 0){
                    JOptionPane.showMessageDialog(null, "Выберите задачу");
                } else {
                    c1.deleteZadacha(nomerSelectedZad, idProject, idSelectedZad);
                    JOptionPane.showMessageDialog(null, "Задача удалена");
                    zadachaTableModel = new ZadachaTableModel(c1.findZadacha(kod));
                    table3.setModel(zadachaTableModel);
                    zadachaTableModel.fireTableDataChanged();
                    table3.getColumnModel().getColumn(0).setMaxWidth(20);
                    table3.getColumnModel().getColumn(2).setMaxWidth(80);
                    table3.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
                }
            }
        });
        buttonDownZad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idSelectedZad == 0){
                    JOptionPane.showMessageDialog(null, "Выберите задачу");
                } else {
                    c1.changeNomerZadacha(nomerSelectedZad, idProject, idSelectedZad, "posle");
                    zadachaTableModel = new ZadachaTableModel(c1.findZadacha(kod));
                    table3.setModel(zadachaTableModel);
                    zadachaTableModel.fireTableDataChanged();
                    table3.getColumnModel().getColumn(0).setMaxWidth(20);
                    table3.getColumnModel().getColumn(2).setMaxWidth(80);
                    table3.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
                }
            }
        });
        buttonUpZad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idSelectedZad == 0){
                    JOptionPane.showMessageDialog(null, "Выберите задачу");
                } else {
                    c1.changeNomerZadacha(nomerSelectedZad, idProject, idSelectedZad, "do");
                    zadachaTableModel = new ZadachaTableModel(c1.findZadacha(kod));
                    table3.setModel(zadachaTableModel);
                    zadachaTableModel.fireTableDataChanged();
                    table3.getColumnModel().getColumn(0).setMaxWidth(20);
                    table3.getColumnModel().getColumn(2).setMaxWidth(80);
                    table3.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
                }
            }
        });
        table4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                idSelectedPodzad =  c1.findIdPodzadacha(idSelectedZad, (Integer) table4.getModel().getValueAt(table4.getSelectedRow(), 0));
                nomerSelectedPodzadad = (Integer) table4.getModel().getValueAt(table4.getSelectedRow(), 0);
            }
        });
        buttonDownPodzad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idSelectedPodzad == 0){
                    JOptionPane.showMessageDialog(null, "Выберите подзадачу");
                } else {
                    c1.changeNomerPodzadacha(idSelectedZad, idSelectedPodzad, nomerSelectedPodzadad, "posle");
                    podzadachaTableModel = new PodzadachaTableModel(c1.findPodzadacha(idSelectedZad));
                    table4.setModel(podzadachaTableModel);
                    table4.getColumnModel().getColumn(0).setMaxWidth(20);
                    table4.getColumnModel().getColumn(2).setMaxWidth(100);
                    table4.getColumnModel().getColumn(3).setMaxWidth(100);
                    table4.getColumnModel().getColumn(4).setMinWidth(10);
                    table4.getColumnModel().getColumn(5).setMinWidth(10);
                    table4.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
                    table4.setVisible(true);
                    podzadachaTableModel.fireTableDataChanged();
                }
            }
        });
        buttonUpPodzad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idSelectedPodzad == 0){
                    JOptionPane.showMessageDialog(null, "Выберите подзадачу");
                } else {
                    c1.changeNomerPodzadacha(idSelectedZad, idSelectedPodzad, nomerSelectedPodzadad, "do");
                    podzadachaTableModel = new PodzadachaTableModel(c1.findPodzadacha(idSelectedZad));
                    table4.setModel(podzadachaTableModel);
                    table4.getColumnModel().getColumn(0).setMaxWidth(20);
                    table4.getColumnModel().getColumn(2).setMaxWidth(100);
                    table4.getColumnModel().getColumn(3).setMaxWidth(100);
                    table4.getColumnModel().getColumn(4).setMinWidth(10);
                    table4.getColumnModel().getColumn(5).setMinWidth(10);
                    table4.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
                    table4.setVisible(true);
                    podzadachaTableModel.fireTableDataChanged();
                }
            }
        });
        buttonDelPodzadacha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idSelectedPodzad == 0){
                    JOptionPane.showMessageDialog(null, "Выберите подзадачу");
                } else {
                    c1.deletePodzadacha(nomerSelectedPodzadad, idSelectedZad, idSelectedPodzad);
                    JOptionPane.showMessageDialog(null, "Подзадача удалена");
                    podzadachaTableModel = new PodzadachaTableModel(c1.findPodzadacha(idSelectedZad));
                    table4.setModel(podzadachaTableModel);
                    table4.getColumnModel().getColumn(0).setMaxWidth(20);
                    table4.getColumnModel().getColumn(2).setMaxWidth(100);
                    table4.getColumnModel().getColumn(3).setMaxWidth(100);
                    table4.getColumnModel().getColumn(4).setMinWidth(10);
                    table4.getColumnModel().getColumn(5).setMinWidth(10);
                    table4.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
                    table4.setVisible(true);
                    podzadachaTableModel.fireTableDataChanged();
                    zadachaTableModel = new ZadachaTableModel(c1.findZadacha(kod));
                    table3.setModel(zadachaTableModel);
                    zadachaTableModel.fireTableDataChanged();
                    table3.getColumnModel().getColumn(0).setMaxWidth(20);
                    table3.getColumnModel().getColumn(2).setMaxWidth(80);
                    table3.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
                }
            }
        });
        newPodzadachaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newPodzadacha.setVisible(true);
            }
        });

        insertNewPodzadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String nazvanie = textField2.getText();
                final String comment = textArea2.getText();
                final int idsotrudnik = comboBox2.getItemAt(comboBox2.getSelectedIndex()).getIdsotrudnik();
                c1.insertNewPodzadacha(idSelectedZad, nazvanie, idsotrudnik, comment);
                newPodzadacha.setVisible(false);
                textArea2.setText("");
                textField2.setText("");
                podzadachaTableModel = new PodzadachaTableModel(c1.findPodzadacha(idSelectedZad));
                table4.setModel(podzadachaTableModel);
                table4.getColumnModel().getColumn(0).setMaxWidth(20);
                table4.getColumnModel().getColumn(2).setMaxWidth(100);
                table4.getColumnModel().getColumn(3).setMaxWidth(100);
                table4.getColumnModel().getColumn(4).setMinWidth(10);
                table4.getColumnModel().getColumn(5).setMinWidth(10);
                table4.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
                table4.setVisible(true);
                podzadachaTableModel.fireTableDataChanged();
            }
        });

        table5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                idSelectedHar = (int) table5.getModel().getValueAt(table5.getSelectedRow(), 0);
            }
        });
        buttonDelHar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idSelectedHar != 0){
                    c1.deleteHaracteristica(idSelectedHar);
                    haracteristicaTableModel = new HaracteristicaTableModel(c1.findHaracteristica(kod));
                    table5.setModel(haracteristicaTableModel);
                    haracteristicaTableModel.fireTableDataChanged();
                    table5.getColumnModel().getColumn(0).setMaxWidth(300);
                    table5.getColumnModel().getColumn(0).setWidth(0);
                    table5.getColumnModel().getColumn(0).setMinWidth(0);
                    table5.getColumnModel().getColumn(0).setMaxWidth(0);
                    table5.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
                    table5.setVisible(true);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Выберите характеристику");
                }
            }
        });
        buttonAddHar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textArea3.getText().equals("")){
                    if (!insertNewHarRadioButton.isSelected()){
                        c1.insertHaracteristica( comboBox3.getItemAt(comboBox3.getSelectedIndex()).getIdharacteristica(), idProject, textArea3.getText());
                    }
                    if (insertNewHarRadioButton.isSelected()){
                        c1.insertHaracteristica( textField3.getText(), idProject, textArea3.getText());
                    }
                    haracteristicaTableModel = new HaracteristicaTableModel(c1.findHaracteristica(kod));
                    table5.setModel(haracteristicaTableModel);
                    haracteristicaTableModel.fireTableDataChanged();
                    table5.getColumnModel().getColumn(1).setMaxWidth(300);
                    table5.getColumnModel().getColumn(0).setWidth(0);
                    table5.getColumnModel().getColumn(0).setMinWidth(0);
                    table5.getColumnModel().getColumn(0).setMaxWidth(0);
                    table5.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
                    comboBox3.removeAllItems();
                    for (TipHaracteristica tipHaracteristica : c1.findTipHaracteristica()) {
                        comboBox3.addItem(tipHaracteristica);
                    }
                    textArea3.setText("");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Введите значение");
                }
            }
        });
        insertNewHarRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField3.setVisible(false);
                if (insertNewHarRadioButton.isSelected()){
                    textField3.setVisible(true);
                    System.out.println("da");
                }
                else {
                    textField3.setVisible(false);
                    System.out.println("net");
                }
            }
        });
        comboBox4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBox4.getSelectedIndex()==0){
                    financeTableModel = new FinanceTableModel(c1.findFinance(idProject, 1));
                    table6.setModel(financeTableModel);
                    financeTableModel.fireTableDataChanged();
                    table6.getColumnModel().getColumn(0).setWidth(0);
                    table6.getColumnModel().getColumn(0).setMinWidth(0);
                    table6.getColumnModel().getColumn(0).setMaxWidth(0);
                    table6.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
                    summaLabel.setText("Итого: " + c1.findSumma(idProject, 1));
                }
                if (comboBox4.getSelectedIndex()==1){
                    financeTableModel = new FinanceTableModel(c1.findFinance(idProject, 2));
                    table6.setModel(financeTableModel);
                    financeTableModel.fireTableDataChanged();
                    table6.getColumnModel().getColumn(0).setWidth(0);
                    table6.getColumnModel().getColumn(0).setMinWidth(0);
                    table6.getColumnModel().getColumn(0).setMaxWidth(0);
                    table6.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
                    summaLabel.setText("Итого: -" + c1.findSumma(idProject, 2));
                }
            }
        });
        table6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                idSelectedFinance = (int) table6.getModel().getValueAt(table6.getSelectedRow(), 0);
            }
        });

        delFinanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idSelectedFinance != 0){
                    c1.deleteFinance(idSelectedFinance);
                    financeTableModel = new FinanceTableModel(c1.findFinance(idProject, 2));
                    table6.setModel(financeTableModel);
                    financeTableModel.fireTableDataChanged();
                    table6.getColumnModel().getColumn(0).setWidth(0);
                    table6.getColumnModel().getColumn(0).setMinWidth(0);
                    table6.getColumnModel().getColumn(0).setMaxWidth(0);
                    table6.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
                    table6.setVisible(true);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Выберите финансовую операцию");
                }
            }
        });

        buttonInsertFin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!formattedTextField1.getText().equals("")){
                    BigDecimal money = new BigDecimal(formattedTextField1.getText());
                    c1.insertFinance(idProject, comboBox4.getItemAt(comboBox4.getSelectedIndex()).getIdtip_fin(), money, textArea4.getText());
                    financeTableModel = new FinanceTableModel(c1.findFinance(idProject, comboBox4.getSelectedIndex()+1));
                    table6.setModel(financeTableModel);
                    financeTableModel.fireTableDataChanged();
                    table6.getColumnModel().getColumn(0).setWidth(0);
                    table6.getColumnModel().getColumn(0).setMinWidth(0);
                    table6.getColumnModel().getColumn(0).setMaxWidth(0);
                    table6.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
                    table6.setVisible(true);
                    formattedTextField1.setText("");
                    textArea4.setText("");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Введите значение");
                }
            }
        });
        checkBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBox1.isSelected()) {
                    updateArchiv.setVisible(true);
                } else {
                    updateArchiv.setVisible(false);
                }
            }
        });
        окButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ryad = textField4.getText();
                String stolb = textField5.getText();
                String polka = textField6.getText();
                c1.updateArchiv(idProject, ryad, stolb, polka);
                JOptionPane.showMessageDialog(null, "Данные обновлены");
                labelRyadStolbPolka.setText(ryad + "-" + stolb + "-" + polka);
                updateArchiv.setVisible(false);
                checkBox1.setVisible(false);
                archivPanel.setVisible(true);
            }
        });
        buttonChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateArchiv.setVisible(true);
            }
        });
    }

    public void paint (Graphics g){
        super.paintComponents(g);

        g.setColor(Color.CYAN);
        g.drawOval(20,20,20,20);
    }


    private void createUIComponents() {
        comboBox1 = new JComboBox <TipDoc>();
        comboBox2 = new JComboBox <Sotrudnik>();
        comboBox3 = new JComboBox <TipHaracteristica>();
        comboBox4 = new JComboBox <TipFin>();
    }




}
