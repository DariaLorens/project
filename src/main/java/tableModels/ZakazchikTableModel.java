package tableModels;

import entity.Zakazchik;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ZakazchikTableModel extends AbstractTableModel {

    ArrayList<Zakazchik> data;
    public ZakazchikTableModel (ArrayList<Zakazchik> data) {
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
            case 0: return "Название";
            case 1: return "Юр. адрес";
            case 2: return "ИНН";
            default: return "";
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: return data.get(rowIndex).getNazvanie();
            case 1: return data.get(rowIndex).getYr_adres();
            case 2: return data.get(rowIndex).getInn();
            default: return "";
        }
    }
}