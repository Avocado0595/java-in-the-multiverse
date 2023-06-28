package org.example.adjlist;

import java.util.LinkedList;
import java.util.Queue;

public class CaroPath {
    private final int[] dx = new int[]{-1, 0, 0, 1};
    private final int[] dy = new int[]{0, -1, 1, 0};
    private char a[][];
    private int V;

    public CaroPath(int v, char[][] a) {
        this.V = v;
        this.a = a;
    }

    public boolean dfs(int i, int j, boolean[][] visited, int[][] d) {

        if (a[i][j] == 'E')
            return true;
        visited[i][j] = true;
        System.out.println(i + " " + j);
        for (int k = 0; k < 4; k++) {
            int i1 = i + dx[k];
            int j1 = j + dy[k];
            if (i1 >= 0 && i1 < V && j1 >= 0 && j1 < V
                    && a[i1][j1] != 'o' && !visited[i1][j1]) { // !='o' not =='x', maybe =='E'
                d[i1][j1] = d[i][j] + 1;
                if (dfs(i1, j1, visited, d)) return true;
            }
        }
        return false;
    }

    public boolean bfs(int i, int j, int[][] d) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});
        boolean[][] visited = new boolean[V][V];
        visited[i][j] = true;

        d[i][j] = 0;
        System.out.println(i + " " + j);
        while (!q.isEmpty()) {
            int[] v = q.peek();
            q.remove();
            for (int k = 0; k < 4; k++) {
                int i1 = v[0] + dx[k];
                int j1 = v[1] + dy[k];
                if (i1 >= 0 && i1 < V && j1 >= 0 && j1 < V
                        && a[i1][j1] != 'o' && !visited[i1][j1]) {
                    System.out.println(i1 + " " + j1);
                    d[i1][j1] = d[v[0]][v[1]] + 1;
                    if (a[i1][j1] == 'E')
                        return true;
                    q.add(new int[]{i1, j1});
                    visited[i1][j1] = true;
                }
            }
        }
        return false;
    }

    public void findPathBfs(int startX, int startY, int endX, int endY) {
        int[][] d = new int[V][V];
        if (bfs(startX, startY, d)) {

            System.out.println("have a path: " + d[endX][endY]);
        } else {
            System.out.println("have no path");
        }
    }

    public void findPathDfs(int startX, int startY, int endX, int endY) {
        int[][] d = new int[V][V];
        d[startX][startY] = 0;
        if (dfs(startX, startY, new boolean[V][V], d)) {
            System.out.println("have a path: " + d[endX][endY]);
        } else {
            System.out.println("have no path");
        }
    }

    public static void demo() {
        int n = 6;
        char[][] a = new char[n][n];
        //x can go, o: block
        String[] raw = new String[]{
                "oooxxx",
                "xSxxxx",
                "xoooxx",
                "xxoxxx",
                "oxExxo",
                "oxooxo"};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = (raw[i].split(""))[j].charAt(0);
            }
        }
        CaroPath cp = new CaroPath(n, a);
        cp.findPathBfs(1, 1, 4, 2);
        cp.findPathDfs(1, 1, 4, 2);
    }

}
