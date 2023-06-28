package org.example;


public class DSU {
    private int n;
    private int[] parent;
    private int[] size;

    public DSU(int n) {
        this.n = n;
        this.parent = new int[n + 1];
        this.size = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int find(int a) {//find parent of a
        if (a == parent[a])
            return a;
        return find(parent[a]);
    }

    //path compression, if a,b in one set => no compression
    // fix: use size
    public int find_1(int a) {//find parent of a
        if (a == parent[a])
            return a;
        return parent[a] = find(parent[a]);
    }

    public void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            int temp;
            if (size[a] < size[b]) {
                temp = a;
                a = b;
                b = temp;
            }
            parent[b] = a;
            size[a] += size[b];
        }
    }

    public static void demo() {
        DSU test = new DSU(5);
        //test.union(1,2);
        test.union(3, 4);
        test.union(2, 3);
        System.out.println(test.find_1(3));
        System.out.println(test.find_1(4));
    }
}

