package tableModels;

import entity.Finance;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class FinanceTableModel extends AbstractTableModel {
    ArrayList<Finance> data;
    public FinanceTableModel(ArrayList<Finance> data) {
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
                return "";
            case 1:
                return "Дата";
            case 2:
                return "Сумма";
            case 3:
                return "Примечание";
            default:
                return "";
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return data.get(rowIndex).getIdfinance();
            case 1:
                return data.get(rowIndex).getDate();
            case 2:
                return data.get(rowIndex).getSumma();
            case 3:
                return data.get(rowIndex).getComment();
            default:
                return "";
        }
    }

}
