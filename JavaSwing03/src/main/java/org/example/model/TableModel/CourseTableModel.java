package org.example.model.TableModel;

import org.example.model.Course;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class CourseTableModel extends AbstractTableModel {
    private final String[] columnNames = new String[] { "ID", "NAME", "DESCRIPTION"};
    private List<Course> courseList;
    public CourseTableModel(){
        courseList = new ArrayList<>();
    }
    public void setData(List<Course> courseList){
        this.courseList = courseList;
        fireTableDataChanged();
    }
    @Override
    public String getColumnName(int i){
        return columnNames[i];
    }

    @Override
    public int getRowCount() {
        return courseList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> courseList.get(rowIndex).getId();
            case 1 -> courseList.get(rowIndex).getName();
            case 2 -> courseList.get(rowIndex).getDescription();
            default -> null;
        };

    }
}
