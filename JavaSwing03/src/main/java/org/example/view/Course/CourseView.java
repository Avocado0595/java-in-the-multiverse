package org.example.view.Course;

import org.example.model.Course;
import org.example.model.DAO.concrete.MySQLCourse;
import org.example.model.Course;
import org.example.model.TableModel.CourseTableModel;
import org.example.model.TableModel.CourseTableModel;
import org.example.utils.DateLabelFormatter;
//import org.example.utils.ValidateCourseInfo;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import static javax.swing.JOptionPane.showMessageDialog;

public class CourseView extends JPanel {
        //menu
        private JPanel pnCourseMenu;
        private JButton btnNew;
        private JButton btnAdd;
        private JButton btnUpdate;
        private JButton btnDelete;
        private JButton btnSave;
        //Data
        private JPanel pnCourseData;
        //course list

        //Info
        private JPanel pnCourseInfo;
        private JTextField txfCourseName;
        private JTextField txfCourseDescription;
        private JTextField txfCourseId;
        private JLabel lbCourseId;
        private JLabel lbCourseName;
        private JLabel lbCourseDescription;
        //Table
        private JScrollPane jscCourseList;
        private JTable tbCourseList;
        private CourseTableModel courseTableModal;
        public CourseView(){

            initComponents();
        }
        private void initComponents(){

            initCourseMenu();

            panelCourseData();

            initTableCourseList();

            setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
            add(pnCourseData);
            add(pnCourseMenu);
            add(jscCourseList);

        }
        private void panelCourseInfo(){
            pnCourseInfo = new JPanel();
            pnCourseInfo.setLayout(new GridLayout(5,2));
            lbCourseId = new javax.swing.JLabel();
            txfCourseId = new javax.swing.JTextField();
            lbCourseName = new javax.swing.JLabel();
            txfCourseName = new javax.swing.JTextField();
            lbCourseDescription=new JLabel();
            txfCourseDescription=new JTextField();
            lbCourseId.setText("ID");
            txfCourseId.setText("");
            txfCourseId.setEnabled(false);

            pnCourseInfo.add(lbCourseId);
            pnCourseInfo.add(txfCourseId);


            lbCourseName.setText("Name");
            txfCourseName.setText("");
            pnCourseInfo.add(lbCourseName);
            pnCourseInfo.add(txfCourseName);


            lbCourseDescription.setText("Description");
            pnCourseInfo.add(lbCourseDescription);

            txfCourseDescription.setText("");
            pnCourseInfo.add(txfCourseDescription);

        }

public void fillSelectTable(){
    int row = tbCourseList.getSelectedRow();
    if(row>=0) {
        enableTextfield(false);
        txfCourseId.setText(tbCourseList.getValueAt(row, 0).toString());
        txfCourseName.setText(tbCourseList.getValueAt(row, 1).toString());
        txfCourseDescription.setText(tbCourseList.getValueAt(row, 2).toString());

    }
}
        private void panelCourseData(){
            panelCourseInfo();
            pnCourseData = new JPanel();
            pnCourseData.add(pnCourseInfo);

            pnCourseData.setLayout(new GridLayout(1,2,10,1));
        }
        private void initTableCourseList(){
            jscCourseList = new JScrollPane();
            tbCourseList = new JTable();


            courseTableModal = new CourseTableModel();
            tbCourseList.setModel(courseTableModal);


            jscCourseList.setViewportView(tbCourseList);
        }
        public void showCourseList(java.util.List<Course> courseList){
            courseTableModal.setData(courseList);
        }
        public void fireUpdateCourseTable(){
            courseTableModal.fireTableDataChanged();
        }
        private void initCourseMenu(){
            pnCourseMenu = new JPanel();
            btnNew = new JButton("NEW");
            btnAdd = new JButton("ADD");
            btnUpdate = new JButton("UPDATE");
            btnDelete = new JButton("DELETE");
            btnSave = new JButton("SAVE");
            btnSave.setEnabled(false);
            pnCourseMenu.setLayout(new FlowLayout());
            pnCourseMenu.add(btnNew);
            pnCourseMenu.add(btnAdd);
            pnCourseMenu.add(btnUpdate);
            pnCourseMenu.add(btnDelete);
            pnCourseMenu.add(btnSave);
        }

        //Event
        public void addSelectRowListener(ListSelectionListener listener){
            tbCourseList.getSelectionModel().addListSelectionListener(listener);
        }
        public void addClickNew(ActionListener listener){
            btnNew.addActionListener(listener);
            tbCourseList.clearSelection();
        }
        public void addClickAdd(ActionListener listener){
            btnAdd.addActionListener(listener);
        }
        public void addClickDelete(ActionListener listener){
            btnDelete.addActionListener(listener);
        }
        public void addClickUpdate(ActionListener listener){
            btnUpdate.addActionListener(listener);
        }
        public void addClickSave(ActionListener listener){
            btnSave.addActionListener(listener);
        }

        //Method
        public void setEnableSaveBtn(boolean b){
            btnSave.setEnabled(b);
        }
        public boolean isCreatingNew(){
            return txfCourseId.getText().equals("");
        }

        public void enableTextfield(boolean b){
            txfCourseName.setEnabled(b);
            txfCourseDescription.setEnabled(b);
        }
        public void resetTextfield(){
            txfCourseId.setText("");
            txfCourseName.setText("");
            txfCourseDescription.setText("");
        }
        public Course getCourseInfo(){
            int id = txfCourseId.getText().equals("")?-1:Integer.parseInt(txfCourseId.getText());
            String name = txfCourseName.getText();
            String description = txfCourseDescription.getText();
            if(name.length()<3){
                showMessage("Invalid name");
                return null;
            }

            Course course = new Course(id, name, description);
            return course;
        }
        public void showMessage(String message){
            showMessageDialog(this,message);
        }


}
