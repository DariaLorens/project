package tableModels;

import entity.Project;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class FullProjectTableModel extends AbstractTableModel {

    ArrayList<Project> data;

    public FullProjectTableModel(ArrayList<Project> data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Путь на сервере";
            case 1:
                return "Ряд-Столбец-Полка";
            case 2:
                return "Комментарий";
            default:
                return "";
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return data.get(rowIndex).getPut();
            case 1:
                return data.get(rowIndex).getRyad() + "-" + data.get(rowIndex).getStolb() + "-" + data.get(rowIndex).getPolka();
            case 2:
                return data.get(rowIndex).getComment();
            default:
                return "";
        }
    }
}