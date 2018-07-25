package tableModels;

import entity.ZavZadSotr;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ZavZadSotrTableModel extends AbstractTableModel {
    ArrayList<ZavZadSotr> data;
    public ZavZadSotrTableModel (ArrayList<ZavZadSotr> data) {
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
            case 0: return "Название проекта";
            case 1: return "Задача";
            case 2: return "Подзадача";
            case 3: return "Дата завершения";
            default: return "";
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: return data.get(rowIndex).getProject();
            case 1: return data.get(rowIndex).getZadacha();
            case 2: return data.get(rowIndex).getPodzadacha();
            case 3: return data.get(rowIndex).getDatazaver();
            default: return "";
        }
    }
}
