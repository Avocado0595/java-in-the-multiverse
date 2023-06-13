package org.example.adjlist;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AdjListGraph {
    private final int V;// số đỉnh
    private int E;// số cạnh

    private List<List<Integer>> adj;

    public AdjListGraph(int v){
        this.V=v;
        this.E =0;
        this.adj = new ArrayList<>();
        for(int i=0; i<V;i++){
            this.adj.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v){
        this.adj.get(u).add(v);
        this.adj.get(v).add(u);
    }

    public void dfs(int u, boolean[] visited){
        System.out.print(u+", ");
        visited[u] = true;
        for(int i:adj.get(u)){
            if(!visited[i]){
                dfs(i,visited);
            }
        }
    }
    public void bfs(int u){
        boolean[] visited = new boolean[V];
        Queue<Integer> q = new LinkedList<>();
        q.add(u);
        visited[u] = true;
        while(!q.isEmpty()){
            int v = q.peek();
            q.remove();
            System.out.print(v+", ");
            for(int i:adj.get(v)){
                if(!visited[i]){
                    q.add(i);
                    visited[i] =true;
                }
            }
        }
    }
    public void connectedComponent(){
        int connected = 0;
        boolean[]  visited = new boolean[V];
        for(int i=0; i<V; i++){
            if(!visited[i]){
                connected++;
                System.out.println("Connected component "+connected+ ": ");
                dfs(i, visited);
            }
        }
    }
}
