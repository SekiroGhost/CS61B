package lab11.graphs;

import javax.print.attribute.standard.RequestingUserName;
import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import edu.princeton.cs.algs4.MinPQ;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = -1;
        edgeTo[s] = s;
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        return Math.abs(to2Dx(t) - to2Dx(v)) + Math.abs(to2Dy(t) - to2Dx(v));
    }

    private int to2Dx(int v){
        return v % maze.N() + 1;
    }

    private int to2Dy(int v){
        return v / maze.N() + 1;
    }

    private class Node implements Comparable<Node>{
        private int key;
        private int location;
        private int pre;

        public Node(int key, int location, int pre){
            this.key = key;
            this.location = location;
            this.pre = pre;
        }

        public int loc(){
            return location;
        }

        public int key(){
            return key;
        }

        public int pre(){
            return pre;
        }

        @Override public int compareTo(Node a){
            return key - a.key;
        }
    }

    /** Finds vertex estimated to be closest to target. */
    private void findMinimumUnmarked() {
        MinPQ<Node> q = new MinPQ<Node>();
        q.insert(new Node(h(s),s,s));
        while (!targetFound){
            Node current = q.delMin();
            int cur = current.loc();
            marked[cur] = true;
            distTo[cur] = distTo[current.pre()] + 1;
            edgeTo[cur] = current.pre();
            announce();
            if (cur == t) return;
            for (int w: maze.adj(cur)){
                if (!marked[w])
                q.insert(new Node(h(w), w,cur));
            }
        }



        return;
        /* You do not have to use this method. */
    }

    

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        // TODO
        findMinimumUnmarked();
    }

    @Override
    public void solve() {
        astar(s);
    }

}

