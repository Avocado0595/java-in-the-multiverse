package org.example.adjlist;

import org.example.mst.Pair;

import java.util.*;
import java.util.function.Predicate;

public class AdjListGraph {
    private final int V;// số đỉnh
    private int E;// số cạnh
    /**
     * <pre>
     * use {@code List<Pair>} store edge
     * </pre>
     */
    private final List<Pair> edgeList;
    private List<Integer> topo;
    private List<List<Integer>> reverseAdj;
    private List<List<Integer>> adj;
    public void getAdj(){
    for(List<Integer>i:adj){
        System.out.println(i);
    }
}
    public AdjListGraph(int v){
        edgeList = new ArrayList<>();
        this.V=v+1;
        this.E =0;
        this.adj = new ArrayList<>();
        this.reverseAdj = new ArrayList<>();
        for(int i=0; i<V;i++){
            this.adj.add(new ArrayList<>());
            this.reverseAdj.add(new ArrayList<>());
        }
        topo = new ArrayList<>();
    }

    public void addEdge(int u, int v){
        this.adj.get(u).add(v);
        this.adj.get(v).add(u);
        this.edgeList.add(new Pair(u,v));
        this.E++;
    }
    public void addEdgeDirect(int u, int v){
        this.adj.get(u).add(v);
        this.reverseAdj.get(v).add(u);
        this.E++;
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
    public void dfs(int u, boolean[] visited){
        visited[u] = true;
        for(int i:adj.get(u)){
            if(!visited[i]){
                dfs(i,visited);
            }
        }
    }

    /**
     *
     * @param u
     * @param parent
     * @param visited 0: not visited,1: visiting, 2: visited
     * @return
     */
    public boolean checkCircle(int u, int[] parent, int[] visited){
        visited[u] = 1;
        for(int v: adj.get(u)){
            if(visited[v]==0){
                parent[v] = u;
                if(checkCircle(v,parent,visited)) return true;
            }
            else{
                if(visited[v]==1) return true;
            }
        }
        visited[u] =2;
        return false;
    }
    ///////////////////////////////////////////////////

    /**
     * topo
     *     dfs(u)
     *      u -> stack
     *      v in loop adj list (u)
     *      dfs(v) => v->stack
     *     ....
     *      if no adj list => pop stack
     * @param u
     * @param visited
     */
    public void dfsTopo(int u, boolean[] visited){

        visited[u] = true;
        for(int i:adj.get(u)){
            if(!visited[i]){
                dfsTopo(i,visited);
            }
        }
        topo.add(u);
    }
    public List<Integer> getTopo(){
        boolean[] visited = new boolean[V];
        for(int i=0; i<V; i++){
            if(!visited[i])
                dfsTopo(i,visited);
        }
        return topo;
    }
    ///////////////////////////////////////////////////
    public int countConnectedComponent(){
        int connected = 0;
        boolean[]  visited = new boolean[V];
        for(int i=1; i<V; i++){
            if(!visited[i]){
                connected++;

                dfs(i, visited);
            }
        }
        return connected;
    }
    ////////////////////////////////////////////////////
    //find path part
    private void dfs(int u, boolean[] visited, int[] parent){
        visited[u] = true;
        for(int i: adj.get(u)){
            if(!visited[i]){
                parent[i] = u;
                dfs(i,visited,parent);
            }
        }
    }
    private void bfs(int u, boolean[] visited, int[] parent ){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(u);
        visited[u] = true;
        while(!queue.isEmpty()){
            int v = queue.peek();
            queue.remove();
            for(int i:adj.get(v)){
                if(!visited[i]){
                    visited[i] = true;
                    parent[i] = v;
                    queue.add(i);
                }
            }
        }
    }
    public void findPath(int u, int v){
        boolean[] visited = new boolean[V];
        int[] parent = new int[V];
        //dfs(u,visited,parent);
        bfs(u,visited,parent);
        if(!visited[v]){
            System.out.println("no path from " + u + " to "+v);
        }
        else{
            List<Integer> path = new ArrayList<>();
            while(v!=u){
                path.add(v);
                v=parent[v];
            }
            path.add(u);
            System.out.println(path);
        }
    }
    ///////////////////////////

    /**
     * Kosaraju -
     * Step 1: DFS and store vertice in stack
     */
    private void dfsKosaraju1(int u, boolean[] visited, Stack<Integer> stack){
        visited[u] = true;
        for(int v:adj.get(u)){
            if(!visited[v]){
                dfsKosaraju1(v, visited,stack);
            }
        }
        stack.push(u);
    }
    /**
     * Kosaraju -
     * Step 3: pop from stack => call dfs
     */
    private void dfsKosaraju2(int u, boolean[] visited){
        visited[u] = true;
        System.out.print(u+", ");
        for(int v:reverseAdj.get(u)){
            if(!visited[v]){
                dfsKosaraju2(v, visited);
            }
        }
    }
    /**
     * <pre>
     * Kosaraju: count Strongly Connnected Component
     * Step 1: DFS and store vertice in stack (like topo sort)
     * step 2: reverse graph
     * step 3: pop from stack => call dfs
     * </pre>
     */
    public void Kosaraju(){
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        //step 1,2
        for(int i=0;i<V; i++){
            if(!visited[i])
                dfsKosaraju1(i,visited,stack);
        }
        visited = new boolean[V];
        //step 3
        while(!stack.isEmpty()){
            int v = stack.pop();
            if(!visited[v]){
                dfsKosaraju2(v,visited);
                System.out.println();
            }

        }

    }

    /**
     * <pre>
     * Check bipartite at vertex s
     * - use bfs to traversal
     * - colored vertex n1 = 1
     * - if n2 adjacency n1:
     *   + if n2 no color: colored n2 = 0
     *   + if n2's color = n1's color => cannot be bipartite
     * </pre>
     */
    private boolean bfsBipartite(int s,short[] color){
        Queue<Integer> r = new LinkedList<>();
        color[s] = 1;
        r.add(s);
        while(!r.isEmpty()){
            int n1 = r.poll();
            for(Integer n2:this.adj.get(n1)){
                if(color[n2] == -1){
                    color[n2] = (short) (1-color[n1]);
                    r.add(n2);
                }
                else{
                    if(color[n2] == color[n1]){
                        return false;
                    }
                }
            }

        }

        return true;
    }
    /**
     * <pre>
     * Check bipartite graph
     * bfs all vertex
     * </pre>
     */
    public void bipartite(){
        short[] color = new short[V];
        for(int i=0;i<V;i++){
            color[i] =-1;
        }
        for(int i=1;i<V;i++){
            if(color[i] ==-1){
                if(!bfsBipartite(i, color)){
                    System.out.println("no bispart");
                    return;
                }
            }
        }
        System.out.println("bispart");
    }

    /**
     * bridge point: if remove this point, number of connected component will be increased
     */
    public void countBridgePoint(){
        int orgConnected = countConnectedComponent();
        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[V];

        int iConnected;
        for(int i=1;i<V; i++){
            Arrays.fill(visited,false);
            visited[i] = true;
            iConnected =0;
            for(int j=1; j<V; j++){
                if(!visited[j]){
                    iConnected++;
                    dfs(j, visited);
                }

            }
            if(iConnected > orgConnected)
                result.add(i);
        }
        System.out.println(result);
    }

    /**
     * <pre>
     * dfs for find edge bridge
     * edge u-v will remove to check bridge
     * </pre>
     */
    private void dfs(int j,int u, int v, boolean[] visited){
        visited[j] = true;
        for(int i:adj.get(j)){
            if((j==u&&i==v)||(j==v&&i==u)) continue;
            if(!visited[i]) dfs(i,u,v,visited);

        }
    }

    /**
     * bridge edge: if remove this edge, number of connected component will be increased
     */
    public void countBridgeEdge(){
        List<Pair> result = new ArrayList<>();
        int orgConnected = countConnectedComponent();
        boolean[] visited = new boolean[V];

        for(Pair i:edgeList){
            Arrays.fill(visited, false);
            int u = i.getFirst();
            int v = i.getSecond();
            int iConnected =0;
            for(int j=1; j<V; j++){
                if(!visited[j]){
                    iConnected++;
                    dfs(j,u,v,visited);
                }
            }
            if(iConnected>orgConnected){
                result.add(i);
            }
        }
        System.out.println(result);
    }

    /**
     * Euler
     */
    public void euler(int v){
        Stack<Integer> st = new Stack<>();
        Stack<Integer> ec = new Stack<>();
        st.add(v);
        List<List<Integer>> cAdj = new ArrayList<>(adj);
        while(!st.isEmpty()){
            v = st.peek();
            if(cAdj.get(v).size()!=0){
                int u = cAdj.get(v).get(0);
                cAdj.get(v).remove((Integer)u);

                 cAdj.get(u).remove((Integer) v);
                st.add(u);
            }
            else{
                v = st.pop();
                ec.add(v);
            }
        }
        while(!ec.isEmpty()){
            System.out.println(ec.pop());
        }

    }
    public static void demo(){
        int n=6;
        AdjListGraph a = new AdjListGraph(n);
//        a.addEdge(0,1);
//        a.addEdge(1,2);
//        a.addEdge(2,3);
//        a.addEdge(3,4);
//        a.addEdge(4,1);
//        a.addEdge(3,5);
//        a.addEdge(5,6);
//        a.addEdge(6,7);
//        a.addEdge(7,8);
//        a.addEdge(8,6);
//        List<Integer> t =a.getTopo();
//        for(int i: t){
//            System.out.print(i+", ");
//        }
        //System.out.println(a.checkCircle(1,new int[7], new int[7]));
        //a.Kosaraju();


        /////////////////////////////////////////
        a.addEdge(1,2);
        a.addEdge(2,5);
        a.addEdge(5,6);
        a.addEdge(2,6);
        a.addEdge(2,3);
        a.addEdge(3,4);


        //a.bipartieness();
        a.euler(1);
        //a.getAdj();
    }
}
