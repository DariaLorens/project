package forms.main;

import DBpodcl.DBProcessor;
import entity.Project;
import entity.Status;
import entity.Zakazchik;
import forms.delete.DeleteProject;
import forms.delete.DeleteSotr;
import forms.delete.DeleteZak;
import forms.insert.NewProject;
import forms.insert.RegistrPolz;
import forms.insert.RegistrZakazchik;
import forms.reports.ZavZadSotr;
import tableModels.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Projects extends JFrame{
    private JPanel project;
    private JTable table1;
    private JTextField textField1;
    private JTable table2;
    private JButton addProjectButton;
    private JButton editProjectButton;
    private JButton delProjectButton;
    private JTable table3;
    private JButton findZakButton;
    private JButton addZakazchikButton;
    private JTable table4;
    private JTextField textField2;
    private JButton findSotrButton;
    private JButton addSotrudhikButton;
    private JButton delZakButton;
    private JButton delSotrudnikButton;
    private JTextField textField3;
    private JButton findProjectButton;
    private JPanel poStatusu;
    private JPanel poZakazchik;
    private JComboBox comboBox1;
    private JComboBox<Status> comboBox2;
    private JComboBox<Zakazchik> comboBox3;
    private JButton poZakazButton;
    private JButton poStatusButton;
    private JTable table5;
    private JTabbedPane tabbedPane1;
    private JTable table6;
    private JPanel projects;
    private JPanel zadachi;
    private JPanel zakazchiki;
    private JPanel sotrudniki;
    private JPanel spravochniki;
    private JLabel harLabel;
    private JLabel docLabel;
    private JLabel polzLabel;
    private JTextField textField4;
    private JButton button1;
    private JTextField textField5;
    private JButton button2;
    private JButton button3;
    private JTextField textField6;
    private JTable table7;
    private JTable table8;
    private JTable table9;
    private JButton buttonEndZadacha;
    private JTextArea textArea1;
    private JButton buttonFindProject;
    private JTextField textField7;
    private JTextField textField8;
    private JButton buttonOtchetZad;
    private ProjectTableModel projectTableModel;
    private SotrudnikTableModel sotrudnikTableModel;
    private ZakazchikTableModel zakazchikTableModel;
    private FullProjectTableModel fullProjectTableModel;
    private MyProjectTableModel myProjectTableModel;
    private ZadachaTableModel zadachaTableModel;
    private PodzadachaTableModel podzadachaTableModel;
    private String selectedInn = "";
    private String selectedFam = "";
    private String selectedName = "";
    private String selectedOtch = "";
    private String selectedKod = "";
    private int selectedidsotrudnik;
    private int selectedIdProject;
    private int idSelectedZad;
    private int nomerSelectedZad;
    private int idSelectedPodzad;
    SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

    public Projects(String title, int idsotrudnik, int tipUser) throws HeadlessException {
        super(title);
        this.setContentPane(project);
        table2.setVisible(false);
        poStatusu.setVisible(false);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        textArea1.setBorder(border);

        if (tipUser == 2){
            tabbedPane1.removeTabAt(4);
        }
        if (tipUser == 3){
            tabbedPane1.removeTabAt(2);
            tabbedPane1.removeTabAt(2);
            tabbedPane1.removeTabAt(2);
            addProjectButton.setVisible(false);
            editProjectButton.setVisible(false);
            delProjectButton.setVisible(false);
        }


        DBProcessor c1 = new DBProcessor();

        for (Status status : c1.findAllStatus()) {
            comboBox2.addItem(status);
        }

        for (Zakazchik zakazchik : c1.findZakazchik()) {
            comboBox3.addItem(zakazchik);
        }

        harLabel.setText(String.valueOf(c1.findTipHaracteristica()));
        docLabel.setText(String.valueOf(c1.findAllTipDoc()));
        polzLabel.setText(String.valueOf(c1.findAllTipUser()));

        projectTableModel = new ProjectTableModel(c1.findProject());
        table1.setModel(projectTableModel);
        projectTableModel.fireTableDataChanged();
        table1.setVisible(true);

        sotrudnikTableModel = new SotrudnikTableModel(c1.findSotrudnik());
        table4.setModel(sotrudnikTableModel);
        sotrudnikTableModel.fireTableDataChanged();
        table4.setVisible(true);
        table4.getColumnModel().getColumn(0).setWidth(0);
        table4.getColumnModel().getColumn(0).setMinWidth(0);
        table4.getColumnModel().getColumn(0).setMaxWidth(0);

        zakazchikTableModel = new ZakazchikTableModel(c1.findZakazchik());
        table3.setModel(zakazchikTableModel);
        zakazchikTableModel.fireTableDataChanged();
        table3.setVisible(true);

        myProjectTableModel = new MyProjectTableModel(c1.findProjectSotr(idsotrudnik));
        table7.setModel(myProjectTableModel);
        myProjectTableModel.fireTableDataChanged();
        table7.setVisible(true);
        table7.getColumnModel().getColumn(0).setWidth(0);
        table7.getColumnModel().getColumn(0).setMinWidth(0);
        table7.getColumnModel().getColumn(0).setMaxWidth(0);

        addZakazchikButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrZakazchik registrZakazchik = new RegistrZakazchik("Добовление нового заказчика");
                registrZakazchik.pack();
                registrZakazchik.setLocationRelativeTo(null);
                registrZakazchik.setVisible(true);
            }
        });

        findZakButton.addActionListener(e -> {
            final String inn = textField1.getText();
            zakazchikTableModel = new ZakazchikTableModel(c1.findZakazchik(inn));
            if (zakazchikTableModel.getRowCount() > 0) {
                table3.setModel(zakazchikTableModel);
                zakazchikTableModel.fireTableDataChanged();
                table3.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Такого ИНН не существует!");
            }
        });

        addSotrudhikButton.addActionListener(e -> {
            RegistrPolz registrPolz = new RegistrPolz("Регистрация пользователей");
            registrPolz.pack();
            registrPolz.setLocationRelativeTo(null);
            registrPolz.setVisible(true);
        });

        addProjectButton.addActionListener(e -> {
            NewProject newProject = new NewProject("Новый проект", idsotrudnik);
            newProject.pack();
            newProject.setLocationRelativeTo(null);
            newProject.setVisible(true);
        });

        findSotrButton.addActionListener(e -> {
            final String fam = textField2.getText();
            sotrudnikTableModel = new SotrudnikTableModel(c1.findSotrudnik(fam));
            table4.setModel(sotrudnikTableModel);
            sotrudnikTableModel.fireTableDataChanged();
            table4.setVisible(true);
            table4.getColumnModel().getColumn(0).setWidth(0);
            table4.getColumnModel().getColumn(0).setMinWidth(0);
            table4.getColumnModel().getColumn(0).setMaxWidth(0);
        });

        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == 10) {
                    final String inn = textField1.getText();
                    zakazchikTableModel = new ZakazchikTableModel(c1.findZakazchik(inn));
                    if (zakazchikTableModel.getRowCount() > 0) {
                        table3.setModel(zakazchikTableModel);
                        zakazchikTableModel.fireTableDataChanged();
                        table3.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Такого ИНН не существует!");
                    }
                }
            }
        });

        textField2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == 10) {
                    final String fam = textField2.getText();
                    sotrudnikTableModel = new SotrudnikTableModel(c1.findSotrudnik(fam));
                    if (sotrudnikTableModel.getRowCount() > 0) {
                        table4.setModel(sotrudnikTableModel);
                        sotrudnikTableModel.fireTableDataChanged();
                        table4.setVisible(true);
                        table4.getColumnModel().getColumn(0).setWidth(0);
                        table4.getColumnModel().getColumn(0).setMinWidth(0);
                        table4.getColumnModel().getColumn(0).setMaxWidth(0);
                    } else {
                        JOptionPane.showMessageDialog(null, "Такого сотрудника не существует!");
                    }
                }
            }
        });

        ListSelectionModel selModelProject = table1.getSelectionModel();
        selModelProject.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                TableModel selectedProject = table1.getModel();
                selectedKod = String.valueOf(selectedProject.getValueAt(table1.getSelectedRow(), 1));
                fullProjectTableModel = new FullProjectTableModel(c1.findProject(selectedKod));
                table2.setModel(fullProjectTableModel);
                fullProjectTableModel.fireTableDataChanged();
                table2.setVisible(true);
            }
        });


        ListSelectionModel selModelZak = table3.getSelectionModel();
        selModelZak.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                TableModel selModelZak = table3.getModel();
                selectedInn = String.valueOf(selModelZak.getValueAt(table3.getSelectedRow(), 2));
                projectTableModel = new ProjectTableModel(c1.findProjectPoZakaz(selectedInn));
                table6.setModel(projectTableModel);
                projectTableModel.fireTableDataChanged();
                table6.setVisible(true);
            }
        });

        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBox1.getSelectedIndex() == 0) {
                    poStatusu.setVisible(false);
                    poZakazchik.setVisible(false);
                    projectTableModel = new ProjectTableModel(c1.findProject());
                    table1.setModel(projectTableModel);
                    projectTableModel.fireTableDataChanged();
                }
                if (comboBox1.getSelectedIndex() == 1) {
                    poStatusu.setVisible(true);
                    poZakazchik.setVisible(false);
                }
                if (comboBox1.getSelectedIndex() == 2) {
                    poStatusu.setVisible(false);
                    poZakazchik.setVisible(true);
                }
            }
        });

        poStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final int idstatus = comboBox2.getItemAt(comboBox2.getSelectedIndex()).getIdstatus();
                projectTableModel = new ProjectTableModel(c1.findProjectPoStatusu(idstatus));
                table1.setModel(projectTableModel);
                projectTableModel.fireTableDataChanged();
            }
        });
        poZakazButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final int idzakazchik = comboBox3.getItemAt(comboBox3.getSelectedIndex()).getIdzakazchik();
                projectTableModel = new ProjectTableModel(c1.findProjectPoZakaz(idzakazchik));
                table1.setModel(projectTableModel);
                projectTableModel.fireTableDataChanged();
            }
        });

        findProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String kod = textField3.getText();
                projectTableModel = new ProjectTableModel(c1.findProject(kod));
                table1.setModel(projectTableModel);
                projectTableModel.fireTableDataChanged();
                table1.setVisible(true);
            }
        });

        textField3.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == 10) {
                    final String kod = textField3.getText();
                    projectTableModel = new ProjectTableModel(c1.findProject(kod));
                    table1.setModel(projectTableModel);
                    projectTableModel.fireTableDataChanged();
                    table1.setVisible(true);
                }
            }
        });

        delZakButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedInn.equals("")) {
                    JOptionPane.showMessageDialog(null, "Выберите заказчика");
                } else {
                    DeleteZak deleteZak = new DeleteZak(selectedInn);
                    deleteZak.pack();
                    deleteZak.setLocationRelativeTo(null);
                    deleteZak.setVisible(true);
                }
            }
        });

        delSotrudnikButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedFam.equals("") && selectedName.equals("") && selectedOtch.equals("")) {
                    JOptionPane.showMessageDialog(null, "Выберите сотруднка");
                } else {
                    DeleteSotr deleteSotr = new DeleteSotr(selectedFam, selectedName, selectedOtch);
                    deleteSotr.pack();
                    deleteSotr.setLocationRelativeTo(null);
                    deleteSotr.setVisible(true);
                }

            }
        });

        delProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedKod.equals("")) {
                    JOptionPane.showMessageDialog(null, "Выберите проект");
                } else {
                    DeleteProject deleteProject = new DeleteProject(selectedKod);
                    deleteProject.pack();
                    deleteProject.setLocationRelativeTo(null);
                    deleteProject.setVisible(true);
                    System.exit(0);
                }
            }
        });

        editProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedKod.equals("")) {
                    JOptionPane.showMessageDialog(null, "Выберите проект");
                } else {
                    ArrayList<Project> project = c1.findProject(selectedKod);
                    Project temp = project.get(0);
                    String nazvanieTemp = temp.getNazvanie();
                    String kodTemp = temp.getKod();
                    String putTemp = temp.getPut();
                    String ryadTemp = temp.getRyad();
                    String stolbTemp = temp.getStolb();
                    String polkaTemp = temp.getPolka();
                    String commentTemp = temp.getComment();
                    String statusTemp = temp.getStatus();
                    String zakazchikTemp = temp.getZakazchik();


                    ProjectCard projectCard = new ProjectCard("Карточка проекта", nazvanieTemp, kodTemp,
                            putTemp, ryadTemp, stolbTemp, polkaTemp, commentTemp, statusTemp, zakazchikTemp);
                    projectCard.pack();
                    projectCard.setSize(1000, 500);
                    projectCard.setLocationRelativeTo(null);
                    projectCard.setVisible(true);
                }
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.insertTipHaracteristica(textField4.getText());
                harLabel.setText(String.valueOf(c1.findTipHaracteristica()));
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.insertNewTipDoc(textField5.getText());
                docLabel.setText(String.valueOf(c1.findAllTipDoc()));
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.insertNewTipUser(textField6.getText());
                polzLabel.setText(String.valueOf(c1.findAllTipUser()));
            }
        });

        table4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                selectedidsotrudnik = (int) table4.getModel().getValueAt(table4.getSelectedRow(), 0);
                projectTableModel = new ProjectTableModel(c1.findProjectSotr(selectedidsotrudnik));
                table5.setModel(projectTableModel);
                projectTableModel.fireTableDataChanged();
                table5.setVisible(true);
            }
        });
        table7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                selectedIdProject = (int) table7.getModel().getValueAt(table7.getSelectedRow(), 0);
                zadachaTableModel = new ZadachaTableModel(c1.findZadacha(selectedIdProject, idsotrudnik));
                table8.setModel(zadachaTableModel);
                zadachaTableModel.fireTableDataChanged();
                table8.getColumnModel().getColumn(0).setMaxWidth(20);
                table8.getColumnModel().getColumn(2).setMaxWidth(80);
                table8.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
                table8.setVisible(true);
            }
        });
        table8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                idSelectedZad = c1.findIdZadacha(selectedIdProject, (Integer) table8.getModel().getValueAt(table8.getSelectedRow(), 0));
                podzadachaTableModel = new PodzadachaTableModel(c1.findPodzadacha(idSelectedZad));
                nomerSelectedZad = table8.getSelectedRow()+1;
                if ((int) podzadachaTableModel.getValueAt(0, 0)==0){
                    table9.setVisible(false);
                }
                else {
                    podzadachaTableModel = new PodzadachaTableModel(c1.findPodzadacha(idSelectedZad, idsotrudnik));
                    table9.setModel(podzadachaTableModel);
                    table9.getColumnModel().getColumn(0).setMaxWidth(20);
                    table9.getColumnModel().getColumn(2).setMaxWidth(100);
                    table9.getColumnModel().getColumn(3).setMaxWidth(100);
                    table9.getColumnModel().getColumn(4).setMinWidth(10);
                    table9.getColumnModel().getColumn(5).setMinWidth(10);
                    table9.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
                    table9.setVisible(true);
                    podzadachaTableModel.fireTableDataChanged();
                }
            }
        });
        table9.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                idSelectedPodzad =  c1.findIdPodzadacha(idSelectedZad, (Integer) table9.getModel().getValueAt(table9.getSelectedRow(), 0));
            }
        });
        buttonEndZadacha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idSelectedPodzad == 0){
                    JOptionPane.showMessageDialog(null, "Выберите подзадачу");
                } else {
                    c1.updatePodzad(idSelectedPodzad, textArea1.getText());
                    podzadachaTableModel = new PodzadachaTableModel(c1.findPodzadacha(idSelectedZad, idsotrudnik));
                    table9.setModel(podzadachaTableModel);
                    table9.getColumnModel().getColumn(0).setMaxWidth(20);
                    table9.getColumnModel().getColumn(2).setMaxWidth(100);
                    table9.getColumnModel().getColumn(3).setMaxWidth(100);
                    table9.getColumnModel().getColumn(4).setMinWidth(10);
                    table9.getColumnModel().getColumn(5).setMinWidth(10);
                    table9.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
                    table9.setVisible(true);
                    podzadachaTableModel.fireTableDataChanged();
                    zadachaTableModel = new ZadachaTableModel(c1.findZadacha(selectedIdProject, idsotrudnik));
                    table8.setModel(zadachaTableModel);
                    zadachaTableModel.fireTableDataChanged();
                    table8.getColumnModel().getColumn(0).setMaxWidth(20);
                    table8.getColumnModel().getColumn(2).setMaxWidth(80);
                    table8.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
                    table8.setVisible(true);
                }
            }
        });
        buttonFindProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FindProject findProject = new FindProject("Поиск проекта по характеристикам");
                findProject.pack();
                findProject.setLocationRelativeTo(null);
                findProject.setVisible(true);
            }
        });

        buttonOtchetZad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField7.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Введите дату начала");
                    if (textField8.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Введите дату окончания");
                    }
                }
                else {
                    ZavZadSotr zavZadSotr = new ZavZadSotr("Отчет сотрудника по выполненным задачам", idsotrudnik,
                            textField7.getText(), textField8.getText());
                    zavZadSotr.pack();
                    zavZadSotr.setLocationRelativeTo(null);
                    zavZadSotr.setVisible(true);
                }
            }
        });
    }

    private void createUIComponents() {
        String [] comboBox1per = {"Без фильтра","Статусу", "Заказчику"};
        comboBox1 = new JComboBox(comboBox1per);
        comboBox2 = new JComboBox <Status>();
        comboBox3 = new JComboBox<Zakazchik>();
    }
}
