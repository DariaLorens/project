package forms.delete;

import javax.swing.*;
import java.awt.event.*;

public class DeleteSotr extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel label1;

    public DeleteSotr(String selectedFam, String selectedName, String selectedOtch) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        label1.setText("Вы уверены что хотите удалить сотрудника " + selectedFam + " " + selectedName + " " + selectedOtch + "?");


        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK(selectedFam, selectedName, selectedOtch);
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK(String selectedFam, String selectedName, String selectedOtch) {
        JOptionPane.showMessageDialog(null, "Заказчик " + selectedFam + " " + selectedName + " " + selectedOtch + " удален");
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
