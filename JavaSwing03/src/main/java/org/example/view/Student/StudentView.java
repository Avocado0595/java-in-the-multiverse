package org.example.view.Student;

import org.example.model.Course;
import org.example.model.DAO.concrete.MySQLCourse;
import org.example.model.DAO.interfaces.CourseDAO;
import org.example.model.Enrollments;
import org.example.model.RegistedCourse;
import org.example.model.Student;
import org.example.model.TableModel.ComboCourseModel;
import org.example.model.TableModel.CourseTableModel;
import org.example.model.TableModel.RegistedCourseTableModel;
import org.example.model.TableModel.StudentTableModel;
import org.example.utils.DateLabelFormatter;
import org.example.utils.ValidateStudentInfo;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
    private JComboBox cbCourse;
    private JButton btnAddACourse;
    private JButton btnDeleteACourse;
    private RegistedCourseTableModel courseTableModel;
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
    private StudentTableModel studentTableModel;
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
        courseTableModel = new RegistedCourseTableModel();
        tbCourseList.setModel(courseTableModel);
        jscCourseList.setViewportView(tbCourseList);
        jscCourseList.setPreferredSize(new Dimension(300, 150));
        pnStudentCourses.add(lbStudentCourses);
        pnStudentCourses.add(jscCourseList);

        pnCourseControl = new JPanel();
        btnAddCourse = new JButton("ADD COURSE");
        btnDeleteCourse = new JButton("DELETE COURSE");
        setEnableCourseBtn(false);
        pnCourseControl.add(btnAddCourse);
        pnCourseControl.add(btnDeleteCourse);

        pnStudentCourses.add(pnCourseControl);
        pnStudentCourses.setLayout(new BoxLayout(pnStudentCourses,BoxLayout.Y_AXIS));
        cbCourse = new JComboBox();
        btnAddACourse = new JButton("ADD A COURSE");
        btnDeleteACourse = new JButton("DELETE A COURSE");
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


            studentTableModel = new StudentTableModel();
            tbStudentList.setModel(studentTableModel);


        jscStudentList.setViewportView(tbStudentList);
    }
    public void showStudentList(java.util.List<Student> studentList){
        studentTableModel.setData(studentList);
    }
    public void fireUpdateStudentTable(){
        studentTableModel.fireTableDataChanged();
    }
    public void fireUpdateCourseTable(){
        courseTableModel.fireTableDataChanged();
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
    public void addSelectCourseListener(ItemListener itemListener){
        cbCourse.addItemListener(itemListener);
    }

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
    public void addClickAddCourse(ActionListener listener){
        btnAddCourse.addActionListener(listener);
    }
    public void addClickAddACourse(ActionListener listener){
        btnAddACourse.addActionListener(listener);
    }
    public void addClickDeleteCourse(ActionListener listener){
        btnDeleteCourse.addActionListener(listener);
    }
    public void addClickDeleteACourse(ActionListener listener){btnDeleteACourse.addActionListener(listener);}
    //Method
    public void setEnableCourseBtn(boolean b){
        btnAddCourse.setEnabled(b);
        btnDeleteCourse.setEnabled(b);
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
            fillCourseTable(row);
        }

    }
    public void fillCourseTable(int row){
        MySQLCourse mySQLCourse = new MySQLCourse();
        java.util.List<RegistedCourse> registedCourses = null;
        try {
            registedCourses = mySQLCourse.findRegistedByStudentId(Integer.parseInt(tbStudentList.getValueAt(row,0).toString()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

        courseTableModel.setData(registedCourses);
        tbCourseList.setModel(courseTableModel);
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
    public void showCourseList(JFrame f, boolean isDelete){
        JDialog dlg = new JDialog(f);

        try {
            MySQLCourse courseDAO = new MySQLCourse();
            JPanel pnDialog = new JPanel();
            JPanel pnCombo = new JPanel();
            pnCombo.setLayout(new FlowLayout());
            List<Course> courseList;
            if(!isDelete){
                courseList = courseDAO.findNotRegisCourse(Integer.parseInt(txfStudentId.getText()));
            }
            else{
                courseList = courseDAO.findByStudentId(Integer.parseInt(txfStudentId.getText()));
            }
            DefaultComboBoxModel model = new DefaultComboBoxModel();
            for (int i = 0; i < courseList.size(); i++) {
                model.addElement(new ComboCourseModel(courseList.get(i).getId(),courseList.get(i).getName()));
            }

            cbCourse.setModel(model);
            JLabel lbcombo = new JLabel("Course list: ");
            pnCombo.add(lbcombo);
            pnCombo.add(cbCourse);

            pnDialog.add(pnCombo);
            if(!isDelete)
                pnDialog.add(btnAddACourse);
            else
                pnDialog.add(btnDeleteACourse);

            dlg.add(pnDialog);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //dlg.setLayout(new BorderLayout());
        dlg.setSize(450,150);
        dlg.setVisible(true);
    }
    public Enrollments getSelectedCourseId(){
        int courseId =  ((ComboCourseModel)cbCourse.getSelectedItem()).getKey();
        int studentId = Integer.parseInt(txfStudentId.getText());
        LocalDate enrollDate = LocalDate.now();
        return new Enrollments(-1, studentId,courseId, enrollDate);
    }


}
