package org.example.model.TableModel;

import org.example.model.Student;

import javax.swing.table.AbstractTableModel;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class StudentTableModel extends AbstractTableModel {
    private String[] columnNames = new String[] { "ID", "NAME", "EMAIL", "PHONE","DOB"};
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
        switch (columnIndex){
            case 0:
                return studentList.get(rowIndex).getId();
            case 1:
                return studentList.get(rowIndex).getName();
            case 2:
                return studentList.get(rowIndex).getEmail();
            case 3:
                return studentList.get(rowIndex).getPhone();
            case 4:
                return studentList.get(rowIndex).getDob().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            default:
                return null;
        }
    }
}
