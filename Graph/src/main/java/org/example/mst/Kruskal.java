package org.example.mst;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * tìm cây khung nhỏ nhất
 */
public class Kruskal {
    private int n;
    private int[] parent;
    private int[] size;
    List<Edge> edgeList;
    public void makeSet(int n){
        this.n=n;
        edgeList=new ArrayList<Edge>();
        parent=new int[n+1];
        size = new int[n+1];
        for(int i=1; i<=n; i++){
            parent[i]=i;
            size[i]=1;
        }
    }
    public int find(int v){
        if(v==parent[v]) return v;
        return parent[v] = find(parent[v]);
    }
    public boolean union(int a, int b){
        a=find(a);
        b=find(b);
        if(a==b) return false;
        if(size[a]<size[b]){
            int temp =a;
            a=b;
            b=temp;
        }
        parent[b]=a;
        size[a]+=size[b];
        return true;
    }
    public void addEdge(int u, int v, int w){
        edgeList.add(new Edge(u,v,w));
    }
    public void kruskal(){
        //create empty mst
        List<Edge> mst=new ArrayList<>();
        int d=0;
        //sort edge list
        edgeList.sort(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return Integer.compare(o1.getW(), o2.getW());
            }
        });
        //loop edge
        for(Edge e: edgeList){
            if(mst.size()==n-1) break;
            if(union(e.getU(), e.getV())) {
                mst.add(e);
                d += e.getW();
            }
        }
        //return
        if(mst.size()!=n-1){
            System.out.println("no connected");
        }
        else{
            System.out.println("MST: ");
            for(Edge e:mst){
                System.out.println(e.getU() +" " + e.getV() +" " + e.getW());
            }
        }

    }
    public static void demo(){
        Kruskal test  = new Kruskal();
        test.makeSet(6);
        test.addEdge(1,2,12);
        test.addEdge(1,3,4);
        test.addEdge(2,3,1);
        test.addEdge(2,4,5);
        test.addEdge(2,5,3);
        test.addEdge(3,5,2);
        test.addEdge(4,5,3);
        test.addEdge(4,6,10);
        test.addEdge(5,6,8);
        test.kruskal();
    }
}
