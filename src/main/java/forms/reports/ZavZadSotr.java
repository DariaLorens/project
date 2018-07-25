package forms.reports;

import DBpodcl.DBProcessor;
import tableModels.ZavZadSotrTableModel;

import javax.swing.*;
import java.awt.*;

public class ZavZadSotr extends JFrame {
    private JPanel zavZadSotr;
    private JTable table1;
    private JButton expButton;
    private JLabel otLabel;
    private JLabel doLabel;
    private ZavZadSotrTableModel zavZadSotrTableModel;
    DBProcessor c1 = new DBProcessor();

    public ZavZadSotr(String title, int idsotrudnik, String datado, String dataposle) throws HeadlessException {
        super(title);
        this.setContentPane(zavZadSotr);
        otLabel.setText(String.valueOf(datado));
        doLabel.setText(String.valueOf(dataposle));
        zavZadSotrTableModel = new ZavZadSotrTableModel(c1.findZavZadSotr(idsotrudnik, datado, dataposle));
        table1.setModel(zavZadSotrTableModel);
        zavZadSotrTableModel.fireTableDataChanged();
        table1.setVisible(true);
    }
}
