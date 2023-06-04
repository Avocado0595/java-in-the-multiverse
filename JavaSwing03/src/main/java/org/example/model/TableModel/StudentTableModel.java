package org.example.model.TableModel;

import org.example.model.Student;

import javax.swing.table.AbstractTableModel;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class StudentTableModel extends AbstractTableModel {
    private final String[] columnNames = new String[] { "ID", "NAME", "EMAIL", "PHONE","DOB"};
    private List<Student> studentList;
    public StudentTableModel(){
        studentList = new ArrayList<>();
    }
    public void setData(List<Student> studentList){
        this.studentList = studentList;
        fireTableDataChanged();

    }
    @Override
    public String getColumnName(int i){
        return columnNames[i];
    }
    @Override
    public int getRowCount() {
        return studentList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> studentList.get(rowIndex).getId();
            case 1 -> studentList.get(rowIndex).getName();
            case 2 -> studentList.get(rowIndex).getEmail();
            case 3 -> studentList.get(rowIndex).getPhone();
            case 4 -> studentList.get(rowIndex).getDob().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            default -> null;
        };
    }
}
