package hw4.puzzle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import edu.princeton.cs.algs4.MinPQ;


public class Solver {
    private int moves;
    private ArrayList<WorldState> res = new ArrayList<>();
    private Set<WorldState> previou = new HashSet<>();

    private class Node implements Comparable<Solver.Node>{
        int key;
        WorldState word;

        public Node(int key, WorldState word){
            this.key = key;
            this.word = word;
        }

        public int key(){
            return key;
        }

        @Override
        public int compareTo(Node o) {
            // TODO Auto-generated method stub
            return this.key - o.key;
        }
        
    }


    public Solver(WorldState initial){
        MinPQ<Node> pq = new MinPQ<>();
        moves = 0;
        res.add(initial);
        WorldState current = initial;
        while (current.estimatedDistanceToGoal() != 0){
            moves += 1;
            for (WorldState w : current.neighbors()){
                Node temp = new Node(moves+w.estimatedDistanceToGoal(),w);
                pq.insert(temp);
            }

            Node currentNode = pq.delMin();
            current = currentNode.word;
            moves = currentNode.key() - current.estimatedDistanceToGoal();
            res.add(current);
        }


    }

    public int moves(){
        return moves;
    }

    public Iterable<WorldState> solution(){
        return res;
    }

}
