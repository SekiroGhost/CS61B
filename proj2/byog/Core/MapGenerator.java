package byog.Core;
import java.util.Random;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.Random;;

public class MapGenerator {
    private int width;
    private int length;
    protected int seed;
    protected Position pos;
    protected Random RANDOM;
    protected int[][] squarePos;
    protected int numSquare;
    private int playerX;
    private int playerY;
    private int gateX;
    private int gateY;

    public MapGenerator(int w, int l, int s,int n){
        this.width = w;
        this.length = l;
        this.seed = s;
        pos = new Position(0, 0);
        RANDOM = new Random(seed);
        squarePos = new int[n][2];
        numSquare = 0;
        playerX = 0;
        playerY = 0;
        gateX = 0;
        gateY = 0;
    }




    public void square(int posX, int posY, int halfLength, int halfWidth,TETile[][] tel){

        int maxLength = tel[0].length;
        int maxWidth = tel.length;

        if (posX - halfWidth < 1 || posX + halfWidth > maxWidth - 1) { return ;}
        if (posY - halfLength < 1 || posY + halfLength > maxLength - 1) { return ;}

        

        //add floor on the map

        for (int i = 0; i < halfWidth; i ++){
            for (int j = 0; j < halfLength; j ++){
                tel[posX + i][posY + j] = Tileset.FLOOR;
                tel[posX + i][posY - j] = Tileset.FLOOR;
                tel[posX - i][posY - j] = Tileset.FLOOR;
                tel[posX - i][posY + j] = Tileset.FLOOR;
            }
        }

        squarePos[numSquare][0] = posX;
        squarePos[numSquare][1] = posY;
        numSquare += 1;

        
    }


    protected void findWay(TETile[][] tel){
        int smallX;
        int largeX;
        int smallY;
        int largeY;

        for (int i = 0; i < numSquare-1; i++){
            if (squarePos[i][0] > squarePos[i+1][0]){
                smallX = squarePos[i+1][0];
                largeX = squarePos[i][0];
                smallY = squarePos[i+1][1];
            }
            else{
                smallX = squarePos[i][0];
                largeX = squarePos[i+1][0];
                smallY = squarePos[i][1];
            }

            for (int j = smallX; j <= largeX; j ++){
                tel[j][smallY] = Tileset.FLOOR;
            }

            if (squarePos[i][1] > squarePos[i+1][1]){
                smallY = squarePos[i+1][1];
                largeY = squarePos[i][1];
            }
            else{
                smallY = squarePos[i][1];
                largeY = squarePos[i+1][1];
            }
            for (int j = smallY; j <= largeY; j ++){
                tel[largeX][j] = Tileset.FLOOR;
            }
        }
    }

    protected void randomPosition(){
        int newX = RANDOM.nextInt(0, width);
        int newY = RANDOM.nextInt(0,length);
        
        pos.changePosition(newX, newY);
        
    }

    protected void addWall(TETile[][] tel){
        int maxLength = tel[0].length;
        int maxWidth = tel.length;

        //Corner
        for (int i = 1; i < maxWidth - 1 ; i ++){
            for ( int j = 1; j < maxLength - 1; j ++){
                if (tel[i][j] == Tileset.FLOOR){
                    addWallHelper(i, j, tel);
                }
            }
        }
    }

    private void addWallHelper(int i, int j,TETile[][] tel){
        for (int ii = i-1; ii < i + 2; ii ++){
            for (int jj = j-1; jj < j + 2; jj++){
                if (tel[ii][jj] == Tileset.NOTHING){
                    tel[ii][jj] = Tileset.WALL;
                }
            }
        }
    }

    protected void addGate(TETile[][] tel){
        int x = squarePos[0][0];
        int y = squarePos[0][1];

        for (int i = y; i > 0; i --){
            if (tel[x][i] == Tileset.WALL){
                tel[x][i] = Tileset.UNLOCKED_DOOR;
                gateX = x;
                gateY = i;
                break;
            }
        }
    }

    protected void addCharactor(TETile[][] tel){
        boolean find = false;
        while (!find){
        int x = RANDOM.nextInt(width);
        int y = RANDOM.nextInt(length);
        for (int i = x; i > 0; i --){
            if (tel[i][y] == Tileset.FLOOR){
                tel[i][y] = Tileset.PLAYER;
                playerX = i;
                playerY = y;
                find = true;
                break;
            }
        }
    }
    }

    public int getPlayerX(){
        return playerX;
    }

    public int getPlayerY(){
        return playerY;
    }

    public int getGateX(){
        return gateX;
    }

    public int getGateY(){
        return gateY;
    }

    public void updatePlayer(int x, int y, TETile[][] tel){
        if (x > width || y > length) return;
        if (tel[x][y] == Tileset.WALL) return;
        tel[playerX][playerY] = Tileset.FLOOR;
        tel[x][y] = Tileset.PLAYER;
        playerX = x;
        playerY = y;
    }

    public static void main(String[] args) {
        MapGenerator g = new MapGenerator(80, 30, 12345,100);

        TERenderer ter = new TERenderer();
        ter.initialize(g.width, g.length);

        TETile[][] tel = new TETile[g.width][g.length];

        for (int i = 0; i < g.width; i ++){
            for (int j = 0; j < g.length; j ++){
                tel[i][j] = Tileset.NOTHING;
            }
        }

        for ( int i = 0; i < 50; i ++){
            g.square(g.pos.getX(), g.pos.getY(), g.RANDOM.nextInt(1,5), g.RANDOM.nextInt(1,5), tel);
            //g.findWay(tel);
            g.randomPosition();
        }
        g.findWay(tel);
        g.addWall(tel);
        g.addGate(tel);
        ter.renderFrame(tel);
    }
}
