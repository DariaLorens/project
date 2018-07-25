package tableModels;

import entity.Podzadacha;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class PodzadachaTableModel extends AbstractTableModel {
    ArrayList<Podzadacha> data;

    public PodzadachaTableModel(ArrayList<Podzadacha> data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "№";
            case 1:
                return "Название";
            case 2:
                return "Статус";
            case 3:
                return "ФИО";
            case 4:
                return "Дата постановки";
            case 5:
                return "Дата завершения";
            case 6:
                return "Коментарий";
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
                return data.get(rowIndex).getStatus();
            case 3:
                return data.get(rowIndex).getFam() + " " + data.get(rowIndex).getName().substring(0, 1) + ". " + data.get(rowIndex).getOtch().substring(0, 1) + ".";
            case 4:
                return data.get(rowIndex).getDataPostan();
            case 5:
                return data.get(rowIndex).getDataZaver();
            case 6:
                return data.get(rowIndex).getComment();
            default:
                return "";
        }
    }
}