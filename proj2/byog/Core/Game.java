package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.util.Scanner;

import java.awt.Font;
import java.util.Set;
import java.awt.Color;
import javax.management.monitor.GaugeMonitor;
import javax.swing.Spring;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    private boolean gameOver;
    private Font titleFont;
    private Font smallFont;
    private TETile[][] world;
    private MapGenerator g;
    


    public Game(){
        ter.initialize(WIDTH, HEIGHT,0,0);
        gameOver = false;
        titleFont = new Font("Monaco", Font.BOLD, 30);
        smallFont = new Font("Monaco", Font.BOLD, 20);
    }
    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
        char last = ' ';
        char current = ' ';
        String input = null;
        startPage();
        while (last != ':' || current != 'q'){
            if (!StdDraw.hasNextKeyTyped()){
                continue;
            }
            last = current;
            current = StdDraw.nextKeyTyped();
            if (current == 'n') {
                input = newGame();
                break;
            }
            if (current == 'l'){
                input = "123";
                break;
            }
            if (current == 'q'){
                break;
            }

        }
        if (input == null) {
            endGame();
            return;
        }
        world = playWithInputString(input);
        System.out.println(TETile.toString(world));
        ter.renderFrame(world);
        last = ' ';
        current = ' ';
        while (last != ':' || current != 'q'){
            int x = (int) StdDraw.mouseX();
            int y = (int) StdDraw.mouseY();
            GUI(x,y,world);
            if (!StdDraw.hasNextKeyTyped()){
                continue;
            }
            last = current;
            current = StdDraw.nextKeyTyped();
            movePlayer(world, current);
            //GUI(x,y,world);
            if (reachGate()){
                winGame();
                return;
            }
        }
        endGame();
    }

    public boolean reachGate(){
        return (g.getGateX() == g.getPlayerX()) && (g.getGateY() == g.getPlayerY());
    }

    public void movePlayer(TETile[][] tel, char c){
        int x = g.getPlayerX();
        int y = g.getPlayerY();
        switch (c){
            case 'w':
                g.updatePlayer(x, y+1, tel);
                break;
            case 's':
                g.updatePlayer(x, y-1, tel);
                break;
            case 'a':
                g.updatePlayer(x-1, y, tel);
                break;
            case 'd':
                g.updatePlayer(x+1, y, tel);
                break;

        }

    }

    public void endGame(){
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(WIDTH/2, HEIGHT/2, "GAME OVER!!!");
        StdDraw.show();
    }

    public void winGame(){
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(WIDTH/2, HEIGHT/2, "YOU WIN!!!");
        StdDraw.show();
    }


    public String newGame(){
        StdDraw.clear(Color.BLACK);
        StdDraw.text(WIDTH/2, HEIGHT/2, "Please enter the seed to start (Integers only)");
        StdDraw.show();
        System.out.println("Please enter the seed to start (Integers only)");
        String input = "";
        char current = ' ';
        while (current != '\n'){
            if (!StdDraw.hasNextKeyTyped()){
                continue;
            }
            current = StdDraw.nextKeyTyped();
            input += current;
            StdDraw.clear(Color.BLACK);
            StdDraw.text(WIDTH/2, HEIGHT/2, "Please enter the seed to start (Integers only)");
            StdDraw.text(WIDTH/2, HEIGHT/2-2, input);
            StdDraw.show();
        }
        return input;
    }

    private int stringToInt(String s){
        char[] c = s.toCharArray();
        String ans = "";
        int index = 0;
        while (index < c.length){
            if (Character.isDigit(c[index])){
                ans += c[index];
            }

            index += 1;
        }

        return Integer.parseInt(ans);
    }



    private void GUI(int x, int y, TETile[][] tel){
        ter.renderFrame(tel);
        StdDraw.setFont(smallFont);
        StdDraw.setPenColor(StdDraw.WHITE);
        String type = "";
        if (x < WIDTH && y < HEIGHT){
        type = symbolToString(tel[x][y]);}
        StdDraw.text(5, HEIGHT-1, type);
        StdDraw.show();
    }

    private String symbolToString(TETile s){

        if (s.equals(Tileset.PLAYER)) return "Player";
        if (s.equals(Tileset.WALL)) return "Wall";
        if (s.equals(Tileset.UNLOCKED_DOOR)) return "Unlock Door";
        if (s.equals(Tileset.FLOOR)) return "Floor";
        return " ";
    }

    private void startPage(){
        StdDraw.clear(Color.BLACK);
        StdDraw.setFont(titleFont);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(WIDTH/2, HEIGHT * 2 / 3, "CS61B The Game");
        StdDraw.setFont(smallFont);
        StdDraw.text(WIDTH/2, HEIGHT/2, "New Game (N)");
        StdDraw.text(WIDTH/2, HEIGHT/2-2, "Load Game (L)");
        StdDraw.text(WIDTH/2, HEIGHT/2-4, "Quit (Q)");
        StdDraw.show();
    } 

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
        int seed = stringToInt(input);
        g = new MapGenerator(WIDTH, HEIGHT, seed,100);
        for (int i = 0; i < WIDTH; i ++){
            for (int j = 0; j < HEIGHT; j ++){
                finalWorldFrame[i][j] = Tileset.NOTHING;
            }
        }

        for ( int i = 0; i < 50; i ++){
            g.square(g.pos.getX(), g.pos.getY(), g.RANDOM.nextInt(1,5), g.RANDOM.nextInt(1,5), finalWorldFrame);
            //g.findWay(tel);
            g.randomPosition();
        }
        g.findWay(finalWorldFrame);
        g.addWall(finalWorldFrame);
        g.addGate(finalWorldFrame);
        g.addCharactor(finalWorldFrame);
        return finalWorldFrame;
    }
}
