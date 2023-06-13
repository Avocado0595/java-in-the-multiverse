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
}
