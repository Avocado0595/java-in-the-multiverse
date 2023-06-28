package org.example;

import org.example.mst.Pair;

import java.util.*;

public class Dijkstra {
    private int v;
    private List<Pair>[] adj;

    public Dijkstra(int v){

        this.v=v+1;
        this.adj = new ArrayList[this.v];
        for(int i=0; i<this.v;i++){
            this.adj[i] = new ArrayList<>();
        }
    }
    public void addEdge(int u, int v, int w){
        this.adj[u].add(new Pair(v,w));
    }
    public void dijkstra(int s, int t){
        List<Integer> d = new ArrayList<>();//store distance
        for(int i=0; i<v;i++){
            d.add(Integer.MAX_VALUE);
        }
        d.set(s,0);
        //store distance and vertex
        PriorityQueue<Pair> q = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return Integer.compare(o1.getFirst(), o2.getFirst());
            }
        });
        q.add(new Pair(0,s));
        int[] previous = new int[v];
        Arrays.fill(previous,0);
        while(!q.isEmpty()){
            //select min distance from s
            Pair top = q.poll();
            //System.out.println(top);
            int u = top.getSecond();
            int distance = top.getFirst();
            if(distance>d.get(u)) continue;
            //relaxation: update distance from s to adj[u]

            for(Pair p:adj[u]){
                int v =p.getFirst();
                int w=p.getSecond();
                if(d.get(v)>d.get(u)+w){
                    d.set(v,d.get(u)+w);
                    q.add(new Pair(d.get(v),v));
                    previous[v] = u;
                }
            }
        }

        List<Integer> path = new ArrayList<>();
        while(true){
            path.add(t);
            if(s==t) break;
            t=previous[t];
        }

        for(int i:path){
            System.out.print(i+", ");
        }
        //print distance from s to each node
//        for(int i:d){
//            System.out.print(i+", ");
//        }


    }
    public static void demo(){
        Dijkstra test = new Dijkstra(7);
        test.addEdge(1,2,7);
        test.addEdge(1,3,12);
        test.addEdge(2,3,2);
        test.addEdge(2,4,9);
        test.addEdge(4,6,1);
        test.addEdge(3,5,10);
        test.addEdge(5,6,5);
        test.addEdge(5,4,4);
        test.dijkstra(1,5);

    }

}
