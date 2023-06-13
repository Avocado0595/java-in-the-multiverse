package org.example.adjmatrix;

import org.example.adjmatrix.AdjacencyGraph;

public class DirectedAdjGraph extends AdjacencyGraph {
    public DirectedAdjGraph(int V) {
        super(V);
    }

    @Override
    public void addEdge(int v, int w) {
        addEdgeAdj(v,w);
        incEdge();
    }

    @Override
    public void removeEdge(int v, int w) {
        removeEdgeAdj(v,w);
    }
}