package tableModels;

import entity.Zadacha;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ZadachaTableModel extends AbstractTableModel {
    ArrayList<Zadacha> data;

    public ZadachaTableModel(ArrayList<Zadacha> data) {
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
                return "№";
            case 1:
                return "Название";
            case 2:
                return "Выполнено";
            default:
                return "";
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return data.get(rowIndex).getNomer();
            case 1:
                return data.get(rowIndex).getNazvanie();
            case 2:
                return data.get(rowIndex).getProcent()+"%";
            default:
                return "";
        }
    }
}
