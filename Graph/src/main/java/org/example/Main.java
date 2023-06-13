package org.example;

import org.example.adjlist.AdjListGraph;
import org.example.adjmatrix.AdjacencyGraph;
import org.example.adjmatrix.DirectedAdjGraph;
import org.example.adjmatrix.UndirectedAdjGraph;

public class Main {
    public static void main(String[] args) {
//        int n= 7;
//        AdjacencyGraph g1 = new UndirectedAdjGraph(n);
//        g1.addEdge(0,1);
//        g1.addEdge(0,2);
//        g1.addEdge(1,3);
//        g1.addEdge(3,4);
//        g1.addEdge(2,4);
//        g1.addEdge(2,5);
//        g1.addEdge(5,6);
//        System.out.println(g1.countNeighbor(0));
//        g1.bfs(1);
//        g1.findPathBfs(1,4);
//        g1.findPathBfs(4,1);
//
//        AdjacencyGraph g2 = new DirectedAdjGraph(n);
//        g2.addEdge(0,1);
//        g2.addEdge(0,2);
//        g2.addEdge(1,3);
//        g2.addEdge(3,4);
//        g2.addEdge(2,4);
//        g2.addEdge(2,5);
//        g2.addEdge(5,6);
//        g2.findPathBfs(1,4);
//        g2.findPathBfs(4,1);

        AdjListGraph adjListGraph1 = new AdjListGraph(10);
        adjListGraph1.addEdge(1,2);
        adjListGraph1.addEdge(2,4);
        adjListGraph1.addEdge(1,3);
        adjListGraph1.addEdge(3,6);
        adjListGraph1.addEdge(3,7);
        adjListGraph1.addEdge(3,9);
        adjListGraph1.addEdge(1,5);
        adjListGraph1.addEdge(5,8);
        adjListGraph1.addEdge(8,9);
        boolean[] v = new boolean[10];
        adjListGraph1.connectedComponent();



    }

}