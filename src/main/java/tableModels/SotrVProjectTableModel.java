package tableModels;

import entity.Sotrudnik;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class SotrVProjectTableModel extends AbstractTableModel {

    ArrayList<Sotrudnik> data;

    public SotrVProjectTableModel(ArrayList<Sotrudnik> data) {
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
            case 0:
                return "Фамилия";
            case 1:
                return "Имя";
            case 2:
                return "Отчество";
            case 3:
                return "Должность";
            case 4:
                return "Процент участия в проекте";
            default:
                return "";
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return data.get(rowIndex).getFam();
            case 1:
                return data.get(rowIndex).getName();
            case 2:
                return data.get(rowIndex).getOtch();
            case 3:
                return data.get(rowIndex).getDolgnost();
            case 4:
                return "100%";
            default:
                return "";
        }
    }
}