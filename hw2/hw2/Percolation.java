package hw2;


import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[] openList;
    private WeightedQuickUnionUF upList;
    private WeightedQuickUnionUF antiBackwash;
    private int N;
    private int numOpen;
    private int toppest;
    private int bottom;
    public Percolation(int N){
        openList = new int[N*N];
        upList = new WeightedQuickUnionUF(N*N + 2);
        antiBackwash = new WeightedQuickUnionUF(N * N + 1);
        this.N = N;
        numOpen = 0;
        toppest = N * N ;
        bottom = N * N + 1;
        for ( int i = 0; i < N; i ++){
            upList.union(toppest, i);
            upList.union(bottom, N * (N-1) + i);
            antiBackwash.union(toppest, i);
        }
    }

    public void open(int row, int col){
        if (N <= 0) throw new java.lang.IllegalArgumentException();
        if (!checkIsIn(row, col)) throw new java.lang.IllegalArgumentException("Not in the rang");
        int index = N*row + col;
        if (openList[index] == 0){
            openList[index] = 1;
            numOpen += 1;
        }

        if ( row - 1 >= 0 && isOpen(row-1, col)) {
            upList.union(index, N * (row -1) + col);
            antiBackwash.union(index, N * (row -1) + col);
        }
        if ( col - 1 >= 0 && isOpen(row, col-1)) {
            upList.union(index, N * (row) + col -1);
            antiBackwash.union(index, N * (row) + col -1);
        }
        if ( col + 1 < N && isOpen(row, col + 1)) {
            upList.union(index, N * row + col + 1);
            antiBackwash.union(index, N * row + col + 1);
        }
        if ( row + 1 < N && isOpen(row + 1, col)) {
            upList.union(index, N * (row + 1) + col);
            antiBackwash.union(index, N * (row + 1) + col);
        }
    }

    public boolean isOpen(int row, int col){
        if (N <= 0) throw new java.lang.IllegalArgumentException();
        if (!checkIsIn(row, col)) throw new java.lang.IllegalArgumentException("Not in the rang");
        int index = N * row + col;
        return openList[index] - 1 == 0;
    }
    
    public boolean isFull(int row, int col){
        if (N <= 0) throw new java.lang.IllegalArgumentException();
        int index = row * N + col;
        if (!isOpen(row, col)) return false;
        if (antiBackwash.connected(toppest, index)) return true;
        return false;
    }

    public int numberOfOpenSites(){
        return numOpen;
    }

    public boolean percolates(){
        if (N <= 0) throw new java.lang.IllegalArgumentException();
        if (upList.connected(toppest, bottom)) return true;
        return false;
    }


    
    private boolean checkIsIn(int row, int col){
        if (N <= 0) return false;
        if (row < 0 || col < 0) return false;
        if (row * N + col > N * N) return false;
        return true;
    }




     public static void main(String[] args) {
        Percolation per = new Percolation(0);
        per.open(-1, 5);
    } 
}
