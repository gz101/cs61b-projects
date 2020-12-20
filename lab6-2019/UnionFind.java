import java.util.Stack;

public class UnionFind {

    private int[] tracker;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Graph must have more than one vertex.");
        }

        tracker = new int[n];

        for (int i = 0; i < n; i++) {
            tracker[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    public void validate(int vertex) {
        if (vertex < 0 || vertex > tracker.length - 1) {
            throw new IllegalArgumentException("Invalid vertex on the graph.");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        validate(v1);
        return Math.abs(tracker[find(v1)]);
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        return tracker[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a
       vertex with itself or vertices that are already connected should not
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        int v1Size = sizeOf(v1);
        int v2Size = sizeOf(v2);
        int largerRoot;
        int smallerRoot;

        if (v1Size > v2Size) {
            largerRoot = v1;
            smallerRoot = v2;
        } else {
            largerRoot = v2;
            smallerRoot = v1;
        }
        tracker[largerRoot] += tracker[smallerRoot];
        tracker[smallerRoot] = largerRoot;
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);
        if (tracker[vertex] < 0) {
            return vertex;
        }

        Stack<Integer> path = new Stack<>();
        int root = vertex;

        // Finding the root while adding to stack of things to compress.
        while (tracker[vertex] > 0) {
            if (tracker[root] < 0) {
                break;
            }
            path.push(root);
            root = tracker[root];
        }

        // Path compression.
        while (!path.isEmpty()) {
            int vert = path.pop();
            tracker[vert] = root;
        }

        return root;
    }
}
