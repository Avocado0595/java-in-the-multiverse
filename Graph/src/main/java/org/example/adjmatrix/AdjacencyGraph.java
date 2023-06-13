package org.example.adjmatrix;

import java.util.*;

public abstract class AdjacencyGraph {
    private final int V;// số đỉnh
    private int E;// số cạnh
    private int[][] adj;// ma trận kề lưu đường đi giữa các đỉnh
    private int[] level;
    public int getLevel(int v){
        return level[v];
    }
    // init matrix
    public AdjacencyGraph(int V) {
        this.V = V;
        this.E = 0;
        this.adj = new int[V][V];
        for (int i = 0; i < V; i++)
            for (int j = 0; j < V; j++)
                adj[i][j] = 0;
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }
    protected void incEdge(){
        E++;
    }
    protected void addEdgeAdj(int v,int w){
        adj[v][w] = 1;
    }
    protected void removeEdgeAdj(int v,int w){
        adj[v][w] = 0;
    }
    // thêm cạnh từ đỉnh v tới đỉnh w (vô hướng)
    public abstract void addEdge(int v, int w);
    public abstract void removeEdge(int v, int w);

    /**
     *
     * @param v
     * @return list đỉnh liền kề của v
     */
    public Iterable<Integer> getAdjList(int v) {
        List<Integer> ls = new ArrayList<>();
        for (int i = 0; i < V; i++)
            if (this.adj[v][i] == 1)
                ls.add(i);
        return ls;
    }

    /**
     *
     * @param v
     * @return số đỉnh kề của v
     */
    public int countNeighbor(int v) {
        return ((ArrayList<Integer>)getAdjList(v)).size();
    }

    /**
     * in ma trận kề
     */
    public void printMatrix() {
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                System.out.print(adj[i][j] + ", ");
            }
            System.out.println();
        }
    }

    /**
     * Kiểm tra các thành phần liên thông trong graph
     * @param start
     */
    public void checkConnectedComponent(int start){
        boolean[] visited = new boolean[V];
        int connectedComponents=0;
        for(int i=0; i<V;i++){
            if(!visited[i]){

                this._dfs(i, visited);
                connectedComponents++;
            }
        }
        if(connectedComponents>1){
            System.out.println("There are "+connectedComponents+" components");
        }
        else{
            System.out.println("this is connected graph.");
        };
    }
    private void _dfs(int start, boolean[] visited) {
        visited[start] = true;
        for (int i = 0; i < this.adj[start].length; i++) {
            if (adj[start][i] == 1 && (!visited[i])) {
                _dfs(i, visited);
            }
        }
    }

    /**
     * Duyệt DFS
     * @param start
     * @param visited
     */
    public void dfs(int start, boolean[] visited) {
        System.out.print(start + ", ");
        visited[start] = true;
        for (int i = 0; i < this.adj[start].length; i++) {
            if (adj[start][i] == 1 && (!visited[i])) {
                dfs(i, visited);
            }
        }
    }

    /**
     * tìm tất cả các path từ a đến b
     * @param src
     * @param dst
     */
    public void findAllPathDfs(int src, int dst){
        boolean[] visited = new boolean[V];
        List<Integer> path = new ArrayList<>();
        path.add(src);
        findPathDfsUtil(src,dst,visited,path);

    }
    //path dfs util
    private void findPathDfsUtil(int src, int dst, boolean[] visited, List<Integer> path ){

        if(src==dst){
            System.out.print(path.toString());
            return;
        }
        visited[src] = true;
        for(int i=0; i<V; i++){
            if (adj[src][i] == 1&&!visited[i]){
                path.add(i);
                findPathDfsUtil(i,dst,visited,path);
                path.remove(path.indexOf(i));
            }
        }
        visited[src] = false;
    }

    /**
     * Duyệt BFS
     * @param start
     */
    public void bfs(int start) {
        boolean[] visited = new boolean[V];
        level = new int[V];
        List<Integer> q = new ArrayList<>();
        q.add(start);
        level[start]=0;
        visited[start] = true;
        int vis;
        while (!q.isEmpty()) {
            vis = q.get(0);

            // Print the current node
            System.out.print(vis + ", ");
            q.remove(q.get(0));

            for (int i = 0; i < V; i++) {
                if (adj[vis][i] == 1 && (!visited[i])){
                    level[i] = level[vis]+1;
                    q.add(i);
                    visited[i] = true;
                }
            }
        }

    }

    /**
     * tìm path ngắn nhất từ src đến dst bằng BFS
     * @param src
     * @param dst
     */
    public void findPathBfs(int src, int dst) {
        boolean[] visited = new boolean[V];
        int[] deep = new int[V];//độ sâu tính từ src
        int[] parent = new int[V];//parent[i] => vertex cha của i (theo đường từ src đến i)
        visited[src] = true;
        deep[src] = 0;
        parent[src] = -1;//src là cao nhất
        List<Integer>q = new ArrayList<>();
        q.add(src);
        int v;
        while(!q.isEmpty()){
            //lấy điềm đầu
            v = q.get(0);
            q.remove(q.get(0));
            for(int i=0; i<V; i++){
                if(!visited[i]&&adj[v][i]==1){
                    //xét điểm kề
                    visited[i]=true;
                    q.add(i);
                    deep[i]=deep[v]+1;//tăng 1 độ sâu kể từ điểm đầu
                    parent[i]=v;//vertex tiếp theo là con của vertex trước đó
                }
            }
        }
        if(!visited[dst]){
            System.out.println("no path");
        }
        else{
            List<Integer>path = new ArrayList<>();
            int x=dst;
            while(x!=-1){
                path.add(x);
                x=parent[x];//duyệt ngược từ dst đến src
            }
            for(int i=path.size()-1; i>0;i--){
                System.out.print(path.get(i)+"->");
            }
            System.out.println(path.get(0));

        }

    }

}
