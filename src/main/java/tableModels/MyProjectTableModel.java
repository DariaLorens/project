package tableModels;

import entity.Project;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class MyProjectTableModel extends AbstractTableModel {

    ArrayList<Project> data;

    public MyProjectTableModel(ArrayList<Project> data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "";
            case 1:
                return "Название";
            default:
                return "";
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return data.get(rowIndex).getIdproject();
            case 1:
                return data.get(rowIndex).getNazvanie();
            default:
                return "";
        }
    }
}