package forms.insert;

import DBpodcl.DBProcessor;
import entity.TipDoc;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NewDoc extends JFrame {
    private JPanel newDoc;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox<TipDoc> comboBox1;
    private JButton insertButton;
    private JButton openButton;
    private JLabel putLabel;

    public static void copy(String sourcePath, String destinationPath) throws IOException {
        Files.copy(Paths.get(sourcePath), new FileOutputStream(destinationPath));
    }

    public NewDoc (String title, int idproject, String kod){
        super(title);
        this.setContentPane(newDoc);
        putLabel.setFont(new Font("Serif", Font.PLAIN, 13));
        putLabel.setText("Файл не выбран");

        final DBProcessor c1 = new DBProcessor();
        for (TipDoc tipDoc:c1.findAllTipDoc()){
            comboBox1.addItem(tipDoc);
        }


        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                int returnValue = jfc.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jfc.getSelectedFile();
                    putLabel.setText(selectedFile.getAbsolutePath());
                }
            }
        });

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomer = textField1.getText();
                String nazvanie = textField2.getText();
                if (putLabel.getText() == "Файл не выбран") {
                    System.out.println("Choose file");
                }
                else {
                    String fileName = nomer + " " + nazvanie +  putLabel.getText().substring(putLabel.getText().lastIndexOf("."));
                    String path = "D:\\Проекты тест\\" + kod + "\\" +
                            comboBox1.getItemAt(comboBox1.getSelectedIndex()) + "\\";
                    File newFile = new File(path + fileName);
                    try
                    {
                        newFile.createNewFile();
                        copy(putLabel.getText(), path + fileName);
                    }
                    catch(IOException ex){
                        System.out.println(ex.getMessage());
                    }
                }

//TODO:                c1.insertNewDoc(idproject, nomer, nazvanie, putLabel.getText(), comboBox1.getItemAt(comboBox1.getSelectedIndex()));
                JOptionPane.showMessageDialog(null,"Файл успешно загружен");
                dispose();
            }
        });
    }

    private void createUIComponents() {
        {
            comboBox1 = new JComboBox <TipDoc>();
        }
    }
}
