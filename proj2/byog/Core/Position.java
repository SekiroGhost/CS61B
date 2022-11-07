package byog.Core;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void changePosition(int newX, int newY){
        x = newX;
        y = newY;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
    
}
