package tableModels;

import entity.Sotrudnik;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class SotrudnikTableModel extends AbstractTableModel {

    ArrayList<Sotrudnik> data;
    public SotrudnikTableModel (ArrayList<Sotrudnik> data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "";
            case 1: return "Фамилия";
            case 2: return "Имя";
            case 3: return "Отчество";
            case 4: return "Должность";
            default: return "";
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: return data.get(rowIndex).getIdsotrudnik();
            case 1: return data.get(rowIndex).getFam();
            case 2: return data.get(rowIndex).getName();
            case 3: return data.get(rowIndex).getOtch();
            case 4: return data.get(rowIndex).getDolgnost();
            default: return "";
        }
    }
}
