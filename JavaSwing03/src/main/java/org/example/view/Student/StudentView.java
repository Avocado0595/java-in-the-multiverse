package org.example.view.Student;

import org.example.model.Course;
import org.example.model.DAO.concrete.MySQLCourse;
import org.example.model.DAO.concrete.MySQLStudent;
import org.example.model.Student;
import org.example.model.TableModel.CourseTableModel;
import org.example.model.TableModel.StudentTableModal;
import org.example.utils.DateLabelFormatter;
import org.example.utils.ValidateStudentInfo;
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
import java.util.Date;
import java.util.Properties;

import static javax.swing.JOptionPane.showMessageDialog;

public class StudentView extends JPanel {
    //menu
    private JPanel pnStudentMenu;
    private JButton btnNew;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnSave;
    //Data
    private JPanel pnStudentData;
    private JLabel lbStudentCourses;
    //course list
    private JPanel pnStudentCourses;
    private JScrollPane jscCourseList;
    private JTable tbCourseList;
    //button course
    private JPanel pnCourseControl;
    private JButton btnAddCourse;
    private JButton btnDeleteCourse;
    //Info
    private JPanel pnStudentInfo;
    private JTextField txfCourseName;
    private JDatePickerImpl dpkStudentDob;
    private JTextField txfStudentEmail;
    private JTextField txfStudentId;
    private JTextField txfStudentName;
    private JTextField txfStudentPhone;
    private JLabel lbStudentDob;
    private JLabel lbStudentEmail;
    private JLabel lbStudentId;
    private JLabel lbStudentName;
    private JLabel lbStudentPhone;
    //Table
    private JScrollPane jscStudentList;
    private JTable tbStudentList;
    private StudentTableModal studentTableModal;
    public StudentView(){

        initComponents();
    }
    private void initComponents(){

        initStudentMenu();

        panelStudentData();

        initTableStudentList();

        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        add(pnStudentData);
        add(pnStudentMenu);
        add(jscStudentList);

    }
    private void panelStudentInfo(){
        pnStudentInfo = new JPanel();
        pnStudentInfo.setLayout(new GridLayout(5,2));
        lbStudentId = new javax.swing.JLabel();
        txfStudentId = new javax.swing.JTextField();
        lbStudentName = new javax.swing.JLabel();
        txfStudentName = new javax.swing.JTextField();
        lbStudentEmail = new javax.swing.JLabel();
        txfStudentEmail = new javax.swing.JTextField();
        lbStudentPhone = new javax.swing.JLabel();
        txfStudentPhone = new javax.swing.JTextField();
        lbStudentDob = new javax.swing.JLabel();
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model,p);
        dpkStudentDob = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        lbStudentId.setText("ID");
        txfStudentId.setText("");
        txfStudentId.setEnabled(false);

        pnStudentInfo.add(lbStudentId);
        pnStudentInfo.add(txfStudentId);


        lbStudentName.setText("Name");
        txfStudentName.setText("");
        pnStudentInfo.add(lbStudentName);
        pnStudentInfo.add(txfStudentName);


        lbStudentEmail.setText("Email");
        pnStudentInfo.add(lbStudentEmail);

        txfStudentEmail.setText("");
        pnStudentInfo.add(txfStudentEmail);

        lbStudentPhone.setText("Phone");
        pnStudentInfo.add(lbStudentPhone);

        txfStudentPhone.setText("");
        pnStudentInfo.add(txfStudentPhone);

        lbStudentDob.setText("Date of birth");
        pnStudentInfo.add(lbStudentDob);

        pnStudentInfo.add(dpkStudentDob);


    }

    private void tableStudentCourse(){
        pnStudentCourses = new JPanel();
        lbStudentCourses = new JLabel("Registered courses");
        jscCourseList = new JScrollPane();
        tbCourseList = new JTable();
        tbCourseList.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null,null, null},
                },
                new String [] {
                        "ID", "Course Name", "Description"
                }
        ));
        jscCourseList.setViewportView(tbCourseList);
        jscCourseList.setPreferredSize(new Dimension(300, 150));
        pnStudentCourses.add(lbStudentCourses);
        pnStudentCourses.add(jscCourseList);

        pnCourseControl = new JPanel();
        btnAddCourse = new JButton("ADD COURSE");
        btnDeleteCourse = new JButton("DELETE COURSE");
        pnCourseControl.add(btnAddCourse);
        pnCourseControl.add(btnDeleteCourse);

        pnStudentCourses.add(pnCourseControl);
        pnStudentCourses.setLayout(new BoxLayout(pnStudentCourses,BoxLayout.Y_AXIS));

    }
    private void panelStudentData(){
        panelStudentInfo();
        tableStudentCourse();
        pnStudentData = new JPanel();
        pnStudentData.add(pnStudentInfo);
        pnStudentData.add(pnStudentCourses);
        pnStudentData.setLayout(new GridLayout(1,2,10,1));
    }
    private void initTableStudentList(){
        jscStudentList = new JScrollPane();
        tbStudentList = new JTable();


            studentTableModal = new StudentTableModal();
            tbStudentList.setModel(studentTableModal);


        jscStudentList.setViewportView(tbStudentList);
    }
    public void showStudentList(java.util.List<Student> studentList){
        studentTableModal.setData(studentList);
    }
    public void fireUpdateStudentTable(){
        studentTableModal.fireTableDataChanged();
    }
    private void initStudentMenu(){
        pnStudentMenu = new JPanel();
        btnNew = new JButton("NEW");
        btnAdd = new JButton("ADD");
        btnUpdate = new JButton("UPDATE");
        btnDelete = new JButton("DELETE");
        btnSave = new JButton("SAVE");
        btnSave.setEnabled(false);
        pnStudentMenu.setLayout(new FlowLayout());
        pnStudentMenu.add(btnNew);
        pnStudentMenu.add(btnAdd);
        pnStudentMenu.add(btnUpdate);
        pnStudentMenu.add(btnDelete);
        pnStudentMenu.add(btnSave);
    }

    //Event
    public void addSelectRowListener(ListSelectionListener listener){
        tbStudentList.getSelectionModel().addListSelectionListener(listener);
    }
    public void addClickNew(ActionListener listener){
        btnNew.addActionListener(listener);
        tbStudentList.clearSelection();
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
        return txfStudentId.getText().equals("");
    }
    public void fillSelectTable()  {
        int row = tbStudentList.getSelectedRow();
        if(row>=0){
            enableTextfield(false);
            txfStudentId.setText(tbStudentList.getValueAt(row,0).toString());
            txfStudentName.setText(tbStudentList.getValueAt(row,1).toString());
            txfStudentEmail.setText(tbStudentList.getValueAt(row,2).toString());
            txfStudentPhone.setText(tbStudentList.getValueAt(row,3).toString());
            dpkStudentDob.getJFormattedTextField().setText(tbStudentList.getValueAt(row,4).toString());
            MySQLCourse mySQLCourse = new MySQLCourse();
            java.util.List<Course> courses = null;
            try {
                courses = mySQLCourse.findByStudentId(Integer.parseInt(tbStudentList.getValueAt(row,0).toString()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
            CourseTableModel cstbmd = new CourseTableModel();
            cstbmd.setData(courses);
            tbCourseList.setModel(cstbmd);
        }

    }
    public void enableTextfield(boolean b){

        txfStudentName.setEnabled(b);
        txfStudentEmail.setEnabled(b);
        txfStudentPhone.setEnabled(b);
        dpkStudentDob.getComponent(1).setEnabled(b);
    }
    public void resetTextfield(){
        txfStudentId.setText("");
        txfStudentName.setText("");
        txfStudentEmail.setText("");
        txfStudentPhone.setText("");
        dpkStudentDob.getJFormattedTextField().setText("");
    }
    public Student getStudentInfo(){
        int id = txfStudentId.getText().equals("")?-1:Integer.parseInt(txfStudentId.getText());
        String name = txfStudentName.getText();
        String email = txfStudentEmail.getText();
        String phone = txfStudentPhone.getText();
        if(name.length()<3){
            showMessage("Invalid name");
            return null;
        }
        if(!ValidateStudentInfo.validateEmail(email)){
            showMessage("Invalid email");
            return null;
        }
        if(!ValidateStudentInfo.validatePhone(phone)){
            showMessage("Invalid phone");
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dob = null;
        try {
            String date = dpkStudentDob.getJFormattedTextField().getText();
            dob = LocalDate.parse(date, formatter);
        }
        catch(Exception ex){
            showMessage("Invalid date of birth");
            return null;
        }

        Student student = new Student(id,name, email, phone, dob);
        return student;
    }
    public void showMessage(String message){
        showMessageDialog(this,message);
    }
}
