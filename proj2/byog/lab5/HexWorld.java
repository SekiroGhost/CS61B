package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

import javax.swing.text.Position;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 40;
    private static final int HEIGHT = 40;

    public static void addHexagon(int n, TETile[][] ter, int x, int y, TETile type){
        int start, end;
        int maxLength = n + 2*(n -1);
        start = n-1;
        end = n;
        for (int i = 0; i < n; i ++){
            int num = end;
            for (int j = 0; j < maxLength; j ++){
                if (j >= start && num != 0){
                    ter[j+x][i+y] = type;
                    num -= 1;
                }
            }
            start -= 1;
            end += 2;
        }

        end -= 2;
        start += 1;

        for (int i = n; i < n+n; i ++){
            int num = end;
            for (int j = 0; j < maxLength; j ++){
                if (j >= start && num != 0){
                    ter[j+x][i+y] = type;
                    num -= 1;
                }
            }
            start += 1;
            end -= 2;
        }

    }

    public void tesselation(int n){
        int start = (WIDTH - n)/2;
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        tesselationHelper(start,0,n,world);
        ter.renderFrame(world);

    }

    private void tesselationHelper(int x, int y, int n, TETile[][] ter){
        if (x + n * (n-1) > WIDTH || y + n * (n-1) > HEIGHT){
            return;
        }
        else if (x < 0 || y < 0){
            return;
        }
        else{
            HexWorld.addHexagon(n,ter,x,y,randomType());
            tesselationHelper(x+n * (n-1)-1,y + n,n,ter);
            tesselationHelper(x-n * (n-1)+1,y + n,n,ter);
        }

    }
    
    private TETile randomType(){
        Random RANDOM = new Random();
        int tileNum = RANDOM.nextInt(3);
        switch (tileNum) {
            case 0: return Tileset.WATER;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.SAND;
            default: return Tileset.WALL;
        }
    }

    public static void main(String[] args) {
        HexWorld test = new HexWorld();
        test.tesselation(3);



    }
}
