package org.example.mst;

import java.util.*;

/**
 * tìm cây khung nhỏ nhất
 */
public class Prim {
    private int n;
    private int[] parent;
    private int[] d;
    /**
     * used[i] = true, i thuoc V(MST)
     * used[i] = false, i thuoc V
     */
    boolean[] used;
    private List<List<Pair>> adj;//Pair.first: lưu đỉnh, Pair.second: lưu trọng số (độ dài đường đi)
    public void init(int n){
        this.n=n;
        this.parent = new int[n+1];
        this.d = new int[n+1];
        used =new boolean[n+1];
        adj =  new ArrayList<>();
        for(int i=0; i<=n; i++){
            this.adj.add(new ArrayList<>());
            //parent[i] = i;
            d[i] = Integer.MAX_VALUE;
        }
    }

    public void addEdge(int u, int v, int w){
        adj.get(u).add(new Pair(v,w));
        adj.get(v).add(new Pair(u,w));
    }
    public void prim(int u){
        for(int i=0; i<=n; i++){
            used[i]=false;
        }
        //create empty mst
        List<Edge> mst=new ArrayList<>();
        int total=0;
        used[u] = true;
        //loop
        /**
         *
         */
        while(mst.size()<n-1){
            int x=0, y=0, minW=Integer.MAX_VALUE; //x thuoc V, y thuoc V(MST)
            //x-> y là cạnh ngắn nhất
            for(int i=1;i<=n; i++){
                if(used[i]){ //i thuoc V(MST)
                    for(Pair p:adj.get(i)){// tim dinh ke cua i
                        if(!used[p.getFirst()]&&p.getSecond()<minW){//m.getKey thuoc V, va co trong so w nho nhat
                            minW=p.getSecond();
                            x=p.getFirst();
                            y=i;
                        }
                    }
                }
            }
            mst.add(new Edge(x,y,minW)); // add vao mst
            total+=minW;
            used[x]=true;//danh dau x thuoc V(MST)
        }

        //return
        if(mst.size()!=n-1){
            System.out.println("no connected");
        }
        else{
            System.out.println("MST: "+total);
            for(Edge e:mst){
                System.out.println(e.getU() +" " + e.getV() +" " + e.getW());
            }
        }

    }

    public void prim2(int u){

        for(int i=0; i<=n; i++){
            used[i]=false;
        }
        PriorityQueue<Pair> q = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return Integer.compare(o1.getFirst(),o2.getFirst());
            }
        });
        List<Edge> mst = new ArrayList<>();
        int total = 0;
        q.add(new Pair(0,u));
        while(!q.isEmpty()){
            Pair top = q.poll();
            int w = top.getFirst();
            int v = top.getSecond();
            if(used[v]) continue;//v thuoc V(MST)
            total+=w;
            used[v] = true;
            if(u!=v){
                mst.add(new Edge(v,parent[v], w));
            }
            for(Pair i:adj.get(v)){
                int vv = i.getFirst();
                int ww = i.getSecond();
                if(!used[vv]&& ww<d[vv]){
                    q.add(new Pair(ww,vv));
                    d[vv] = ww;
                    parent[vv] = v;
                }
            }
        }

        //return
        System.out.println("MST: "+total);
        for(Edge e:mst){
            System.out.println(e.getU() +" " + e.getV() +" " + e.getW());
        }


    }
    public static void demo(){
        Prim test  = new Prim();
        test.init(6);
        test.addEdge(1,2,12);
        test.addEdge(1,3,4);
        test.addEdge(2,3,1);
        test.addEdge(2,4,5);
        test.addEdge(2,5,3);
        test.addEdge(3,5,2);
        test.addEdge(4,5,3);
        test.addEdge(4,6,10);
        test.addEdge(5,6,8);
        test.prim(1);
        test.prim2(1);
    }
}
