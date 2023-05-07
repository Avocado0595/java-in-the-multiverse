package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static javax.swing.JOptionPane.showOptionDialog;

public class Controller implements ActionListener {
    final int STEP_SCORE =10;
    private String[][] data;
    private int[] firstSelection;
    private int[] secondSelection;
    private int score;
    private int maxScore;
    private View view;
    private Model model;
    public Controller(View view, Model model, int score){
        this.view = view;
        this.model = model;
        this.score = score;
        initController();
        this.data = this.getRandomData(model.getData(), view.getBtn().length);
        this.firstSelection=new int[]{-1,-1};
        this.secondSelection=new int[]{-1,-1};
        this.maxScore = STEP_SCORE*view.getBtn().length*2;
    }

    private void addAction(){
        for (int i = 0; i < view.getBtn().length; i++) {
            for (int j = 0; j < view.getBtn().length; j++) {
                view.getBtn()[i][j].addActionListener(this);
            }
        }
        view.getBtnCancel().addActionListener(this);
    }
    public void initController(){
        addAction();
        this.view.getLbScore().setText("Bạn được "+score+" điểm");
        view.getF().setVisible(true);
    }
    private void selectBtn(JButton bt, String data){
        bt.setBackground(Color.LIGHT_GRAY);
        bt.setText(data);
    }
    private void disSelectBtn(JButton bt){
        bt.setBackground(Color.CYAN);
        bt.setText(bt.getName());
    }
    private void showOption(){
        int option = showOptionDialog(this.view.getF(),"You win!\nMuốn tiếp tục không?","Thông báo nè!",
                JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null,null,null);
        if(option == JOptionPane.NO_OPTION) {
            this.view.getF().dispose();
            return;
        }
        else{
            new Controller(new View(), new Model(), this.score);
            this.view.getF().dispose();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if((firstSelection[0]!=-1 && secondSelection[0]!=-1)||(firstSelection[0]==-1 && secondSelection[0]==-1))
            view.getLbStatus().setText("Lật thêm 1 ô");
        else if(firstSelection[0]!=-1 && secondSelection[0]==-1)
                view.getLbStatus().setText("Lật 1 ô");

        //dừng chương trình
        if(e.getActionCommand() == "cancel"){
            showOption();
            return;
        }
        //lấy event
        int i = Integer.parseInt(e.getActionCommand().split(",")[0]);
        int j = Integer.parseInt(e.getActionCommand().split(",")[1]);
        //nếu bấm 1 nút 2 lần
        if(firstSelection[0] ==i && firstSelection[1]==j){
            disSelectBtn(view.getBtn()[i][j]);
            firstSelection = new int[]{secondSelection[0],secondSelection[1]};
            secondSelection = new int[]{-1,-1};
            return;
        }
        if(secondSelection[0] ==i && secondSelection[1]==j){
            disSelectBtn(view.getBtn()[i][j]);
            secondSelection = new int[]{-1,-1};
            return;
        }
        //đổi màu nút chọn
        selectBtn(view.getBtn()[i][j], data[i][j]);
        if(firstSelection[0]==-1&&secondSelection[0]==-1){
            //click ô đầu tiên hoặc ô tiếp theo 2 ô trùng
            firstSelection = new int[]{i,j};
            return;
        }
        if(firstSelection[0]!=-1&&secondSelection[0]==-1){
            //ô thứ 2
            secondSelection = new int[]{i,j};
            if(data[firstSelection[0]][firstSelection[1]].equals(data[secondSelection[0]][secondSelection[1]])){
                //ô thứ 2 trùng với ô thứ 1
                view.getBtn()[firstSelection[0]][firstSelection[1]].setEnabled(false);
                view.getBtn()[secondSelection[0]][secondSelection[1]].setEnabled(false);
                firstSelection = new int[]{-1,-1};
                secondSelection = new int[]{-1,-1};
                score+=STEP_SCORE;
                this.view.getLbScore().setText("Bạn được "+score+" điểm");
                if(score == this.maxScore){
                    showOption();
                }

                return;
            }
            return;
        }
        if(firstSelection[0]!=-1&&secondSelection[0]!=-1){
            disSelectBtn(view.getBtn()[firstSelection[0]][firstSelection[1]]);
            disSelectBtn(view.getBtn()[secondSelection[0]][secondSelection[1]]);
            firstSelection = new int[]{i,j};
            secondSelection = new int[]{-1,-1};
            return;
        }

    }

    private  String[][] getRandomData(String[] data,int size){
        String[][] result = new String[size][size];
        java.util.List<String> doubleData = new ArrayList<>();
        List<Integer> usedData = new ArrayList<>();
        for(int i=0;i<size*size; i++){
            doubleData.add(data[i%(size*2)]);
        }

        Random generator = new Random();
        for(int i=0;i<size; i++){
            for(int j=0; j<size;j++) {
                int nextIndex = -1;
                do {
                    nextIndex = generator.nextInt(0,size * size);
                    if (!usedData.contains(nextIndex))
                        break;

                }
                while (true);
                result[i][j] = doubleData.get(nextIndex);
                usedData.add(nextIndex);
            }
        }
        return result;
    }
}
