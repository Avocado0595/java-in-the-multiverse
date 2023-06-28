package org.example.adjlist;

public class CaroGraph {
    private final int[] dx = new int[]{-1,0,0,1};
    private final int[] dy = new int[]{0,-1,1,0};
    private char a[][];
    private int V;
    public CaroGraph(int v,char[][] a ){
        this.V=v;
        this.a = a;
    }

    public void dfs(int i, int j,boolean[][] visited){
        visited[i][j] = true;
        System.out.println(i+" "+j);
        for(int k=0;k<4; k++){
            int i1 = i+dx[k];
            int j1 = j+dy[k];
            if(i1>=0 && i1<V && j1>=0 && j1<V
                    && a[i1][j1]=='x'&&!visited[i1][j1]){
                dfs(i1,j1,visited);
            }
        }
    }

    public void countConnected(boolean[][] visited){
        int count=0;
        for(int i=0; i<V; i++){
            for(int j=0; j<V; j++){
                if(a[i][j]=='x'&&!visited[i][j]){
                    System.out.println("Component "+count);

                    dfs(i,j,visited);
                    count++;
                }
            }
        }
    }
    public static void demo(){
        int n=6;
        char[][] a = new char[n][n];
        String[] raw = new String[]{
                "xxxooo",
                "ooxooo",
                "oxxxxo",
                "ooooox",
                "xxoxox",
                "xoxxox"};
        for(int i=0; i<n; i++){
            for(int j=0;j<n;j++){
                a[i][j] = (raw[i].split(""))[j].charAt(0);
            }
        }
        for(int i=0; i<n; i++){
            for(int j=0;j<n;j++){
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }

        CaroGraph c = new CaroGraph(n, a);
        c.countConnected(new boolean[n][n]);
    }
}
