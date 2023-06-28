package org.example.adjmatrix;

import org.example.adjmatrix.AdjacencyGraph;

public class UndirectedAdjGraph extends AdjacencyGraph {
    public UndirectedAdjGraph(int V) {
        super(V);
    }

    @Override
    public void addEdge(int v, int w) {
        addEdgeAdj(v,w);
        addEdgeAdj(w,v);
        incEdge();
    }

    @Override
    public void removeEdge(int v, int w) {
        removeEdgeAdj(v,w);
        removeEdgeAdj(w,v);
    }
    public static void demo(){
        int n=6;
        AdjacencyGraph g1 = new UndirectedAdjGraph(n);
        g1.addEdge(0,1);
        g1.addEdge(0,2);
        g1.addEdge(1,3);
        g1.addEdge(3,4);
        g1.addEdge(2,4);
        g1.addEdge(2,5);
        g1.addEdge(5,6);
        System.out.println(g1.countNeighbor(0));
        g1.bfs(1);
        g1.findPathBfs(1,4);
        g1.findPathBfs(4,1);
    }
}
