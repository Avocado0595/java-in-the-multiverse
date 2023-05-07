package org.example;

import javax.swing.*;
import java.awt.*;

public class View  {
    final int SIZE = 4;
    private JButton btn[][] = new JButton[SIZE][SIZE];
    private JPanel gamePanel;
    private JLabel lbStatus;
    private JFrame f;
    private JLabel lbScore;
    private JButton cancelBtn;

    public JButton[][] getBtn(){
        return this.btn;
    }
    public JLabel getLbStatus(){
        return lbStatus;
    }
    public JLabel getLbScore(){
        return lbScore;
    }
    public JFrame getF(){
        return f;
    }
    public JButton getBtnCancel(){
        return this.cancelBtn;
    }
    public void setButtonGrid(){
        int k=0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                k++;
                btn[i][j] = new JButton(k+"");
                btn[i][j].setName(k+"");
                btn[i][j].setBackground(Color.CYAN);
                btn[i][j].setActionCommand(i + "," + j);
                gamePanel.add(btn[i][j]);
            }
        }
    }
    public View() {
        initView();
    }

    private void initView(){
        f = new JFrame("Memory Game");
        //main frame
        f.setSize(620, 600);
        // header
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridLayout(1, 2));
        headerPanel.setBounds(0, 0, 600, 40);
        lbStatus = new JLabel("Lật một ô");
        lbScore= new JLabel("Bạn được 0 điểm", SwingConstants.RIGHT);
        headerPanel.add(lbStatus);
        headerPanel.add(lbScore);
        f.add(headerPanel);
        //game panel
        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(SIZE, SIZE));
        gamePanel.setBounds(0, 50, 600, 400);
        f.add(gamePanel);
        setButtonGrid();

        //footer
        cancelBtn = new JButton("Cancel");
        cancelBtn.setActionCommand("cancel");
        //cancelBtn.addActionListener(controller);
        cancelBtn.setBounds(260, 460, 80, 60);
        f.add(cancelBtn);
        //set frame visible
        f.setLayout(null);
    }
}