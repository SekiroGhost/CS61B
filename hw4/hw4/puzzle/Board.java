package hw4.puzzle;
import java.lang.IndexOutOfBoundsException;
import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState{
    
    private int[][] tiles;
    private int size;
    private int[][] goal;
    private int BLANK = 0;

    public Board(int[][] tiles){
        this.size = tiles.length;
        this.tiles = new int[size][size];
        goal = new int[size][size];
        int index = 1;
        for (int i = 0; i < size; i ++){
            for (int j = 0; j < size; j ++){
                goal[i][j] = index;
                this.tiles[i][j] = tiles[i][j];
                index ++;
            }
        }

        goal[size-1][size-1] = 0;
    }

    public int tileAt(int i, int j){
        if ((i < 0 || i >= size) || (j < 0 || j >= size)){
            throw new IndexOutOfBoundsException("Outof boundary");
        }
        return tiles[i][j];
    }

    public int size(){
        return size;
    }

    public Iterable<WorldState> neighbors(){
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }
    public int hamming(){
        int hamming = 0;
        for (int i = 0; i < size; i ++){
            for (int j = 0; j < size; j ++){
                if (goal[i][j] != tiles[i][j]) hamming += 1;
            }
        }
        return hamming;
    }

    public int manhattan(){
        int manhattan = 0;
        int targetI;
        int targetJ;
        for (int i = 0; i < size; i ++){
            for (int j = 0; j < size; j ++){
                int current = tileAt(i, j);
                if (current == BLANK) continue;
                targetI = (current - 1) / size;
                targetJ = (current - 1) % size;
                manhattan += Math.abs(targetI - i) + Math.abs(targetJ - j);
            }
        }
        
        
        return manhattan;

    }

    public int estimatedDistanceToGoal(){
        return manhattan();
    }
    public boolean equals(Object y){
        if (this == y) return true;
        if (y == null && getClass() != y.getClass()) return false;
        Board board1 = (Board) y;
        return board1 == this;
    }

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
