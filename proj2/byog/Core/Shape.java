package byog.Core;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Shape {
    
    public static void square(int posX, int posY, int halfLength, int halfWidth,TETile[][] tel){

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

    }


    public static void hallway(int posX, int posY, TETile[][] tel){
        int manxLength = tel[0].length;
        int maxWidth = tel.length;

        if (posX > maxWidth || posY > manxLength) { return;}

        tel[posX][posY] = Tileset.FLOOR;
    }


}
