package tableModels;

import entity.Project;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ProjectTableModel extends AbstractTableModel {

    ArrayList<Project> data;

    public ProjectTableModel(ArrayList<Project> data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Название";
            case 1:
                return "Код";
            case 2:
                return "Статус";
            case 3:
                return "Заказчик";
            default:
                return "";
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return data.get(rowIndex).getNazvanie();
            case 1:
                return data.get(rowIndex).getKod();
            case 2:
                return data.get(rowIndex).getStatus();
            case 3:
                return data.get(rowIndex).getZakazchik();
            default:
                return "";
        }
    }
}