package org.example;

public class Model {
    private String[] data;
    public String[] getData(){
        return data;
    }
    public Model() {
        String[] rawData = new String[]{"Chó", "Mèo", "Gà", "Heo", "Vịt", "Bò", "Chim", "Chuột"};
        this.data = rawData;
    }
}

