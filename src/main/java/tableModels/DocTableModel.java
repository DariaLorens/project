package tableModels;

import entity.Doc;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class DocTableModel extends AbstractTableModel {
    ArrayList<Doc> data;
    public DocTableModel(ArrayList<Doc> data) {
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
            default:
                return "";
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return data.get(rowIndex).getNomer() + " " + data.get(rowIndex).getNazvanie();
            case 1:
                return "Открыть";
            case 2:
                return "Скачать";
            case 3:
                return "Обновить";
            default:
                return "";
        }
    }
}
